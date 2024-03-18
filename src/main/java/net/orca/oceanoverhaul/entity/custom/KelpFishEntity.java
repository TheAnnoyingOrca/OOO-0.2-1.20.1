package net.orca.oceanoverhaul.entity.custom;

import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.orca.oceanoverhaul.entity.client.othervariants.KelpFishVariant;
import net.orca.oceanoverhaul.item.ModItems;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class KelpFishEntity extends AbstractSchoolingFish {
    public static final String BUCKET_VARIANT_TAG = "BucketVariantTag";
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT = SynchedEntityData.defineId(KelpFishEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(KelpFishEntity.class, EntityDataSerializers.BOOLEAN);

    public KelpFishEntity(EntityType<? extends AbstractSchoolingFish> p_29790_, Level p_29791_) {
        super(p_29790_, p_29791_);
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
        super.registerGoals();}

    public int getMaxSchoolSize() {
            return 1;
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
        ItemStack stack = new ItemStack(ModItems.KELPFISH_BUCKET.get());
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


    //public ItemStack getBucketItemStack() {
    //    return new ItemStack(Items.);
    //}
//
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
        if (mobSpawnType != MobSpawnType.BUCKET) {
            KelpFishVariant[] variants = KelpFishVariant.values();
            KelpFishVariant variant = Util.getRandom(variants, serverLevelAccessor.getRandom());
            this.setVariant(variant);
        }
        if (mobSpawnType == MobSpawnType.BUCKET && compoundTag != null && compoundTag.contains(BUCKET_VARIANT_TAG, 3)) {
            this.setVariant(KelpFishVariant.byId(compoundTag.getInt(BUCKET_VARIANT_TAG)));
            return spawnGroupData;
        } else {
            RandomSource randomsource = serverLevelAccessor.getRandom();
            KelpFishVariant variant;
            if (spawnGroupData instanceof KelpFishEntity.KelpFishGroupData) {
                KelpFishEntity.KelpFishGroupData kelpfish$kelpfishgroupdata = (KelpFishEntity.KelpFishGroupData)spawnGroupData;
                variant = kelpfish$kelpfishgroupdata.variant;
        } else {
            this.isSchool = false;
        }

            return spawnGroupData;
        }

    }

    public final AnimationState swimAnimationState = new AnimationState();
    public final AnimationState swimIdleAnimationState = new AnimationState();
    private int swimIdleAnimationTimeout = 0;

    public boolean isMaxGroupSizeReached(int pSize) {
        return !this.isSchool;
    }

    private boolean isSchool = true;

    static class KelpFishGroupData extends AbstractSchoolingFish.SchoolSpawnGroupData {
        final KelpFishVariant variant;

        KelpFishGroupData(KelpFishEntity pLeader, KelpFishVariant pVariant) {
            super(pLeader);
            this.variant = pVariant;
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
}

    // void travel(@NotNull Vec3 pTravelVector) {
        //if (this.isEffectiveAi() && this.isInWater()) {
            //this.moveRelative(this.getSpeed(), pTravelVector);
            ////this.move(MoverType.SELF, this.getDeltaMovement());
            //this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
            //if (this.getTarget() == null) {
                //this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            //}
        //} else {
            //super.travel(pTravelVector);
        //}

    //}

// else {
//        RandomSource randomsource = serverLevelAccessor.getRandom();
//        KelpFishVariant variant;
//        if (spawnGroupData instanceof KelpFishEntity.KelpFishGroupData) {
//            KelpFishEntity.KelpFishGroupData kelpfish$kelpfishgroupdata = (KelpFishEntity.KelpFishGroupData)spawnGroupData;
//            variant = kelpfish$kelpfishgroupdata.variant;
//        } else {
//            this.isSchool = false;
//            KelpFishVariant = new KelpFishEntity();
//        }
//
//        return spawnGroupData;
//    }
//@Nullable
//    private AbstractSchoolingFish leader;
//    private int schoolSize = 1;
//
//    public boolean isFollower() {
//        return this.leader != null && this.leader.isAlive();
//    }
//
//    public KelpFishEntity startFollowing(KelpFishEntity pLeader) {
//        this.leader = pLeader;
//        pLeader.addFollower();
//        return pLeader;
//    }
//
//    public void stopFollowing() {
//        this.leader.removeFollower();
//        this.leader = null;
//    }
//
//    public void addFollower() {
//        ++this.schoolSize;
//    }
//
//    public void removeFollower() {
//        --this.schoolSize;
//    }
//
//    public boolean canBeFollowed() {
//        return this.hasFollowers() && this.schoolSize < this.getMaxSchoolSize();
//    }
//
//}
