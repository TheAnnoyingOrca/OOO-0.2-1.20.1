package net.orca.oceanoverhaul.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.orca.oceanoverhaul.OceanOverhaul;
import net.orca.oceanoverhaul.entity.custom.KelpFishEntity;

import javax.annotation.Nullable;

public class KelpFishRenderer extends MobRenderer<KelpFishEntity, KelpFishModel<KelpFishEntity>>{
    private final KelpFishModel<KelpFishEntity> rockfish = this.getModel();
    private final KelpFishModel<KelpFishEntity> ronquil;
    private KelpFishModel<KelpFishEntity> sculpin;
    private KelpFishModel<KelpFishEntity> greenling;
    private KelpFishModel<KelpFishEntity> perch;
    private KelpFishModel<KelpFishEntity> poacher;
    private KelpFishModel<KelpFishEntity> sailfin;
    private KelpFishModel<KelpFishEntity> cabezon;
    private KelpFishModel<KelpFishEntity> warbonnet;
    private KelpFishModel<KelpFishEntity> lump;
    private KelpFishModel<KelpFishEntity> ratfish;
    private KelpFishModel<KelpFishEntity> catshark;
    private KelpFishModel<KelpFishEntity> dogfish;
    private KelpFishModel<KelpFishEntity> lancet;
    private KelpFishModel<KelpFishEntity> leopard;
    private KelpFishModel<KelpFishEntity> lingcod;
    private KelpFishModel<KelpFishEntity> snipe;
    private KelpFishModel<KelpFishEntity> wolf;

    public KelpFishRenderer(EntityRendererProvider.Context context) {
        super (context, new KelpFishModel<>(context.bakeLayer(ModModelLayers.ROCKFISH_LAYER)), 0.2F);
        this.ronquil = new KelpFishModel<>(context.bakeLayer(ModModelLayers.RONQUIL_LAYER));
        this.sculpin = new KelpFishModel<>(context.bakeLayer(ModModelLayers.SCULPIN_LAYER));
        this.greenling = new KelpFishModel<>(context.bakeLayer(ModModelLayers.GREENLING_LAYER));
        this.perch = new KelpFishModel<>(context.bakeLayer(ModModelLayers.PERCH_LAYER));
        this.poacher = new KelpFishModel<>(context.bakeLayer(ModModelLayers.POACHER_LAYER));
        this.sailfin = new KelpFishModel<>(context.bakeLayer(ModModelLayers.SAILFIN_LAYER));
        this.cabezon = new KelpFishModel<>(context.bakeLayer(ModModelLayers.CABEZON_LAYER));
        this.warbonnet = new KelpFishModel<>(context.bakeLayer(ModModelLayers.WARBONNET_LAYER));
        this.lump = new KelpFishModel<>(context.bakeLayer(ModModelLayers.LUMP_LAYER));
        this.ratfish = new KelpFishModel<>(context.bakeLayer(ModModelLayers.RATFISH_LAYER));
        this.catshark = new KelpFishModel<>(context.bakeLayer(ModModelLayers.CATSHARK_LAYER));
        this.dogfish = new KelpFishModel<>(context.bakeLayer(ModModelLayers.DOGFISH_LAYER));
        this.lancet = new KelpFishModel<>(context.bakeLayer(ModModelLayers.LANCET_LAYER));
        this.leopard = new KelpFishModel<>(context.bakeLayer(ModModelLayers.LEOPARD_LAYER));
        this.lingcod = new KelpFishModel<>(context.bakeLayer(ModModelLayers.LINGCOD_LAYER));
        this.snipe = new KelpFishModel<>(context.bakeLayer(ModModelLayers.SNIPE_LAYER));
        this.wolf = new KelpFishModel<>(context.bakeLayer(ModModelLayers.WOLF_LAYER));

    }
    public void render(KelpFishEntity kelpFishEntity, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        this.model = switch (kelpFishEntity.getBodyType()) {
            case "rockfish" -> this.rockfish;
            case "ronquil" -> this.ronquil;
            case "sculpin" -> this.sculpin;
            case "greenling" -> this.greenling;
            case "perch" -> this.perch;
            case "poacher" -> this.poacher;
            case "sailfin" -> this.sailfin;
            case "cabezon" -> this.cabezon;
            case "warbonnet" -> this.warbonnet;
            case "lump" -> this.lump;
            case "ratfish" -> this.ratfish;
            case "catshark" -> this.catshark;
            case "dogfish" -> this.dogfish;
            case "lancet" -> this.lancet;
            case "leopard" -> this.leopard;
            case "lingcod" -> this.lingcod;
            case "snipe" -> this.snipe;
            case "wolf" -> this.wolf;

            default -> throw new IncompatibleClassChangeError();
        };
    super.render(kelpFishEntity, f, g, poseStack, multiBufferSource, i);
    }

    @Nullable
    @Override
    protected RenderType getRenderType(KelpFishEntity kelpFishEntity, boolean bl, boolean bl2, boolean bl3) {
        ResourceLocation resourceLocation = this.getTextureLocation(kelpFishEntity);
        return RenderType.entityTranslucent(resourceLocation);
    }
    public ResourceLocation getTextureLocation(KelpFishEntity kelpFishEntity) {
        return new ResourceLocation(OceanOverhaul.MOD_ID, "textures/entity/kelpfish/"+ kelpFishEntity.getVariant().getSerializedName() +".png");
    }
}
