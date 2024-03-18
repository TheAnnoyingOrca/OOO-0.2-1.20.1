package net.orca.oceanoverhaul.entity.custom;

import com.google.common.collect.Lists;
import net.minecraft.Util;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.orca.oceanoverhaul.effect.ModEffects;
import net.orca.oceanoverhaul.entity.client.orca.eyePatch;
import net.orca.oceanoverhaul.entity.client.orca.saddlePatch;
import net.orca.oceanoverhaul.entity.goals.OrcaBreathAirGoal;
import net.orca.oceanoverhaul.entity.goals.OrcaFollowBoatGoal;
import net.orca.oceanoverhaul.entity.goals.OrcaJumpGoal;
import net.orca.oceanoverhaul.item.ModItems;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Predicate;

public class OrcaEntity extends WaterAnimal implements NeutralMob {

    private static final EntityDataAccessor<Integer> MOISTNESS_LEVEL = SynchedEntityData.defineId(OrcaEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT = SynchedEntityData.defineId(OrcaEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME = SynchedEntityData.defineId(OrcaEntity.class, EntityDataSerializers.INT);

    static final TargetingConditions SWIM_WITH_PLAYER_TARGETING = TargetingConditions.forNonCombat().range(24.0D).ignoreLineOfSight();

    private static final Ingredient TEMPT_INGREDIENT = Ingredient.of(Items.COD, Items.SALMON, ModItems.KELPFISH.get());

    private static final EntityDataAccessor<Boolean> DATA_TRUSTING = SynchedEntityData.defineId(OrcaEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(OrcaEntity.class, EntityDataSerializers.BYTE);

    private static final int FLAG_DEFENDING = 128;
    public static final int TOTAL_AIR_SUPPLY = 10000;
    private static final int TOTAL_MOISTNESS_LEVEL = 3200;

    private static final EntityDataAccessor<Optional<UUID>> DATA_TRUSTED_ID_0 = SynchedEntityData.defineId(OrcaEntity.class, EntityDataSerializers.OPTIONAL_UUID);
    private static final EntityDataAccessor<Optional<UUID>> DATA_TRUSTED_ID_1 = SynchedEntityData.defineId(OrcaEntity.class, EntityDataSerializers.OPTIONAL_UUID);
    private static final Predicate<Entity> TRUSTED_TARGET_SELECTOR = (p_28521_) -> {
        if (!(p_28521_ instanceof LivingEntity livingentity)) {
            return false;
        } else {
            return livingentity.getLastHurtMob() != null && livingentity.getLastHurtMobTimestamp() < livingentity.tickCount + 600;
        }
    };

    private int ticksSinceEaten;

    public OrcaEntity(EntityType<? extends WaterAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);


    }
    private void setFlag(int pFlagId, boolean pValue) {
        if (pValue) {
            this.entityData.set(DATA_FLAGS_ID, (byte)(this.entityData.get(DATA_FLAGS_ID) | pFlagId));
        } else {
            this.entityData.set(DATA_FLAGS_ID, (byte)(this.entityData.get(DATA_FLAGS_ID) & ~pFlagId));
        }

    }

    private boolean getFlag(int pFlagId) {
        return (this.entityData.get(DATA_FLAGS_ID) & pFlagId) != 0;
    }

    @Nullable
    private OrcaEntity.OrcaTemptGoal temptGoal;

    public boolean canBreatheUnderwater() {
        return true;
    }

    protected void handleAirSupply(int pAirSupply) {
    }

    public int getMoistnessLevel() {
        return this.entityData.get(MOISTNESS_LEVEL);
    }

    public void setMoisntessLevel(int pMoistnessLevel) {
        this.entityData.set(MOISTNESS_LEVEL, pMoistnessLevel);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(MOISTNESS_LEVEL, 2400);

        this.entityData.define(DATA_ID_TYPE_VARIANT, 0);

        this.entityData.define(DATA_REMAINING_ANGER_TIME, 0);
        this.entityData.define(DATA_TRUSTING, false);

        this.entityData.define(DATA_TRUSTED_ID_0, Optional.empty());
        this.entityData.define(DATA_TRUSTED_ID_1, Optional.empty());

        this.entityData.define(DATA_FLAGS_ID, (byte)0);
    }
    private boolean isMovingInWater() {
        return this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6D && this.isInWaterOrBubble();
    }

    public final AnimationState swimAnimationState = new AnimationState();
    public final AnimationState swimIdleAnimationState = new AnimationState();
    private int swimIdleAnimationTimeout = 0;






    boolean isTrusting() {
        return this.entityData.get(DATA_TRUSTING);
    }

    private void setTrusting(boolean pTrusting) {
        this.entityData.set(DATA_TRUSTING, pTrusting);
    }

    public float getHeadRollAngle(float pPartialTick) {
        return 0;
    }


    @Override
    protected void registerGoals() {


        this.goalSelector.addGoal(0, new OrcaBreathAirGoal(this));
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(2, new OrcaEntity.OrcaEntitySwimWithPlayerGoal(this, 2.0D));
        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 0.5D, 10));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 20.0F));
        this.goalSelector.addGoal(4, new OrcaJumpGoal(this, 10));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, (double) 1.2F, true));
        this.goalSelector.addGoal(8, new OrcaFollowBoatGoal(this));
        //this.goalSelector.addGoal(8, new OrcaPorpoiseGoal(this, 20));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Guardian.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Drowned.class, true));
        this.targetSelector.addGoal(7, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(8, new ResetUniversalAngerTargetGoal<>(this, true));
        this.targetSelector.addGoal(1, new OrcaEntity.DefendTrustedTargetGoal(LivingEntity.class, false, false, (p_28580_) -> {
            return TRUSTED_TARGET_SELECTOR.test(p_28580_) && !this.trusts(p_28580_.getUUID());
        }));

    }
    void clearStates() {
        this.setDefending(false);
    }
    public boolean removeWhenFarAway(double pDistanceToClosestPlayer) {
        return !this.isTrusting() && this.tickCount > 2400;
    }


    public static AttributeSupplier.Builder createMobAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 124)
                .add(Attributes.MOVEMENT_SPEED, (double) 2f)
                .add(Attributes.ARMOR_TOUGHNESS, 2.0f)
                .add(Attributes.ATTACK_KNOCKBACK, 0.8f)
                .add(Attributes.ATTACK_DAMAGE, 16F);
    }

    protected PathNavigation createNavigation(Level pLevel) {
        return new WaterBoundPathNavigation(this, pLevel);
    }

    public boolean doHurtTarget(Entity pEntity) {
        boolean flag = pEntity.hurt(this.damageSources().mobAttack(this), (float) ((int) this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
        if (flag) {
            this.doEnchantDamageEffects(this, pEntity);
            this.playSound(SoundEvents.DOLPHIN_ATTACK, 1.0F, 1.0F);
        }

        return flag;
    }

    public int getMaxAirSupply() {
        return 4800;
    }

    protected int increaseAirSupply(int pCurrentAir) {
        return this.getMaxAirSupply();
    }

    protected float getStandingEyeHeight(Pose pPose, EntityDimensions pSize) {
        return 0.3F;
    }

    /**
     * The speed it takes to move the entityliving's head rotation through the faceEntity method.
     */
    public int getMaxHeadXRot() {
        return 1;
    }

    public int getMaxHeadYRot() {
        return 1;
    }

    protected boolean canRide(Entity pEntity) {
        return true;
    }

    public boolean canTakeItem(ItemStack pItemstack) {
        EquipmentSlot equipmentslot = Mob.getEquipmentSlotForItem(pItemstack);
        if (!this.getItemBySlot(equipmentslot).isEmpty()) {
            return false;
        } else {
            return equipmentslot == EquipmentSlot.MAINHAND && super.canTakeItem(pItemstack);
        }
    }


    public void tick() {
        super.tick();
        if (this.isNoAi()) {
            this.setAirSupply(this.getMaxAirSupply());
        } else {
            if (this.isInWaterRainOrBubble()) {
                this.setMoisntessLevel(2400);
            } else {
                this.setMoisntessLevel(this.getMoistnessLevel() - 1);
                if (this.getMoistnessLevel() <= 0) {
                    this.hurt(this.damageSources().dryOut(),1.0F);
                }

                if (this.onGround()) {
                    this.setDeltaMovement(this.getDeltaMovement().add((double) ((this.random.nextFloat() * 2.0F - 1.0F) * 0.2F), 0.5D, (double) ((this.random.nextFloat() * 2.0F - 1.0F) * 0.2F)));
                    this.setYRot(this.random.nextFloat() * 360.0F);
                    this.setOnGround(false);
                    this.hasImpulse = true;
                }
            }

            if (this.level().isClientSide && this.isInWater() && this.getDeltaMovement().lengthSqr() > 0.03D) {
                Vec3 vec3 = this.getViewVector(0.0F);
                float f = Mth.cos(this.getYRot() * ((float) Math.PI / 180F)) * 0.3F;
                float f1 = Mth.sin(this.getYRot() * ((float) Math.PI / 180F)) * 0.3F;
                float f2 = 1.2F - this.random.nextFloat() * 0.7F;

                for (int i = 0; i < 2; ++i) {
                    this.level().addParticle(ParticleTypes.DOLPHIN, this.getX() - vec3.x * (double) f2 + (double) f, this.getY() - vec3.y, this.getZ() - vec3.z * (double) f2 + (double) f1, 0.0D, 0.0D, 0.0D);
                    this.level().addParticle(ParticleTypes.DOLPHIN, this.getX() - vec3.x * (double) f2 - (double) f, this.getY() - vec3.y, this.getZ() - vec3.z * (double) f2 - (double) f1, 0.0D, 0.0D, 0.0D);
                }
            }
            if (this.isMovingInWater()) {
                this.swimIdleAnimationState.stop();
                this.swimAnimationState.startIfStopped(this.tickCount);
            } else if (this.isInWaterOrBubble()) {
                this.swimAnimationState.stop();
                this.swimIdleAnimationState.startIfStopped(this.tickCount);
            } else {
                this.swimAnimationState.stop();
                this.swimIdleAnimationState.stop();
            }

        }
    }




    private void addParticlesAroundSelf(ParticleOptions pParticleOption) {
        for (int i = 0; i < 7; ++i) {
            double d0 = this.random.nextGaussian() * 0.01D;
            double d1 = this.random.nextGaussian() * 0.01D;
            double d2 = this.random.nextGaussian() * 0.01D;
            this.level().addParticle(pParticleOption, this.getRandomX(1.0D), this.getRandomY() + 0.2D, this.getRandomZ(1.0D), d0, d1, d2);
        }

    }


    public void travel(@NotNull Vec3 pTravelVector) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(this.getSpeed(), pTravelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(pTravelVector);
        }

    }


    public void addAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("Pattern", this.getTypePattern());
        pCompound.putBoolean("Trusting", this.isTrusting());
        List<UUID> list = this.getTrustedUUIDs();
        ListTag listtag = new ListTag();

        for(UUID uuid : list) {
            if (uuid != null) {
                listtag.add(NbtUtils.createUUID(uuid));
            }
        }
        pCompound.put("Trusted", listtag);
    }

    public void readAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setTypePattern(pCompound.getInt("Pattern"));
        this.setTrusting(pCompound.getBoolean("Trusting"));
        ListTag listtag = pCompound.getList("Trusted", 11);

        for(int i = 0; i < listtag.size(); ++i) {
            this.addTrustedUUID(NbtUtils.loadUUID(listtag.get(i)));
        }
    }

    private void setTypePattern(int pTypePattern) {
        this.entityData.set(DATA_ID_TYPE_VARIANT, pTypePattern);
    }

    private int getTypePattern() {
        return this.entityData.get(DATA_ID_TYPE_VARIANT);
    }

    private void seteyePatchandsaddlePatch(eyePatch peyePatch, saddlePatch psaddlePatch) {
        this.setTypePattern(peyePatch.getId() << 8 & '\uff00' | psaddlePatch.getId() << 8 & '\uff00');
    }


    public eyePatch geteyePatch() {
        return eyePatch.byId((this.getTypePattern() & '\uff00') >> 8);
    }

    public saddlePatch getsaddlePatch() {
        return saddlePatch.byId((this.getTypePattern() & '\uff00') >> 8);
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, @NotNull DifficultyInstance pDifficulty, @NotNull MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        RandomSource randomsource = pLevel.getRandom();


        this.seteyePatchandsaddlePatch(Util.getRandom(eyePatch.values(), randomsource), Util.getRandom(saddlePatch.values(), randomsource));
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return this.entityData.get(DATA_REMAINING_ANGER_TIME);
    }

    public void setRemainingPersistentAngerTime(int pTime) {
        this.entityData.set(DATA_REMAINING_ANGER_TIME, pTime);
    }


    @org.jetbrains.annotations.Nullable
    @Override
    public UUID getPersistentAngerTarget() {
        return null;
    }

    @Override
    public void setPersistentAngerTarget(@org.jetbrains.annotations.Nullable UUID pPersistentAngerTarget) {

    }

    @Override
    public void startPersistentAngerTimer() {

    }

    static class OrcaEntitySwimWithPlayerGoal extends Goal {
        private final OrcaEntity orcaentity;
        private final double speedModifier;
        @Nullable
        private Player player;

        OrcaEntitySwimWithPlayerGoal(OrcaEntity pOrcaEntity, double pSpeedModifier) {
            this.orcaentity = pOrcaEntity;
            this.speedModifier = pSpeedModifier;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            this.player = this.orcaentity.level().getNearestPlayer(OrcaEntity.SWIM_WITH_PLAYER_TARGETING, this.orcaentity);
            if (this.player == null) {
                return false;
            } else {
                return this.player.isSwimming() && this.orcaentity.getTarget() != this.player;
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean canContinueToUse() {
            return this.player != null && this.player.isSwimming() && this.orcaentity.distanceToSqr(this.player) < 256.0D;
        }

        public void tick() {
            this.orcaentity.getLookControl().setLookAt(this.player, (float) (this.orcaentity.getMaxHeadYRot() + 20), (float) this.orcaentity.getMaxHeadXRot());
            if (this.orcaentity.distanceToSqr(this.player) < 12.25D) {
                this.orcaentity.getNavigation().stop();
            } else {
                this.orcaentity.getNavigation().moveTo(this.player, this.speedModifier);
            }

            if (this.player.isSwimming() && this.orcaentity.isTrusting() && this.player.level().random.nextInt(6) == 0) {
                this.player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 100, 0, false, false, false), this.orcaentity);
                this.orcaentity.addEffect(new MobEffectInstance(ModEffects.SYNERGY.get(), 250));
                this.player.addEffect(new MobEffectInstance(ModEffects.SYNERGY.get(), 250));

                }else {
                    if (this.player.isSwimming() && this.player.level().random.nextInt(6) == 0) {
                        this.player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 100, 0, false, false, false), this.orcaentity);
                    }
                }


        }
    }

    public boolean isFood(ItemStack pStack) {
        return TEMPT_INGREDIENT.test(pStack);
    }

    public @NotNull InteractionResult mobInteract(Player pPlayer, @NotNull InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        Item item = itemstack.getItem();
        if (this.level().isClientSide) {
            if (this.isTrusting()) {
                return InteractionResult.SUCCESS;
            } else {
                return !this.isFood(itemstack) || !(this.getHealth() < this.getMaxHealth()) && this.isTrusting() ? InteractionResult.PASS : InteractionResult.SUCCESS;
            }
        } else {
            if ((this.temptGoal == null || this.temptGoal.isRunning()) && !this.isTrusting() && this.isFood(itemstack) && pPlayer.distanceToSqr(this) < 9.0D) {
                this.usePlayerItem(pPlayer, pHand, itemstack);
                if (!this.level().isClientSide) {
                    if (this.random.nextInt(12) == 0) {
                        this.setTrusting(true);
                        this.addTrustedUUID(pPlayer.getUUID());
                        this.spawnTrustingParticles(true);
                        this.level().broadcastEntityEvent(this, (byte) 41);
                    } else {
                        this.spawnTrustingParticles(false);
                        this.level().broadcastEntityEvent(this, (byte) 40);
                    }
                    itemstack.shrink(1);
                }
                return InteractionResult.sidedSuccess(this.level().isClientSide);


            } else {
                if (this.isTrusting()) {
                    if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
                        this.heal((float) itemstack.getFoodProperties(this).getNutrition());
                        this.usePlayerItem(pPlayer, pHand, itemstack);
                        itemstack.shrink(1);
                        return InteractionResult.CONSUME;
                    }

                    InteractionResult interactionresult = super.mobInteract(pPlayer, pHand);
                    return interactionresult;
                }
                return super.mobInteract(pPlayer, pHand);
            }

        }

    }
    protected void usePlayerItem (Player pPlayer, InteractionHand pHand, ItemStack pStack){
        if (this.isFood(pStack)) {
            this.playSound(this.getEatingSound(pStack), 1.0F, 1.0F);
        }

    }


    public void handleEntityEvent(byte pId) {
        if (pId == 41) {
            this.spawnTrustingParticles(true);
        } else if (pId == 40) {
            this.spawnTrustingParticles(false);
        } else {
            super.handleEntityEvent(pId);
        }

    }

    private void spawnTrustingParticles(boolean pIsTrusted) {
        ParticleOptions particleoptions = ParticleTypes.HEART;
        if (!pIsTrusted) {
            particleoptions = ParticleTypes.SMOKE;
        }

        for (int i = 0; i < 7; ++i) {
            double d0 = this.random.nextGaussian() * 0.02D;
            double d1 = this.random.nextGaussian() * 0.02D;
            double d2 = this.random.nextGaussian() * 0.02D;
            this.level().addParticle(particleoptions, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), d0, d1, d2);
        }

    }

    static class OrcaTemptGoal extends TemptGoal {
        private final OrcaEntity orcaentity;

        public OrcaTemptGoal(OrcaEntity pOrcaEntity, double pSpeedModifier, Ingredient pItems, boolean pCanScare) {
            super(pOrcaEntity, pSpeedModifier, pItems, pCanScare);
            this.orcaentity = pOrcaEntity;
        }

    }
    class DefendTrustedTargetGoal extends NearestAttackableTargetGoal<LivingEntity> {
        @Nullable
        private LivingEntity trustedLastHurtBy;
        @Nullable
        private LivingEntity trustedLastHurt;
        private int timestamp;

        public DefendTrustedTargetGoal(Class<LivingEntity> pTargetType, boolean pMustSee, @Nullable boolean pMustReach, Predicate<LivingEntity> pPredicate) {
            super(OrcaEntity.this, pTargetType, 10, pMustSee, pMustReach, pPredicate);
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            if (this.randomInterval > 0 && this.mob.getRandom().nextInt(this.randomInterval) != 0) {
                return false;
            } else {
                for(UUID uuid : OrcaEntity.this.getTrustedUUIDs()) {
                    if (uuid != null && OrcaEntity.this.level() instanceof ServerLevel) {
                        Entity entity = ((ServerLevel)OrcaEntity.this.level()).getEntity(uuid);
                        if (entity instanceof LivingEntity) {
                            LivingEntity livingentity = (LivingEntity)entity;
                            this.trustedLastHurt = livingentity;
                            this.trustedLastHurtBy = livingentity.getLastHurtByMob();
                            int i = livingentity.getLastHurtByMobTimestamp();
                            return i != this.timestamp && this.canAttack(this.trustedLastHurtBy, this.targetConditions);
                        }
                    }
                }

                return false;
            }
        }
        public void start() {
            this.setTarget(this.trustedLastHurtBy);
            this.target = this.trustedLastHurtBy;
            if (this.trustedLastHurt != null) {
                this.timestamp = this.trustedLastHurt.getLastHurtByMobTimestamp();
            }

            OrcaEntity.this.setDefending(true);
            super.start();
        }
    }
    List<UUID> getTrustedUUIDs() {
        List<UUID> list = Lists.newArrayList();
        list.add(this.entityData.get(DATA_TRUSTED_ID_0).orElse((UUID)null));
        list.add(this.entityData.get(DATA_TRUSTED_ID_1).orElse((UUID)null));
        return list;
    }

    void addTrustedUUID(@Nullable UUID pUuid) {
        if (this.entityData.get(DATA_TRUSTED_ID_0).isPresent()) {
            this.entityData.set(DATA_TRUSTED_ID_1, Optional.ofNullable(pUuid));
        } else {
            this.entityData.set(DATA_TRUSTED_ID_0, Optional.ofNullable(pUuid));
        }

    }
    public void setTarget(@Nullable LivingEntity pLivingEntity) {
        if (this.isDefending() && pLivingEntity == null) {
            this.setDefending(false);
        }

        super.setTarget(pLivingEntity);
    }

    boolean trusts(UUID pUuid) {
        return this.getTrustedUUIDs().contains(pUuid);
    }


    boolean isDefending() {
        return this.getFlag(128);
    }
    void setDefending(boolean pDefending) {
        this.setFlag(128, pDefending);
    }
}