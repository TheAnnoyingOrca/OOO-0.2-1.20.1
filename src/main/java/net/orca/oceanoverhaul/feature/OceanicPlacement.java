package net.orca.oceanoverhaul.feature;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.AquaticFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.NoiseBasedCountPlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.eventbus.api.IEventBus;

public class OceanicPlacement {
    public static final ResourceKey<PlacedFeature> WILD_KELP_COLD = PlacementUtils.createKey("wild_kelp_cold");

    //public static void bootstrap(BootstapContext<PlacedFeature> pContext) {
        //HolderGetter<NoneFeatureConfiguration> holdergetter = pContext.lookup(Registries.CONFIGURED_FEATURE);
        //Holder.Reference<NoneFeatureConfiguration> reference = holdergetter.getOrThrow(OceanicAquaticFeatures.WILD_KELP);
        //PlacementUtils.register(pContext, WILD_KELP_COLD, reference, NoiseBasedCountPlacement.of(120, 80.0D, 0.0D), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome());
    //}

    public static void register(IEventBus modEventBus) {
    }
}
