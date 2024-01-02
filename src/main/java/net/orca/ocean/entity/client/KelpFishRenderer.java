package net.orca.ocean.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ByIdMap;
import net.orca.ocean.Ocean;
import net.orca.ocean.entity.client.othervariants.KelpFishVariant;
import net.orca.ocean.entity.custom.KelpFishEntity;

import javax.annotation.Nullable;

public class KelpFishRenderer extends MobRenderer<KelpFishEntity, KelpFishModel<KelpFishEntity>>{
    private final KelpFishModel<KelpFishEntity> rockfish = this.getModel();
    private final KelpFishModel<KelpFishEntity> ronquil;

    public KelpFishRenderer(EntityRendererProvider.Context context) {
        super (context, new KelpFishModel<>(context.bakeLayer(ModModelLayers.ROCKFISH_LAYER)), 0.2F);
        this.ronquil = new KelpFishModel<>(context.bakeLayer(ModModelLayers.RONQUIL_LAYER));
    }
    public void render(KelpFishEntity kelpFishEntity, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        this.model = switch (kelpFishEntity.getBodyType()) {
            case "rockfish" -> this.rockfish;
            case "ronquil" -> this.ronquil;
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
        return new ResourceLocation(Ocean.MOD_ID, "textures/entity/kelpfish/"+ kelpFishEntity.getVariant().getSerializedName() +".png");
    }
}
