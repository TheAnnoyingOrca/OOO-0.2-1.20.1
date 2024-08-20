package net.orca.oceanoverhaul.entity.custom;

import com.mojang.datafixers.DataFixUtils;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.orca.oceanoverhaul.entity.client.othervariants.KelpFishVariant;
import net.orca.oceanoverhaul.item.OceanicItems;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

public class KelpFishEntity extends OceanicAbstractFish {
    public static final String BUCKET_VARIANT_TAG = "BucketVariantTag";
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT = SynchedEntityData.defineId(KelpFishEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(KelpFishEntity.class, EntityDataSerializers.BOOLEAN);

    public KelpFishEntity(EntityType<? extends OceanicAbstractFish> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 80, 10, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
    }

    public static AttributeSupplier.Builder createMobAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4);

    }

    private boolean isMovingInWater() {
        return this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6D && this.isInWaterOrBubble();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new KelpFishSchoolGoal(this));
    }


    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE_VARIANT, KelpFishVariant.BLUE_ROCKFISH.id());
        this.entityData.define(FROM_BUCKET, false);
    }

    public String getBodyType() {
        return KelpFishVariant.byId(this.entityData.get(DATA_ID_TYPE_VARIANT)).getBodyPlan();
    }

    public KelpFishVariant getVariant() {
        return KelpFishVariant.byId(this.entityData.get(DATA_ID_TYPE_VARIANT));
    }

    public void setVariant(KelpFishVariant variant) {
        this.entityData.set(DATA_ID_TYPE_VARIANT, variant.id());
    }

    @Override
    public void saveToBucketTag(ItemStack bucket) {
        if (this.hasCustomName()) {
            bucket.setHoverName(this.getCustomName());
        }
        Bucketable.saveDefaultDataToBucketTag(this, bucket);
        CompoundTag compoundnbt = bucket.getOrCreateTag();
        compoundnbt.putInt(BUCKET_VARIANT_TAG, this.getVariant().id());
    }

    @Override
    @Nonnull
    public ItemStack getBucketItemStack() {
        ItemStack stack = new ItemStack(OceanicItems.KELPFISH_BUCKET.get());
        if (this.hasCustomName()) {
            stack.setHoverName(this.getCustomName());
        }
        return stack;
    }

    @Override
    public void loadFromBucketTag(@Nonnull CompoundTag compound) {
        Bucketable.loadDefaultDataFromBucketTag(this, compound);
        if (compound.contains("BucketVariantTag", 3)) {
            this.setVariant(KelpFishVariant.byId(compound.getInt("BucketVariantTag")));
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putInt("Variant", this.getVariant().id());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        int variant = compoundTag.getInt("Variant");
        this.setVariant(KelpFishVariant.byId((compoundTag.getInt("Variant"))));

    }


    protected SoundEvent getAmbientSound() {
        return SoundEvents.SALMON_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.SALMON_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource p_29795_) {
        return SoundEvents.SALMON_HURT;
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.SALMON_FLOP;
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance, MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnGroupData, @Nullable CompoundTag compoundTag) {
        if (mobSpawnType == MobSpawnType.BUCKET && compoundTag != null && compoundTag.contains(BUCKET_VARIANT_TAG, 3)) {
            this.setVariant(KelpFishVariant.byId(compoundTag.getInt(BUCKET_VARIANT_TAG)));
        } else {

            KelpFishVariant variant;

            if (spawnGroupData instanceof KelpFishEntity.KelpFishGroupData) {
                KelpFishEntity.KelpFishGroupData groupData = (KelpFishEntity.KelpFishGroupData) spawnGroupData;
                variant = groupData.variant;
            } else {
                variant = Util.getRandom(KelpFishVariant.values(), serverLevelAccessor.getRandom());
                spawnGroupData = new KelpFishEntity.KelpFishGroupData(variant);
            }

            this.setVariant(variant);
        }
        return spawnGroupData;
    }

    public final AnimationState swimAnimationState = new AnimationState();
    public final AnimationState swimIdleAnimationState = new AnimationState();
    private int swimIdleAnimationTimeout = 0;

    public boolean isMaxGroupSizeReached(int pSize) {
        return !this.isSchool;
    }

    private boolean isSchool = true;

    private static class KelpFishGroupData implements SpawnGroupData {
        final KelpFishVariant variant;

        KelpFishGroupData(KelpFishVariant variant) {
            this.variant = variant;
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
    public static boolean checkKelpFishSpawnRules(EntityType<KelpFishEntity> entityType, LevelAccessor levelAccessor, MobSpawnType mobSpawnType, BlockPos blockPos, RandomSource randomSource) {
        return levelAccessor.getFluidState(blockPos.below()).is(FluidTags.WATER) && levelAccessor.getBlockState(blockPos.above()).is(Blocks.WATER) || WaterAnimal.checkSurfaceWaterAnimalSpawnRules(entityType, levelAccessor, mobSpawnType, blockPos, randomSource);
    }
    public static class KelpFishSchoolGoal extends Goal {
        private final KelpFishEntity kelpFish;
        private int timeToRecalcPath;
        private int nextStartTick;

        public KelpFishSchoolGoal(KelpFishEntity kelpFish) {
            this.kelpFish = kelpFish;
            this.nextStartTick = this.nextStartTick(kelpFish);
        }

        protected int nextStartTick(KelpFishEntity kelpFish) {
            return reducedTickDelay(200 + kelpFish.getRandom().nextInt(200) % 20);
        }

        public boolean canUse() {
            if (this.kelpFish.hasFollowers()) {
                return false;
            } else if (this.kelpFish.isFollower()) {
                return true;
            } else if (this.nextStartTick > 0) {
                --this.nextStartTick;
                return false;
            } else {
                this.nextStartTick = this.nextStartTick(this.kelpFish);
                Predicate<KelpFishEntity> predicate = (schoolingFishx) -> (schoolingFishx.canBeFollowed() || !schoolingFishx.isFollower()) && schoolingFishx.getVariant() == this.kelpFish.getVariant();
                List<? extends KelpFishEntity> list = this.kelpFish.level().getEntitiesOfClass(KelpFishEntity.class, this.kelpFish.getBoundingBox().inflate(8.0, 8.0, 8.0), predicate);
                KelpFishEntity tetra1 = DataFixUtils.orElse(list.stream().filter(KelpFishEntity::canBeFollowed).findAny(), this.kelpFish);
                tetra1.addFollowers(list.stream().filter((schoolingFishx) -> !schoolingFishx.isFollower()));
                return this.kelpFish.isFollower();
            }
        }

        public boolean canContinueToUse() {
            return this.kelpFish.isFollower() && this.kelpFish.inRangeOfLeader();
        }

        public void start() {
            this.timeToRecalcPath = 0;
        }

        public void stop() {
            this.kelpFish.stopFollowing();
        }

        public void tick() {
            if (--this.timeToRecalcPath <= 0) {
                this.timeToRecalcPath = this.adjustedTickDelay(10);
                this.kelpFish.pathToLeader();
            }
        }
    }
}

