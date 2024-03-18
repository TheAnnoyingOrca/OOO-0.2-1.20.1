package net.orca.oceanoverhaul.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.orca.oceanoverhaul.OceanOverhaul;
import net.orca.oceanoverhaul.entity.ModEntities;
import net.orca.oceanoverhaul.entity.client.*;

@Mod.EventBusSubscriber(modid = OceanOverhaul.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.ORCA_LAYER, OrcaModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.ROCKFISH_LAYER, KelpFishModel::createRockFishBodyLayer);
        event.registerLayerDefinition(ModModelLayers.RONQUIL_LAYER, KelpFishModel::createRonquilBodyLayer);
        event.registerLayerDefinition(ModModelLayers.SCULPIN_LAYER, KelpFishModel::createSculpinBodyLayer);
        event.registerLayerDefinition(ModModelLayers.GREENLING_LAYER, KelpFishModel::createGreenlingBodyLayer);
        event.registerLayerDefinition(ModModelLayers.PERCH_LAYER, KelpFishModel::createPerchBodyLayer);
        event.registerLayerDefinition(ModModelLayers.POACHER_LAYER, KelpFishModel::createPoacherBodyLayer);
        event.registerLayerDefinition(ModModelLayers.SAILFIN_LAYER, KelpFishModel::createSailfinBodyLayer);
        event.registerLayerDefinition(ModModelLayers.CABEZON_LAYER, KelpFishModel::createCabezonBodyLayer);
        event.registerLayerDefinition(ModModelLayers.WARBONNET_LAYER, KelpFishModel::createWarbonnetBodyLayer);
        event.registerLayerDefinition(ModModelLayers.LUMP_LAYER, KelpFishModel::createLumpBodyLayer);
        event.registerLayerDefinition(ModModelLayers.RATFISH_LAYER, KelpFishModel::createRatfishBodyLayer);
        event.registerLayerDefinition(ModModelLayers.CATSHARK_LAYER, KelpFishModel::createCatSharkBodyLayer);
        event.registerLayerDefinition(ModModelLayers.DOGFISH_LAYER, KelpFishModel::createDogfishBodyLayer);
        event.registerLayerDefinition(ModModelLayers.LANCET_LAYER, KelpFishModel::createLancetBodyLayer);
        event.registerLayerDefinition(ModModelLayers.LEOPARD_LAYER, KelpFishModel::createLeopardBodyLayer);
        event.registerLayerDefinition(ModModelLayers.LINGCOD_LAYER, KelpFishModel::createLingcodBodyLayer);
        event.registerLayerDefinition(ModModelLayers.SNIPE_LAYER, KelpFishModel::createSnipeBodyLayer);
        event.registerLayerDefinition(ModModelLayers.WOLF_LAYER, KelpFishModel::createWolfBodyLayer);
    }
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.ORCA.get(), OrcaRenderer::new);
        event.registerEntityRenderer(ModEntities.KELPFISH.get(), KelpFishRenderer::new);
    }
}
