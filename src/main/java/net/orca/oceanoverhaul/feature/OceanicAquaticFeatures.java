package net.orca.oceanoverhaul.feature;

import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;

public class OceanicAquaticFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> WILD_KELP = FeatureUtils.createKey("wild_kelp");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> pContext) {
        FeatureUtils.register(pContext, WILD_KELP, (Feature<NoneFeatureConfiguration>) OceanicFeatures.WILD_KELP.get());
    }

    public static void register(IEventBus modEventBus) {
    }
}
