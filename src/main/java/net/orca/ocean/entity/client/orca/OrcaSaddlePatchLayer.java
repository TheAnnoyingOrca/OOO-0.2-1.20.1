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
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.orca.ocean.entity.client.OrcaModel;
import net.orca.ocean.entity.custom.OrcaEntity;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class OrcaSaddlePatchLayer extends RenderLayer<OrcaEntity, OrcaModel<OrcaEntity>> {
    private static final Map<saddlePatch, ResourceLocation> LOCATION_BY_SADDLEPATCH = Util.make(Maps.newEnumMap(saddlePatch.class), (p_117069_) -> {
        p_117069_.put(saddlePatch.NONE, (ResourceLocation)null);
        p_117069_.put(saddlePatch.MISCHIEF, new ResourceLocation("textures/entity/orca/saddlepatches/saddlepatch_mischief"));
        p_117069_.put(saddlePatch.WORRYWORT, new ResourceLocation("textures/entity/orca/saddlepatches/saddlepatch_worrywort"));
        p_117069_.put(saddlePatch.STYLISH, new ResourceLocation("textures/entity/orca/saddlepatches/saddlepatch_stylish"));
        p_117069_.put(saddlePatch.SLENDER, new ResourceLocation("textures/entity/orca/saddlepatches/saddlepatch_slender"));
    });

    public OrcaSaddlePatchLayer(RenderLayerParent<OrcaEntity, OrcaModel<OrcaEntity>> pRenderer) {
        super(pRenderer);
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, OrcaEntity pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        ResourceLocation resourcelocation = LOCATION_BY_SADDLEPATCH.get(pLivingEntity.getsaddlePatch());
        if (resourcelocation != null && !pLivingEntity.isInvisible()) {
            VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.entityTranslucent(resourcelocation));
            this.getParentModel().renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, LivingEntityRenderer.getOverlayCoords(pLivingEntity, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}