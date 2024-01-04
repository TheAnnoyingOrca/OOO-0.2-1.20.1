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
    public static final ModelLayerLocation SCULPIN_LAYER = create("sculpin_layer");
    public static final ModelLayerLocation GREENLING_LAYER = create("greenling_layer");
    public static final ModelLayerLocation PERCH_LAYER = create("perch_layer");
    public static final ModelLayerLocation POACHER_LAYER = create("poacher_layer");
    public static final ModelLayerLocation SAILFIN_LAYER = create("sailfin_layer");
    public static final ModelLayerLocation CABEZON_LAYER = create("cabezon_layer");
    public static final ModelLayerLocation WARBONNET_LAYER = create("warbonnet_layer");
    public static final ModelLayerLocation LUMP_LAYER = create("lump_layer");
    public static final ModelLayerLocation RATFISH_LAYER = create("ratfish_layer");
    public static final ModelLayerLocation CATSHARK_LAYER = create("catshark_layer");
    public static final ModelLayerLocation DOGFISH_LAYER = create("dogfish_layer");
    public static final ModelLayerLocation LANCET_LAYER = create("lancet_layer");
    public static final ModelLayerLocation LEOPARD_LAYER = create("leopard_layer");
    public static final ModelLayerLocation LINGCOD_LAYER = create("lingcod_layer");
    public static final ModelLayerLocation SNIPE_LAYER = create("snipe_layer");
    public static final ModelLayerLocation WOLF_LAYER = create("wolf_layer");


    private static ModelLayerLocation create(String name) {
        return new ModelLayerLocation(new ResourceLocation(Ocean.MOD_ID, name), "main");
    }
    //public ModModelLayers(ResourceLocation KelpFishModel, String layer) {
        //this.model = KelpFishModel;
    //}
}


