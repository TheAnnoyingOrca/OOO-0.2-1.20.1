package net.orca.oceanoverhaul.entity.client;// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.orca.oceanoverhaul.entity.animations.OrcaAnimationDefinitions;
import net.orca.oceanoverhaul.entity.custom.OrcaEntity;
import org.joml.Vector3f;

import static org.joml.Math.clamp;

public class OrcaModel<T extends OrcaEntity> extends HierarchicalModel<T> {
	public final ModelPart head;
	private final ModelPart tail;
	private final ModelPart fluke;

	public OrcaModel(ModelPart root) {
		this.head = root.getChild("head");
		this.tail = head.getChild("tail");
		this.fluke = tail.getChild("fluke");
	}

	public static LayerDefinition createBodyLayer() {


		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-14.0F, -22.0F, -29.5F, 28.0F, 22.0F, 59.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition tail = head.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(87, 81).addBox(-6.0F, -4.5F, -3.0F, 12.0F, 9.0F, 25.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.5F, 29.5F));

		PartDefinition fluke = tail.addOrReplaceChild("fluke", CubeListBuilder.create().texOffs(0, 81).addBox(-20.0F, -1.0F, 0.0F, 40.0F, 2.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, 19.0F));

		PartDefinition dorsalFin = head.addOrReplaceChild("dorsalFin", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -19.0F, 0.0F, 2.0F, 19.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -22.0F, 7.5F, -0.1309F, 0.0F, 0.0F));

		PartDefinition flipperLeft = head.addOrReplaceChild("flipperLeft", CubeListBuilder.create(), PartPose.offset(14.0F, 0.0F, -6.0F));

		PartDefinition flipperLeft_r1 = flipperLeft.addOrReplaceChild("flipperLeft_r1", CubeListBuilder.create().texOffs(115, 0).addBox(-1.0F, -1.0F, -1.0F, 23.0F, 2.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, 0.0F, -0.4363F, 0.7854F));

		PartDefinition flipperRight = head.addOrReplaceChild("flipperRight", CubeListBuilder.create(), PartPose.offset(-14.0F, 0.0F, -6.0F));

		PartDefinition flipperRight_r1 = flipperRight.addOrReplaceChild("flipperRight_r1", CubeListBuilder.create().texOffs(115, 0).mirror().addBox(-22.0F, -1.0F, 0.0F, 23.0F, 2.0F, 17.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.4363F, -0.7854F));

		return LayerDefinition.create(meshdefinition, 208, 208);
	}
	//float f = 18.0F;
	//float f1 = -8.0F;
	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return head;
	}
	public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pHeadYaw, float pHeadPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
		this.head.yRot = pHeadYaw * ((float)Math.PI / 180F);
		if (pEntity.isInWaterOrBubble()){
			if (pEntity.getDeltaMovement().horizontalDistanceSqr() > 1.0E-7D) {
				//this.animateWalk(OrcaAnimationDefinitions.swim, pLimbSwing, pLimbSwingAmount,2.0F, 2.5F);
				//this.animate(pEntity.swimIdleAnimationState, OrcaAnimationDefinitions.swimIdle, pAgeInTicks);
				this.head.xRot += -0.05F - 0.05F * Mth.cos(pAgeInTicks * 0.3F);
				this.tail.xRot =-0.1F * Mth.cos(+1.0F - pAgeInTicks * 0.3F);
				//this.tail.yRot = (-pHeadYaw/4*(3 - pLimbSwingAmount));
				this.fluke.xRot = -0.5F * Mth.cos(+2.0F - pAgeInTicks * 0.3F);

			}


		}

		//if (pEntity.getDeltaMovement().horizontalDistanceSqr() > 1.0E-7D) {
			//this.head.xRot += -0.05F - 0.05F * Mth.cos(pAgeInTicks * 2.5F);
		//}




		}


	private static final Vector3f ANIMATION_VECTOR_CACHE = new Vector3f();

	//private void animateWalk(AnimationState swimAnimationState, AnimationDefinition swim, T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks) {
		//long i = (long)(pLimbSwing * 50.0F * pAgeInTicks);
		//float f = Math.min(pLimbSwingAmount * pAgeInTicks, 1.0F);
		//KeyframeAnimations.animate(this, swim, i, f, ANIMATION_VECTOR_CACHE);
	}





//if (pEntity.getDeltaMovement().horizontalDistanceSqr() > 1.0E-7D) {
//			this.head.xRot += -0.05F - 0.05F * Mth.cos(pAgeInTicks * 0.3F);
//			this.tail.xRot = -0.1F * Mth.cos(+1.0F - pAgeInTicks * 0.3F);
//			this.fluke.xRot = -0.2F * Mth.cos(+2.0F - pAgeInTicks * 0.3F);
//this.root().getAllParts().forEach(ModelPart::resetPose);
//			this.animate(pEntity.swimAnimationState, OrcaAnimationDefinitions.swim, pAgeInTicks);
//			this.animate(pEntity.swimIdleAnimationState, OrcaAnimationDefinitions.swimIdle, pAgeInTicks);
//this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
//		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
//		if (pEntity.getDeltaMovement().horizontalDistanceSqr() > 1.0E-7D);{
//			this.head.xRot += -0.05F - 0.05F * Mth.cos(pAgeInTicks * 0.3F);
//			this.tail.xRot = -0.1F * Mth.cos(+1.0F - pAgeInTicks * 0.3F);
//			this.fluke.xRot = -0.2F * Mth.cos(+2.0F - pAgeInTicks * 0.3F);
//		}