package net.orca.ocean.datagen;

import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.orca.ocean.Ocean;

public class OceanItemModelProvider extends ItemModelProvider {
    public OceanItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Ocean.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (Item i : Registry.ITEM) {
            if (i instanceof SpawnEggItem && ForgeRegistries.ITEMS.getKey(i).getNamespace().equals(Ocean.MOD_ID)) {
                getBuilder(ForgeRegistries.ITEMS.getKey(i).getPath())
                        .parent(getExistingFile(new ResourceLocation("item/template_spawn_egg")));
            }
        }
    }
}


