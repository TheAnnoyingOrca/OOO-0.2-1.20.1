package net.orca.oceanoverhaul.item;

import net.minecraft.world.item.BannerPatternItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.orca.oceanoverhaul.OceanOverhaul;
import net.orca.oceanoverhaul.entity.OceanicEntities;
import net.orca.oceanoverhaul.item.custom.ItemModFishBucket;
import net.orca.oceanoverhaul.misc.OceanTags;

public class OceanicItems {
    public static final DeferredRegister<Item> OCEANIC_ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, OceanOverhaul.MOD_ID);

    public static final RegistryObject<Item> KELPFISH = OCEANIC_ITEMS.register("kelpfish",
            () -> new Item(new Item.Properties().food(OceanicFoods.KELPFISH)));
    private static net.minecraft.world.food.FoodProperties FoodProperties;
    public static final RegistryObject<Item> COOKED_KELPFISH = OCEANIC_ITEMS.register("cooked_kelpfish",
            () -> new Item(new Item.Properties().food(OceanicFoods.COOKED_KELPFISH)));
    public static final RegistryObject<Item> CLUMP = OCEANIC_ITEMS.register("clump",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ORCA_SPAWN_EGG = OCEANIC_ITEMS.register("orca_spawn_egg",
            () -> new ForgeSpawnEggItem(OceanicEntities.ORCA, 0X343a42, 0Xccced5,
                    new Item.Properties()));
    public static final RegistryObject<Item> KELPFISH_SPAWN_EGG = OCEANIC_ITEMS.register("kelpfish_spawn_egg",
            () -> new ForgeSpawnEggItem(OceanicEntities.KELPFISH, 0Xa96d3e, 0X3b5580,
                    new Item.Properties()));
    public static final RegistryObject<Item> KELPFISH_BUCKET = OCEANIC_ITEMS.register("kelpfish_bucket",
            () -> new ItemModFishBucket(OceanicEntities.KELPFISH, Fluids.WATER, new Item.Properties()));
    public static final RegistryObject<Item> PYGMYS_SPAWN_EGG = OCEANIC_ITEMS.register("pygmys_spawn_egg",
            () -> new ForgeSpawnEggItem(OceanicEntities.PYGMYS, 0Xd2d5d9, 0X3a4456,
                    new Item.Properties()));

    public static final RegistryObject<Item> BANNER_PATTERN_ORCA = OCEANIC_ITEMS.register("banner_pattern_orca", () -> new BannerPatternItem(OceanTags.PATTERN_ITEM_ORCA, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> BANNER_PATTERN_LANCET = OCEANIC_ITEMS.register("banner_pattern_lancet", () -> new BannerPatternItem(OceanTags.PATTERN_ITEM_LANCET, new Item.Properties().stacksTo(1)));



    public static void register(IEventBus eventBus) {
        OCEANIC_ITEMS.register(eventBus);
    }

}
