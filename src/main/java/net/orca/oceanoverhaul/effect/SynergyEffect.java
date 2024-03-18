package net.orca.oceanoverhaul.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class SynergyEffect extends MobEffect{
    public SynergyEffect() {
        super (MobEffectCategory.BENEFICIAL, 0X363549);
        this.addAttributeModifier(Attributes.ARMOR, "845DB27C-C624-495F-8C9F-6020A9A58B6B", 1.5D, AttributeModifier.Operation.ADDITION);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, "CB3F55D3-645C-4F38-A497-9C13A33DB5CF", 2.5D, AttributeModifier.Operation.ADDITION);
        this.addAttributeModifier(Attributes.FOLLOW_RANGE, "CB3F55D3-645C-4F38-A497-9C13A33DB5CF", 2.5D, AttributeModifier.Operation.ADDITION);

    }
    public void applyEffectTick(LivingEntity LivingEntityIn, int amplifier) {
    }
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration > 0;
    }
    public String getDescriptionId() {
        return "oceanoverhaul.potion.synergy";
    }
}
