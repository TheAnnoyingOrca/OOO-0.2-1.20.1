package net.orca.oceanoverhaul.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.orca.oceanoverhaul.OceanOverhaul;
import net.orca.oceanoverhaul.block.OceanicBlocks;

public class OceanicCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, OceanOverhaul.MOD_ID);

    public static final RegistryObject<CreativeModeTab> OCEANOVERHAULTAB = CREATIVE_MODE_TABS.register("oceanoverhaul_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(OceanicItems.KELPFISH.get()))
                    .title(Component.translatable("creativetab.oceanoverhaul_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(OceanicItems.KELPFISH.get());
                        pOutput.accept(OceanicItems.COOKED_KELPFISH.get());
                        pOutput.accept(OceanicItems.CLUMP.get());
                        pOutput.accept(OceanicItems.KELPFISH_BUCKET.get());
                        pOutput.accept(OceanicItems.KELPFISH_SPAWN_EGG.get());
                        pOutput.accept(OceanicItems.ORCA_SPAWN_EGG.get());
                        pOutput.accept(OceanicBlocks.WILD_KELP_BLOCK.get());
                        pOutput.accept(OceanicBlocks.BUDDING_KELP_BLOCK.get());
                        pOutput.accept(OceanicBlocks.SEDIMENT.get());
                        pOutput.accept(OceanicBlocks.ANEMONE_WHITE.get());
                        pOutput.accept(OceanicBlocks.ANEMONE_ORANGE.get());
                        pOutput.accept(OceanicBlocks.ANEMONE_GREEN.get());
                        pOutput.accept(OceanicBlocks.ANEMONE_MAGENTA.get());
                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}