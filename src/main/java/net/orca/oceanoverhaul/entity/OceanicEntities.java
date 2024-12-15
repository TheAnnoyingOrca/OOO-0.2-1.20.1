package net.orca.oceanoverhaul.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.orca.oceanoverhaul.OceanOverhaul;
import net.orca.oceanoverhaul.entity.custom.KelpFishEntity;
import net.orca.oceanoverhaul.entity.custom.OrcaEntity;
import net.orca.oceanoverhaul.entity.custom.PygmySEntity;

public class OceanicEntities {
    public static final DeferredRegister<EntityType<?>> OCEANIC_ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, OceanOverhaul.MOD_ID);

    public static final RegistryObject<EntityType<OrcaEntity>> ORCA =
            OCEANIC_ENTITY_TYPES.register("orca", () -> EntityType.Builder.of(OrcaEntity::new, MobCategory.WATER_CREATURE).sized(3.5f, 1.4f).build("orca"));
    public static final RegistryObject<EntityType<KelpFishEntity>> KELPFISH =
            OCEANIC_ENTITY_TYPES.register("kelpfish", () -> EntityType.Builder.of(KelpFishEntity::new, MobCategory.WATER_AMBIENT).sized(0.3f, 0.3f).build("kelpfish"));
    public static final RegistryObject<EntityType<PygmySEntity>> PYGMYS =
            OCEANIC_ENTITY_TYPES.register("pygmys", () -> EntityType.Builder.of(PygmySEntity::new, MobCategory.WATER_CREATURE).sized(1.5f, 0.8f).build("pygmys"));

    public static void register(IEventBus eventBus) {
        OCEANIC_ENTITY_TYPES.register(eventBus);
    }
}
