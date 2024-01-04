package net.orca.ocean.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.orca.ocean.entity.ModEntities;
import net.orca.ocean.Ocean;
import net.orca.ocean.item.custom.ItemModFishBucket;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Ocean.MOD_ID);

    public static final RegistryObject<Item> KELPFISH = ITEMS.register("kelpfish",
            () -> new Item(new Item.Properties().food(ModFoods.KELPFISH)));
    private static net.minecraft.world.food.FoodProperties FoodProperties;
    public static final RegistryObject<Item> COOKED_KELPFISH = ITEMS.register("cooked_kelpfish",
            () -> new Item(new Item.Properties().food(ModFoods.COOKED_KELPFISH)));
    public static final RegistryObject<Item> CLUMP = ITEMS.register("clump",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ORCA_SPAWN_EGG = ITEMS.register("orca_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.ORCA, 0X343a42, 0Xccced5,
                    new Item.Properties()));
    public static final RegistryObject<Item> KELPFISH_SPAWN_EGG = ITEMS.register("kelpfish_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.KELPFISH, 0Xa96d3e, 0X3b5580,
                    new Item.Properties()));
    public static final RegistryObject<Item> KELPFISH_BUCKET = ITEMS.register("kelpfish_bucket",
            () -> new ItemModFishBucket(ModEntities.KELPFISH, Fluids.WATER, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
