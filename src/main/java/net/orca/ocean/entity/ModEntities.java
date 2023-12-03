package net.orca.ocean.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.orca.ocean.Ocean;
import net.orca.ocean.entity.custom.OrcaEntity;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Ocean.MOD_ID);

    public static final RegistryObject<EntityType<OrcaEntity>> ORCA =
            ENTITY_TYPES.register("orca", () -> EntityType.Builder.of(OrcaEntity::new, MobCategory.WATER_CREATURE).sized(3.5f, 1.5f).build("orca"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
