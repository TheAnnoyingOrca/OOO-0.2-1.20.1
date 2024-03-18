package net.orca.oceanoverhaul.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.orca.oceanoverhaul.OceanOverhaul;
import net.orca.oceanoverhaul.entity.ModEntities;
import net.orca.oceanoverhaul.entity.custom.KelpFishEntity;
import net.orca.oceanoverhaul.entity.custom.OrcaEntity;

@Mod.EventBusSubscriber(modid = OceanOverhaul.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.ORCA.get(), OrcaEntity.createMobAttributes().build());
        event.put(ModEntities.KELPFISH.get(), KelpFishEntity.createMobAttributes().build());
    }
}
