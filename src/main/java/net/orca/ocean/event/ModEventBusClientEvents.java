package net.orca.ocean.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.orca.ocean.Ocean;
import net.orca.ocean.entity.client.ModModelLayers;
import net.orca.ocean.entity.client.OrcaModel;

@Mod.EventBusSubscriber(modid = Ocean.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.ORCA_LAYER, OrcaModel::createBodyLayer);
    }
}
