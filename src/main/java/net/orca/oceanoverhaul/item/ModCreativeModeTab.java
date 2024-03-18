package net.orca.oceanoverhaul.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.orca.oceanoverhaul.OceanOverhaul;
import net.orca.oceanoverhaul.block.ModBlocks;

public class ModCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, OceanOverhaul.MOD_ID);

    public static final RegistryObject<CreativeModeTab> OCEANOVERHAULTAB = CREATIVE_MODE_TABS.register("oceanoverhaul_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.KELPFISH.get()))
                    .title(Component.translatable("creativetab.oceanoverhaul_tab"))
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
                        pOutput.accept(ModBlocks.ANEMONE_WHITE.get());
                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}