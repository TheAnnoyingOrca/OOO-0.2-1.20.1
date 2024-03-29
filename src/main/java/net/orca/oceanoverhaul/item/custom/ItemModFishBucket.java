package net.orca.oceanoverhaul.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.orca.oceanoverhaul.entity.OceanicEntities;
import net.orca.oceanoverhaul.entity.client.othervariants.KelpFishVariant;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class ItemModFishBucket extends MobBucketItem {
    public ItemModFishBucket(Supplier<? extends EntityType<?>> fishTypeIn, Fluid fluid, Item.Properties builder) {
        super(fishTypeIn, () -> fluid, () -> SoundEvents.BUCKET_EMPTY_FISH, builder.stacksTo(1));
    }
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag){
        EntityType fishType = getFishType();
        if (fishType == OceanicEntities.KELPFISH.get()) {
            CompoundTag compoundnbt = itemStack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int i = compoundnbt.getInt("BucketVariantTag");
                String s = "entity.oceanoverhaul.kelpfish.variant_" + KelpFishVariant.byId(i).getSerializedName();
                list.add((Component.translatable(s)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            }
        }
    }

}
