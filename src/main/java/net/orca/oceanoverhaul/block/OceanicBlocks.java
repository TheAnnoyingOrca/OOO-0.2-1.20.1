package net.orca.oceanoverhaul.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.orca.oceanoverhaul.OceanOverhaul;
import net.orca.oceanoverhaul.block.custom.Anemone;
import net.orca.oceanoverhaul.block.custom.AnemoneSmall;
import net.orca.oceanoverhaul.block.custom.WildKelpHead;
import net.orca.oceanoverhaul.block.custom.WildKelpPlantBlock;
import net.orca.oceanoverhaul.item.OceanicItems;

import java.util.function.Supplier;

public class OceanicBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, OceanOverhaul.MOD_ID);

    public static final RegistryObject<Block> WILD_KELP_BLOCK = registerBlock("wild_kelp_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.KELP_PLANT).sound(SoundType.WET_GRASS).noCollission()));
    public static final RegistryObject<Block> BUDDING_KELP_BLOCK = registerBlock("budding_kelp_block",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.KELP_PLANT).sound(SoundType.WET_GRASS).noCollission(),
                    UniformInt.of(3, 7)));
    public static final RegistryObject<Block> SEDIMENT = registerBlock("sediment",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.CLAY).sound(SoundType.MUD)));

    public static final RegistryObject<Block> ANEMONE_WHITE = registerBlock("anemone_white",
            () -> new Anemone(BlockBehaviour.Properties.of().sound(SoundType.SLIME_BLOCK)));
    public static final RegistryObject<Block> ANEMONE_ORANGE = registerBlock("anemone_orange",
            () -> new Anemone(BlockBehaviour.Properties.of().sound(SoundType.SLIME_BLOCK)));

    public static final RegistryObject<Block> ANEMONE_GREEN = registerBlock("anemone_green",
            () -> new AnemoneSmall(BlockBehaviour.Properties.of().sound(SoundType.SLIME_BLOCK)));
    public static final RegistryObject<Block> ANEMONE_MAGENTA = registerBlock("anemone_magenta",
            () -> new AnemoneSmall(BlockBehaviour.Properties.of().sound(SoundType.SLIME_BLOCK)));
    public static final RegistryObject<Block> WILD_KELP_HEAD = registerBlock("wild_kelp",
            () -> new WildKelpHead(BlockBehaviour.Properties.of().sound(SoundType.WET_GRASS).noCollission()));
    public static final RegistryObject<Block> WILD_KELP_PLANT = registerBlock("wild_kelp_plant",
            () -> new WildKelpPlantBlock(BlockBehaviour.Properties.of().sound(SoundType.WET_GRASS).noCollission()));




    private static <T extends  Block>RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);

        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {

        return OceanicItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventbus) {
        BLOCKS.register(eventbus);
    }
}
