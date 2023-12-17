package net.orca.ocean.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties KELPFISH = new FoodProperties.Builder().meat().nutrition(2).saturationMod(0.1f)
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 150), 0.25f).build();
    public static final FoodProperties COOKED_KELPFISH = new FoodProperties.Builder().meat().nutrition(6).saturationMod(0.6f).build();
}
