package net.orca.oceanoverhaul.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.orca.oceanoverhaul.OceanOverhaul;
import net.orca.oceanoverhaul.entity.OceanicEntities;
import net.orca.oceanoverhaul.entity.custom.KelpFishEntity;
import net.orca.oceanoverhaul.entity.custom.OrcaEntity;
import net.orca.oceanoverhaul.entity.custom.PygmySEntity;

@Mod.EventBusSubscriber(modid = OceanOverhaul.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OceanicEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(OceanicEntities.ORCA.get(), OrcaEntity.createMobAttributes().build());
        event.put(OceanicEntities.KELPFISH.get(), KelpFishEntity.createMobAttributes().build());
        event.put(OceanicEntities.PYGMYS.get(), PygmySEntity.createMobAttributes().build());
    }
}
