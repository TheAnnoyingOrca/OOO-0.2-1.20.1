package net.orca.oceanoverhaul.entity.client.orca;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.orca.oceanoverhaul.OceanOverhaul;
import net.orca.oceanoverhaul.entity.client.OrcaModel;
import net.orca.oceanoverhaul.entity.custom.OrcaEntity;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class OrcaBodyLayer extends RenderLayer<OrcaEntity, OrcaModel<OrcaEntity>> {
    private static final Map<body, ResourceLocation> LOCATION_BY_BODY = Util.make(Maps.newEnumMap(body.class), (p_117069_) -> {

        p_117069_.put(body.SCARRED, new ResourceLocation(OceanOverhaul.MOD_ID, "textures/entity/orca/bodycolor/body_scarred.png"));
        p_117069_.put(body.YELLER, new ResourceLocation(OceanOverhaul.MOD_ID, "textures/entity/orca/bodycolor/body_yeller.png"));
        p_117069_.put(body.SNUBBY, new ResourceLocation(OceanOverhaul.MOD_ID, "textures/entity/orca/bodycolor/body_snubby.png"));
        p_117069_.put(body.HOMEY, new ResourceLocation(OceanOverhaul.MOD_ID, "textures/entity/orca/bodycolor/body_homey.png"));

    });

    public OrcaBodyLayer(RenderLayerParent<OrcaEntity, OrcaModel<OrcaEntity>> pRenderer) {
        super(pRenderer);
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, OrcaEntity pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
    ResourceLocation resourcelocation = LOCATION_BY_BODY.get(pLivingEntity.getbody());
        if (resourcelocation != null && !pLivingEntity.isInvisible()) {
            VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.entityTranslucent(resourcelocation));
            this.getParentModel().renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, LivingEntityRenderer.getOverlayCoords(pLivingEntity, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}