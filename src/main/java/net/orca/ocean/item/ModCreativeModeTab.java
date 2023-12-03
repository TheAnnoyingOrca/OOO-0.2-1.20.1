package net.orca.ocean.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab OCEAN_TAB = new CreativeModeTab("oceantab") {
        @Override
        public ItemStack makeIcon() {
        return new ItemStack(ModItems.KELPFISH.get());
        }
    };
}
