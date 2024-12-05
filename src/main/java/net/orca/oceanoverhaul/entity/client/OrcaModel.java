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
	private final ModelPart body;
	private final ModelPart tail;
	private final ModelPart fluke;


	public OrcaModel(ModelPart root) {
		this.head = root.getChild("head");
		this.body = head.getChild("body");
		this.tail = body.getChild("tail");
		this.fluke = tail.getChild("fluke");

	}

	public static LayerDefinition createBodyLayer() {


		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = head.addOrReplaceChild("body", CubeListBuilder.create().texOffs(8, 0).addBox(-10.0F, -22.0F, -29.5F, 20.0F, 22.0F, 59.0F, new CubeDeformation(0.0F))
				.texOffs(0, 99).addBox(-4.0F, -8.0F, -33.5F, 8.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(87, 81).addBox(-6.0F, -4.5F, -3.0F, 12.0F, 9.0F, 25.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.5F, 29.5F));

		PartDefinition fluke = tail.addOrReplaceChild("fluke", CubeListBuilder.create().texOffs(0, 81).addBox(-20.0F, -1.0F, 0.0F, 40.0F, 2.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, 19.0F));

		PartDefinition dorsalFin = body.addOrReplaceChild("dorsalFin", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -19.0F, 0.0F, 2.0F, 19.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -22.0F, 7.5F));

		PartDefinition flipperLeft = body.addOrReplaceChild("flipperLeft", CubeListBuilder.create(), PartPose.offset(10.0F, 0.0F, -8.0F));

		PartDefinition flipperLeft_r1 = flipperLeft.addOrReplaceChild("flipperLeft_r1", CubeListBuilder.create().texOffs(115, 0).addBox(-1.0F, -1.0F, -1.0F, 23.0F, 2.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, 0.0F, -0.4363F, 0.7854F));

		PartDefinition flipperRight = body.addOrReplaceChild("flipperRight", CubeListBuilder.create(), PartPose.offset(-10.0F, 0.0F, -8.0F));

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
				this.body.xRot += -0.05F - 0.05F * Mth.cos(pAgeInTicks * 0.2F);
				this.tail.yRot = -(pEntity.tilt * ((float) Math.PI / 180F));
				this.fluke.yRot = -(pEntity.tilt * ((float) Math.PI / 180F));
				this.tail.xRot =-0.2F * Mth.cos(+2.0F - pAgeInTicks * 0.2F)+(-(pHeadPitch * ((float) Math.PI / 180F)/2));
				this.fluke.xRot = -0.4F * Mth.cos(+4.0F - pAgeInTicks * 0.2F) + (-(pHeadPitch * ((float) Math.PI / 180F)/2));
				//

			}


		} else {
			this.tail.xRot = -(pHeadPitch * ((float) Math.PI / 180F)/2);
			this.fluke.xRot = -(pHeadPitch * ((float) Math.PI / 180F)/2);
		}


		//if (pEntity.getDeltaMovement().horizontalDistanceSqr() > 1.0E-7D) {
			//this.head.xRot += -0.05F - 0.05F * Mth.cos(pAgeInTicks * 2.5F);
		//}

		}
	//@Override
	//public void setCustomAnimations (T animatable, long instanceId, AnimationState<T> animationState) { if (!this.turnsHead)
		//return;
		//GeoBone head = (GeoBone) getAnimation Processor().getBone("Head");
		//GeoBone body = (GeoBone) getAnimationProcessor().getBone ("Body");
		//GeoBone legC = (GeoBone) getAnimation Processor().getBone("LegControl");
		//GeoBone neck = (GeoBone) getAnimation Processor().getBone("Neck");
		//if (head != null && body != null && legC != null && ! animatable.isVehicle()){ EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA); float headOffset = body.getRotX() * 1;

		//float offsetYaw = entityData.netHeadYaw() * Mth.DEG_TO_RAD + headOffset; offsetYaw = (float) Mth.clamp (offsetYaw, -0.7, 0.7)
		//body.setRotY(offsetYaw);
		//legC.setRotY(offsetYaw);
		//offsetYaw = (float) Mth.clamp (offsetYaw, -0.4, 0.4);

		//head.setRotY(offsetYaw);
		//neck.setRotY(offsetYaw);
		//} else if (animatable.is Vehicle()) {
		//	EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
		//} float headOffset = body.getRotX() * 1;
		//float offSet Yaw = entityData.net Head Yaw() * Mth.DEG_TO_RAD + headOffset;
		//offsetYaw = (float) Mth.clamp (offsetYaw, -0.7, 0.7);
		//body.setRotY(offsetYaw);
		//legC.setRotY(offsetYaw);
		//LivingEntity entity = animatable.getControlling Passenger();
		//if (entity != null) {
		//	offsetYaw = (float) Mth.clamp (offsetYaw, -0.4, 0.4);
		//} head.setRotX((float) (entity.getLookAngle().y)); neck.setRotX(((float) entity.getLookAngle().y));
	//}

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