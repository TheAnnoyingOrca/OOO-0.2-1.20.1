package net.orca.ocean.entity.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.orca.ocean.Ocean;
import net.orca.ocean.entity.client.orca.OrcaEyePatchLayer;
import net.orca.ocean.entity.client.orca.OrcaSaddlePatchLayer;

public class ModModelLayers {

    private final ResourceLocation model;
    public static final ModelLayerLocation ORCA_LAYER = new ModelLayerLocation(
            new ResourceLocation(Ocean.MOD_ID, "orca_layer"), "main");


    public ModModelLayers(ResourceLocation OrcaModel, String layer) {
        this.model = OrcaModel;
    }
}


