package net.orca.oceanoverhaul.feature;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.CountConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.orca.oceanoverhaul.OceanOverhaul;

import java.awt.*;
import java.util.concurrent.CountDownLatch;

public class OceanicFeatures {
    public static final DeferredRegister<Feature<?>> OCEANIC_FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, OceanOverhaul.MOD_ID);

    public static final RegistryObject<Feature<?>> ANEMONE_WHITE = OCEANIC_FEATURES.register("anemone_white", () -> new AnemoneFeature(CountConfiguration.CODEC));
    public static final RegistryObject<Feature<?>> ANEMONE_ORANGE = OCEANIC_FEATURES.register("anemone_orange", () -> new AnemoneFeature(CountConfiguration.CODEC));
    public static final RegistryObject<Feature<?>> ANEMONE_GREEN = OCEANIC_FEATURES.register("anemone_green", () -> new AnemoneSmallFeature(CountConfiguration.CODEC));
    public static final RegistryObject<Feature<?>> ANEMONE_MAGENTA = OCEANIC_FEATURES.register("anemone_magenta", () -> new AnemoneSmallFeature(CountConfiguration.CODEC));

    public static void register(IEventBus modEventBus) {
    };
}
