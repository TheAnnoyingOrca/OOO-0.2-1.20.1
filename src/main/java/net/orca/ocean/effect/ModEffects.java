package net.orca.ocean.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.orca.ocean.Ocean;

@Mod.EventBusSubscriber(modid = Ocean.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEffects extends MobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Ocean.MOD_ID);

    public static final RegistryObject<MobEffect> SYNERGY = MOB_EFFECTS.register("synergy",
            () -> new SynergyEffect(MobEffectCategory.BENEFICIAL, 363549));

    public static void register(IEventBus eventbus) {
        MOB_EFFECTS.register(eventbus);
    }
}


