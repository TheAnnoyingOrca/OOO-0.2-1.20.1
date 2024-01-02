package net.orca.ocean.entity.client;// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.orca.ocean.entity.animations.OrcaAnimationDefinitions;
import net.orca.ocean.entity.custom.OrcaEntity;
import org.joml.Vector3f;

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
		float f = 18.0F;
		float f1 = -8.0F;

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-14.0F, -9.0F, -33.0F, 28.0F, 24.0F, 65.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-14.0F, -9.0F, -34.0F, 28.0F, 20.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(18, 21).addBox(-6.0F, 6.0F, -36.0F, 12.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 9.0F, 0.0F));

		PartDefinition tail = head.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 89).addBox(-6.0F, -7.0F, -2.0F, 12.0F, 13.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, 30.0F));

		PartDefinition fluke = tail.addOrReplaceChild("fluke", CubeListBuilder.create().texOffs(56, 89).addBox(-20.0F, -1.5F, 0.0F, 40.0F, 3.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.5F, 24.0F));

		PartDefinition flipperLeft = head.addOrReplaceChild("flipperLeft", CubeListBuilder.create(), PartPose.offset(14.0F, 15.0F, 0.5F));

		PartDefinition flipperLeft_r1 = flipperLeft.addOrReplaceChild("flipperLeft_r1", CubeListBuilder.create().texOffs(121, 0).addBox(-3.0F, -1.0F, -14.5F, 24.0F, 2.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, 0.0F, -0.5236F, 0.8727F));

		PartDefinition flipperRight = head.addOrReplaceChild("flipperRight", CubeListBuilder.create(), PartPose.offset(-14.0F, 15.0F, 0.5F));

		PartDefinition flipperRight_r1 = flipperRight.addOrReplaceChild("flipperRight_r1", CubeListBuilder.create().texOffs(121, 0).mirror().addBox(-21.0F, -1.0F, -14.5F, 24.0F, 2.0F, 18.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, 0.0F, 0.5236F, -0.8727F));

		PartDefinition dorsalFin = head.addOrReplaceChild("dorsalFin", CubeListBuilder.create().texOffs(0, 22).addBox(-1.0F, -23.0F, 0.0F, 2.0F, 23.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, 10.0F));

		return LayerDefinition.create(meshdefinition, 224, 224);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return head;
	}
	public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.animateWalk(OrcaAnimationDefinitions.swim, pLimbSwing, pLimbSwingAmount,2.0F, 2.5F);
		this.animate(pEntity.swimIdleAnimationState, OrcaAnimationDefinitions.swimIdle, pAgeInTicks);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		if (pEntity.getDeltaMovement().horizontalDistanceSqr() > 1.0E-7D) {
			this.head.xRot += -0.05F - 0.05F * Mth.cos(pAgeInTicks * 0.3F);
		}



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