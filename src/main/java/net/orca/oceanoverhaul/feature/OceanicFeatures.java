package net.orca.oceanoverhaul.feature;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.orca.oceanoverhaul.OceanOverhaul;

import java.awt.*;

public class OceanicFeatures {
    public static final DeferredRegister<Feature<?>> OCEANIC_FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, OceanOverhaul.MOD_ID);

    public static final RegistryObject<Feature<?>> ANEMONE_WHITE = OCEANIC_FEATURES.register("anemone_white", () -> new AnemoneFeature());

    public static void register(IEventBus modEventBus) {
    };
}
