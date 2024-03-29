package net.orca.oceanoverhaul.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.orca.oceanoverhaul.OceanOverhaul;

@Mod.EventBusSubscriber(modid = OceanOverhaul.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OceanicEffects {
    public static final DeferredRegister<MobEffect> OCEANIC_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, OceanOverhaul.MOD_ID);

    public static final RegistryObject<MobEffect> SYNERGY = OCEANIC_EFFECTS.register("synergy", ()-> new SynergyEffect());
}


