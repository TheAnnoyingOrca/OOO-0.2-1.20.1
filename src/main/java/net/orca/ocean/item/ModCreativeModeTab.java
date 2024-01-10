package net.orca.ocean.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.orca.ocean.Ocean;
import net.orca.ocean.block.ModBlocks;

public class ModCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Ocean.MOD_ID);

    public static final RegistryObject<CreativeModeTab> OCEANTAB = CREATIVE_MODE_TABS.register("ocean_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.KELPFISH.get()))
                    .title(Component.translatable("creativetab.ocean_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.KELPFISH.get());
                        pOutput.accept(ModItems.COOKED_KELPFISH.get());
                        pOutput.accept(ModItems.CLUMP.get());
                        pOutput.accept(ModItems.KELPFISH_BUCKET.get());
                        pOutput.accept(ModItems.KELPFISH_SPAWN_EGG.get());
                        pOutput.accept(ModItems.ORCA_SPAWN_EGG.get());
                        pOutput.accept(ModBlocks.WILD_KELP_BLOCK.get());
                        pOutput.accept(ModBlocks.BUDDING_KELP_BLOCK.get());
                        pOutput.accept(ModBlocks.SEDIMENT.get());
                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}