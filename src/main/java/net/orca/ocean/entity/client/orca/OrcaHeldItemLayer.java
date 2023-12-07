package net.orca.ocean.entity.client.orca;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.orca.ocean.entity.client.OrcaModel;
import net.orca.ocean.entity.custom.OrcaEntity;

@OnlyIn(Dist.CLIENT)
public class OrcaHeldItemLayer extends RenderLayer<OrcaEntity, OrcaModel<OrcaEntity>> {
    private final ItemInHandRenderer itemInHandRenderer;

    public OrcaHeldItemLayer(RenderLayerParent<OrcaEntity, OrcaModel<OrcaEntity>> p_234838_, ItemInHandRenderer p_234839_) {
        super(p_234838_);
        this.itemInHandRenderer = p_234839_;
    }

    public void render(PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, OrcaEntity pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        pMatrixStack.pushPose();
            float f = 0.75F;
            pMatrixStack.scale(0.75F, 0.75F, 0.75F);
            pMatrixStack.translate(0.0D, 0.5D, 10F);
        pMatrixStack.translate((double)((this.getParentModel()).head.x / 16.0F), (double)((this.getParentModel()).head.y / 16.0F), (double)((this.getParentModel()).head.z / 16.0F));
        float f1 = pLivingEntity.getHeadRollAngle(pPartialTicks);
        pMatrixStack.mulPose(Vector3f.ZP.rotation(f1));
        pMatrixStack.mulPose(Vector3f.YP.rotationDegrees(pNetHeadYaw));
        pMatrixStack.mulPose(Vector3f.XP.rotationDegrees(pHeadPitch));
        pMatrixStack.translate((double)0.06F, (double)0.27F, -0.5D);
        pMatrixStack.mulPose(Vector3f.XP.rotationDegrees(90.0F));

        ItemStack itemstack = pLivingEntity.getItemBySlot(EquipmentSlot.MAINHAND);
        this.itemInHandRenderer.renderItem(pLivingEntity, itemstack, ItemTransforms.TransformType.GROUND, false, pMatrixStack, pBuffer, pPackedLight);
        pMatrixStack.popPose();
    }
}