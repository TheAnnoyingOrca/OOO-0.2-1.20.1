package net.orca.ocean.entity.client.orca;

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
import net.orca.ocean.Ocean;
import net.orca.ocean.entity.client.OrcaModel;
import net.orca.ocean.entity.custom.OrcaEntity;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class OrcaEyePatchLayer extends RenderLayer<OrcaEntity, OrcaModel<OrcaEntity>> {
    private static final Map<eyePatch, ResourceLocation> LOCATION_BY_EYEPATCH = Util.make(Maps.newEnumMap(eyePatch.class), (p_117069_) -> {
        p_117069_.put(eyePatch.MISCHIEF, new ResourceLocation(Ocean.MOD_ID, "textures/entity/orca/eyepatches/eyepatch_mischief.png"));
        p_117069_.put(eyePatch.WORRYWORT, new ResourceLocation(Ocean.MOD_ID, "textures/entity/orca/eyepatches/eyepatch_worrywort.png"));
        p_117069_.put(eyePatch.STYLISH, new ResourceLocation(Ocean.MOD_ID, "textures/entity/orca/eyepatches/eyepatch_stylish.png"));
        p_117069_.put(eyePatch.SLENDER, new ResourceLocation(Ocean.MOD_ID, "textures/entity/orca/eyepatches/eyepatch_slender.png"));
    });

    public OrcaEyePatchLayer(RenderLayerParent<OrcaEntity, OrcaModel<OrcaEntity>> pRenderer) {
        super(pRenderer);
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, OrcaEntity pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        ResourceLocation resourcelocation = LOCATION_BY_EYEPATCH.get(pLivingEntity.geteyePatch());
        if (resourcelocation != null && !pLivingEntity.isInvisible()) {
            VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.entityTranslucent(resourcelocation));
            this.getParentModel().renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, LivingEntityRenderer.getOverlayCoords(pLivingEntity, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}