package net.orca.oceanoverhaul;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.orca.oceanoverhaul.block.OceanicBlocks;
import net.orca.oceanoverhaul.effect.OceanicEffects;
import net.orca.oceanoverhaul.entity.OceanicEntities;
import net.orca.oceanoverhaul.entity.client.KelpFishRenderer;
import net.orca.oceanoverhaul.entity.client.OrcaRenderer;
import net.orca.oceanoverhaul.feature.OceanicAquaticFeatures;
import net.orca.oceanoverhaul.feature.OceanicFeatures;
import net.orca.oceanoverhaul.feature.OceanicPlacement;
import net.orca.oceanoverhaul.item.OceanicItems;
import net.orca.oceanoverhaul.misc.OceanicBanner;
import org.slf4j.Logger;

import static net.orca.oceanoverhaul.item.OceanicCreativeModeTab.*;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(OceanOverhaul.MOD_ID)
public class OceanOverhaul {
    public static final String MOD_ID = "oceanoverhaul";
    private static final Logger LOGGER = LogUtils.getLogger();

    //very important comment
    public OceanOverhaul() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        OceanicItems.register(modEventBus);
        OceanicBlocks.register(modEventBus);
        OceanicFeatures.register(modEventBus);
        OceanicBanner.BANNER_PATTERNS.register(modEventBus);
        OceanicAquaticFeatures.register(modEventBus);
        OceanicPlacement.register(modEventBus);


        register(modEventBus);

        OceanicEffects.OCEANIC_EFFECTS.register(modEventBus);
        OceanicEntities.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            SpawnPlacements.register(OceanicEntities.ORCA.get(),
                SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE,
                    WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
            SpawnPlacements.register(OceanicEntities.KELPFISH.get(),
                    SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE,
                    WaterAnimal::checkSurfaceWaterAnimalSpawnRules);


        });

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ItemBlockRenderTypes.setRenderLayer(OceanicBlocks.WILD_KELP_BLOCK.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(OceanicBlocks.WILD_KELP_HEAD.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(OceanicBlocks.WILD_KELP_PLANT.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(OceanicBlocks.BUDDING_KELP_BLOCK.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(OceanicBlocks.ANEMONE_ORANGE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(OceanicBlocks.ANEMONE_WHITE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(OceanicBlocks.ANEMONE_GREEN.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(OceanicBlocks.ANEMONE_MAGENTA.get(), RenderType.cutout());
            EntityRenderers.register(OceanicEntities.ORCA.get(), OrcaRenderer::new);
            EntityRenderers.register(OceanicEntities.KELPFISH.get(), KelpFishRenderer::new);
        }
    }
}
