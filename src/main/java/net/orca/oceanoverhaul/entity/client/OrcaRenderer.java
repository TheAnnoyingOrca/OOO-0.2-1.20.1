package net.orca.oceanoverhaul.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.orca.oceanoverhaul.OceanOverhaul;
import net.orca.oceanoverhaul.entity.client.orca.OrcaBodyLayer;
import net.orca.oceanoverhaul.entity.client.orca.OrcaEyePatchLayer;
import net.orca.oceanoverhaul.entity.client.orca.OrcaSaddlePatchLayer;
import net.orca.oceanoverhaul.entity.custom.OrcaEntity;

public class OrcaRenderer extends MobRenderer<OrcaEntity, OrcaModel<OrcaEntity>> {
    public OrcaRenderer(EntityRendererProvider.Context pContext ) {
        super(pContext, new OrcaModel<>(pContext.bakeLayer(ModModelLayers.ORCA_LAYER)), 3f);
        this.addLayer(new OrcaBodyLayer(this));
        this.addLayer(new OrcaSaddlePatchLayer(this));
        this.addLayer(new OrcaEyePatchLayer(this));
        //this.addLayer(new OrcaHeldItemLayer(this, pContext.getItemInHandRenderer()));
    }

    @Override
    public ResourceLocation getTextureLocation(OrcaEntity pEntity) {
        return new ResourceLocation(OceanOverhaul.MOD_ID, "textures/entity/orca/orca.png");
    }


    @Override
    public void render(OrcaEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
    @Override
    protected void setupRotations(OrcaEntity animatable, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTick) {
        super.setupRotations(animatable, poseStack, ageInTicks, rotationYaw, partialTick);

        poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTick, -animatable.prevTilt, -animatable.tilt)));
    }
}

