package net.orca.ocean.entity.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.Salmon;
import net.minecraft.world.entity.animal.TropicalFish;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.orca.ocean.entity.client.othervariants.KelpFishVariant;
import net.orca.ocean.item.ModItems;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class KelpFishEntity extends AbstractSchoolingFish {
    public static final String BUCKET_VARIANT_TAG = "BucketVariantTag";
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT = SynchedEntityData.defineId(KelpFishEntity.class, EntityDataSerializers.INT);

private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(KelpFishEntity.class, EntityDataSerializers.BOOLEAN);
    public KelpFishEntity(EntityType<? extends AbstractSchoolingFish> p_29790_, Level p_29791_) {
        super(p_29790_, p_29791_);
        this.moveControl = new SmoothSwimmingMoveControl(this, 80, 10, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
    }
    public static AttributeSupplier. Builder createMobAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4);

    }
    private boolean isMovingInWater() {
        return this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6D && this.isInWaterOrBubble();
    }
    @Override
    protected void registerGoals() {
       super.registerGoals();
    }

    public int getMaxSchoolSize() {
        return 5;
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
        if(this.hasCustomName()){
            stack.setHoverName(this.getCustomName());
        }
        return stack;
    }
    @Override
    public void loadFromBucketTag(@Nonnull CompoundTag compound){
        Bucketable.loadDefaultDataFromBucketTag(this, compound);
        if (compound.contains("BucketVariantTag",3)){
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

}
