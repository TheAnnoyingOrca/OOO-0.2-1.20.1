package net.orca.ocean.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.orca.ocean.Ocean;
import net.orca.ocean.entity.ModEntities;
import net.orca.ocean.entity.client.OrcaRenderer;
import net.orca.ocean.entity.custom.KelpFishEntity;
import net.orca.ocean.entity.custom.OrcaEntity;

@Mod.EventBusSubscriber(modid = Ocean.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.ORCA.get(), OrcaEntity.createMobAttributes().build());
        event.put(ModEntities.KELPFISH.get(), KelpFishEntity.createMobAttributes().build());
    }
}
