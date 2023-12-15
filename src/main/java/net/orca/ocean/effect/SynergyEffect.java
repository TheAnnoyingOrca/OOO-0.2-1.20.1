package net.orca.ocean.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class SynergyEffect extends MobEffect{
    public SynergyEffect(MobEffectCategory mobEffectCategory, int color) {
        super (mobEffectCategory.BENEFICIAL, 363549);
        this.addAttributeModifier(Attributes.ARMOR_TOUGHNESS, "", 1.5D, AttributeModifier.Operation.ADDITION);
        this.addAttributeModifier(Attributes.FOLLOW_RANGE, "", 2.5D, AttributeModifier.Operation.ADDITION);

    }
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration > 0;
    }
    public String getDescriptionId() {
        return "ocean.potion.synergy";
    }
}
