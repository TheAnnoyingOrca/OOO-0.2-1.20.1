package net.orca.ocean.entity.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.orca.ocean.Ocean;
import net.orca.ocean.entity.client.orca.OrcaEyePatchLayer;
import net.orca.ocean.entity.client.orca.OrcaSaddlePatchLayer;

public class ModModelLayers {

    //private final ResourceLocation model;

    public static final ModelLayerLocation ORCA_LAYER = new ModelLayerLocation(
            new ResourceLocation(Ocean.MOD_ID, "orca_layer"), "main");
    //public static final ModelLayerLocation ROCKFISH_LAYER = new ModelLayerLocation(
            //new ResourceLocation(Ocean.MOD_ID, "rockfish_layer"), "main");
    public static final ModelLayerLocation ROCKFISH_LAYER = create("rockfish_layer");
    //public static final ModelLayerLocation RONQUIL_LAYER = new ModelLayerLocation(
            //new ResourceLocation(Ocean.MOD_ID, "ronquil_layer"), "main");
    public static final ModelLayerLocation RONQUIL_LAYER = create("ronquil_layer");


    private static ModelLayerLocation create(String name) {
        return new ModelLayerLocation(new ResourceLocation(Ocean.MOD_ID, name), "main");
    }
    //public ModModelLayers(ResourceLocation KelpFishModel, String layer) {
        //this.model = KelpFishModel;
    //}
}


