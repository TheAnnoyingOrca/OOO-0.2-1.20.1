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
import net.orca.oceanoverhaul.block.ModBlocks;
import net.orca.oceanoverhaul.effect.ModEffects;
import net.orca.oceanoverhaul.entity.ModEntities;
import net.orca.oceanoverhaul.entity.client.KelpFishRenderer;
import net.orca.oceanoverhaul.entity.client.OrcaRenderer;
import net.orca.oceanoverhaul.item.ModItems;
import org.slf4j.Logger;

import static net.orca.oceanoverhaul.item.ModCreativeModeTab.*;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(OceanOverhaul.MOD_ID)
public class OceanOverhaul {
    public static final String MOD_ID = "oceanoverhaul";
    private static final Logger LOGGER = LogUtils.getLogger();

    //very important comment
    public OceanOverhaul() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        register(modEventBus);

        ModEffects.MOD_EFFECTS.register(modEventBus);
        ModEntities.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            SpawnPlacements.register(ModEntities.ORCA.get(),
                SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE,
                    WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
            SpawnPlacements.register(ModEntities.KELPFISH.get(),
                    SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE,
                    WaterAnimal::checkSurfaceWaterAnimalSpawnRules);


        });

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.WILD_KELP_BLOCK.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.BUDDING_KELP_BLOCK.get(), RenderType.cutout());
            EntityRenderers.register(ModEntities.ORCA.get(), OrcaRenderer::new);
            EntityRenderers.register(ModEntities.KELPFISH.get(), KelpFishRenderer::new);
        }
    }
}
