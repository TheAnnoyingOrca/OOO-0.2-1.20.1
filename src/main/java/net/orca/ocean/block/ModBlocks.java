package net.orca.ocean.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.orca.ocean.item.ModCreativeModeTab;
import net.orca.ocean.item.ModItems;
import net.orca.ocean.Ocean;

import java.util.function.Supplier;

import static net.minecraft.util.valueproviders.UniformInt.*;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Ocean.MOD_ID);

    public static final RegistryObject<Block> WILD_KELP_BLOCK = registerBlock("wild_kelp_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.WATER_PLANT).sound(SoundType.WET_GRASS).noCollission()), ModCreativeModeTab.OCEAN_TAB);
    public static final RegistryObject<Block> BUDDING_KELP_BLOCK = registerBlock("budding_kelp_block",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.WATER_PLANT).sound(SoundType.WET_GRASS).noCollission(),
                    UniformInt.of(3, 7)), ModCreativeModeTab.OCEAN_TAB);
    public static final RegistryObject<Block> SEDIMENT = registerBlock("sediment",
            () -> new Block(BlockBehaviour.Properties.of(Material.CLAY).sound(SoundType.MUD)), ModCreativeModeTab.OCEAN_TAB);





    private static <T extends  Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);

        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {

        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventbus) {
        BLOCKS.register(eventbus);
    }
}
