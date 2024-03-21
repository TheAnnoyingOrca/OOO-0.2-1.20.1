package net.orca.oceanoverhaul.entity.client;// Made with Blockbench 4.9.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.orca.oceanoverhaul.entity.animations.BigKelpFishAnimationDefinitions;
import net.orca.oceanoverhaul.entity.custom.KelpFishEntity;

public class KelpFishModel<T extends KelpFishEntity> extends HierarchicalModel<T> {
	public final ModelPart body;

	private final ModelPart tail;
	private final ModelPart leftFin;
	private final ModelPart rightFin;


	public KelpFishModel(ModelPart root) {
		this.body = root.getChild("body");
		this.tail = body.getChild("tail");
		this.leftFin = body.getChild("leftFin");
		this.rightFin = body.getChild("rightFin");
	}

	public static LayerDefinition createRockFishBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -3.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(0, 5).addBox(0.0F, -6.0F, -1.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.5F, 0.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, 3.0F));

		PartDefinition leftFin = body.addOrReplaceChild("leftFin", CubeListBuilder.create(), PartPose.offset(1.5F, 0.0F, -1.0F));

		PartDefinition cube_r1 = leftFin.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1F));

		PartDefinition rightFin = body.addOrReplaceChild("rightFin", CubeListBuilder.create(), PartPose.offset(-1.5F, 0.0F, -1.0F));

		PartDefinition cube_r2 = rightFin.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.0F, 0.0F, 0.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1F));
		PartDefinition tail2 = tail.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(3, 3).addBox(0.0F, 1.5F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition leftPelvic = body.addOrReplaceChild("leftPelvic", CubeListBuilder.create().texOffs(5, 2).addBox(2.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.0F, 2.0F));

		PartDefinition rightPelvic = body.addOrReplaceChild("rightPelvic", CubeListBuilder.create().texOffs(5, 2).mirror().addBox(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, 0.0F, 2.0F));
		return LayerDefinition.create(meshdefinition, 32, 32);
	}
	public static LayerDefinition createRonquilBodyLayer(){
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -3.0F, -6.0F, 3.0F, 3.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(0, 6).addBox(0.0F, -4.0F, -2.0F, 0.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition pelvic_r1 = body.addOrReplaceChild("pelvic_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -3.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.5F, 0.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, 5.0F));

		PartDefinition leftFin = body.addOrReplaceChild("leftFin", CubeListBuilder.create(), PartPose.offset(1.5F, -1.0F, -2.0F));

		PartDefinition leftFin_r1 = leftFin.addOrReplaceChild("leftFin_r1", CubeListBuilder.create().texOffs(6, 0).addBox(0.0F, -1.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition rightFin = body.addOrReplaceChild("rightFin", CubeListBuilder.create(), PartPose.offset(-1.5F, -1.0F, -2.0F));

		PartDefinition rightFin_r1 = rightFin.addOrReplaceChild("rightFin_r1", CubeListBuilder.create().texOffs(6, 0).mirror().addBox(-2.0F, -2.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
		PartDefinition tail2 = tail.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(3, 3).addBox(0.0F, 1.5F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition leftPelvic = body.addOrReplaceChild("leftPelvic", CubeListBuilder.create().texOffs(5, 2).addBox(2.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.0F, 2.0F));

		PartDefinition rightPelvic = body.addOrReplaceChild("rightPelvic", CubeListBuilder.create().texOffs(5, 2).mirror().addBox(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, 0.0F, 2.0F));
		return LayerDefinition.create(meshdefinition, 32, 32);
	}
	public static LayerDefinition createSculpinBodyLayer(){
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -3.0F, -2.0F, 3.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(0, 4).addBox(0.0F, -5.0F, 0.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.5F, -1.0F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 7).addBox(0.0F, -1.5F, 0.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, 3.0F));

		PartDefinition leftFin = body.addOrReplaceChild("leftFin", CubeListBuilder.create(), PartPose.offset(1.5F, 0.0F, 0.0F));

		PartDefinition cube_r1 = leftFin.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(9, 0).addBox(0.0F, 0.0F, 1.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 0.0F, 0.0F, 0.1F));

		PartDefinition rightFin = body.addOrReplaceChild("rightFin", CubeListBuilder.create(), PartPose.offset(-1.5F, 0.0F, 0.0F));

		PartDefinition cube_r2 = rightFin.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(9, 0).mirror().addBox(-2.0F, 0.0F, 1.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 0.0F, 0.0F, -0.1F));
		PartDefinition tail2 = tail.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(3, 3).addBox(0.0F, 1.5F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition leftPelvic = body.addOrReplaceChild("leftPelvic", CubeListBuilder.create().texOffs(5, 2).addBox(2.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.0F, 2.0F));

		PartDefinition rightPelvic = body.addOrReplaceChild("rightPelvic", CubeListBuilder.create().texOffs(5, 2).mirror().addBox(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, 0.0F, 2.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}
	public static LayerDefinition createGreenlingBodyLayer(){
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -3.0F, -5.0F, 3.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 5).addBox(0.0F, -5.0F, -3.0F, 0.0F, 7.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(12, 11).addBox(-0.5F, -1.0F, -7.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.5F, 0.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, 3.0F));

		PartDefinition leftFin = body.addOrReplaceChild("leftFin", CubeListBuilder.create(), PartPose.offset(1.5F, 0.0F, -3.0F));

		PartDefinition cube_r1 = leftFin.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, -2.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 2.0F, 0.0F, 0.0F, 0.1F));

		PartDefinition rightFin = body.addOrReplaceChild("rightFin", CubeListBuilder.create(), PartPose.offset(-1.5F, 0.0F, -3.0F));

		PartDefinition cube_r2 = rightFin.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.0F, 0.0F, -2.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 2.0F, 0.0F, 0.0F, -0.1F));
		PartDefinition tail2 = tail.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(3, 3).addBox(0.0F, 1.5F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition leftPelvic = body.addOrReplaceChild("leftPelvic", CubeListBuilder.create().texOffs(5, 2).addBox(2.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.0F, 2.0F));

		PartDefinition rightPelvic = body.addOrReplaceChild("rightPelvic", CubeListBuilder.create().texOffs(5, 2).mirror().addBox(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, 0.0F, 2.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}
	public static LayerDefinition createPerchBodyLayer(){
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(1, 0).addBox(-1.5F, -6.0F, -4.0F, 3.0F, 6.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(0, 10).addBox(0.0F, -8.0F, -2.0F, 0.0F, 10.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(14, 0).addBox(-1.5F, -4.0F, -6.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(10, 11).addBox(0.0F, -3.0F, 0.0F, 0.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 3.0F));

		PartDefinition leftFin = body.addOrReplaceChild("leftFin", CubeListBuilder.create(), PartPose.offset(1.5F, 0.0F, -3.0F));

		PartDefinition cube_r1 = leftFin.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, -2.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 2.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition rightFin = body.addOrReplaceChild("rightFin", CubeListBuilder.create(), PartPose.offset(-1.5F, 0.0F, -3.0F));

		PartDefinition cube_r2 = rightFin.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3.0F, 0.0F, -2.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 2.0F, 0.0F, 0.0F, -0.7854F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}
	public static LayerDefinition createPoacherBodyLayer(){
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -3.0F, -4.0F, 3.0F, 3.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(12, 10).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 7).addBox(0.0F, -4.0F, -2.0F, 0.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(0, 5).addBox(-1.0F, -1.0F, -5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 3).addBox(-1.0F, 0.0F, -5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 0.6545F, 0.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 4).addBox(0.0F, -2.5F, 0.0F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 3.0F));

		PartDefinition leftFin = body.addOrReplaceChild("leftFin", CubeListBuilder.create(), PartPose.offset(1.5F, 0.0F, -1.0F));

		PartDefinition cube_r2 = leftFin.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(10, 0).addBox(0.0F, 0.0F, 0.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1F));

		PartDefinition rightFin = body.addOrReplaceChild("rightFin", CubeListBuilder.create(), PartPose.offset(-1.5F, 0.0F, -1.0F));

		PartDefinition cube_r3 = rightFin.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(10, 0).mirror().addBox(-3.0F, 0.0F, 0.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1F));
		PartDefinition tail2 = tail.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(3, 3).addBox(0.0F, 1.5F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition leftPelvic = body.addOrReplaceChild("leftPelvic", CubeListBuilder.create().texOffs(5, 2).addBox(2.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.0F, 2.0F));

		PartDefinition rightPelvic = body.addOrReplaceChild("rightPelvic", CubeListBuilder.create().texOffs(5, 2).mirror().addBox(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, 0.0F, 2.0F));
		return LayerDefinition.create(meshdefinition, 32, 32);
	}
	public static LayerDefinition createSailfinBodyLayer(){
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -3.0F, -5.0F, 3.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 4).addBox(0.0F, -7.0F, -4.0F, 0.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.5F, 0.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, 3.0F));

		PartDefinition leftFin = body.addOrReplaceChild("leftFin", CubeListBuilder.create(), PartPose.offset(1.5F, 0.0F, -3.0F));

		PartDefinition cube_r1 = leftFin.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, -2.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 2.0F, 0.0F, 0.0F, 0.1F));

		PartDefinition rightFin = body.addOrReplaceChild("rightFin", CubeListBuilder.create(), PartPose.offset(-1.5F, 0.0F, -3.0F));

		PartDefinition cube_r2 = rightFin.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.0F, 0.0F, -2.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 2.0F, 0.0F, 0.0F, -0.1F));
		PartDefinition tail2 = tail.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(3, 3).addBox(0.0F, 1.5F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition leftPelvic = body.addOrReplaceChild("leftPelvic", CubeListBuilder.create().texOffs(5, 2).addBox(2.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.0F, 2.0F));

		PartDefinition rightPelvic = body.addOrReplaceChild("rightPelvic", CubeListBuilder.create().texOffs(5, 2).mirror().addBox(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, 0.0F, 2.0F));
		return LayerDefinition.create(meshdefinition, 32, 32);
	}
	public static LayerDefinition createCabezonBodyLayer(){
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -3.0F, -4.0F, 4.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 5).addBox(0.0F, -5.0F, -1.0F, 0.0F, 7.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(12, 14).addBox(-2.0F, -4.0F, -3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.5F, 0.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, 4.0F));

		PartDefinition leftFin = body.addOrReplaceChild("leftFin", CubeListBuilder.create(), PartPose.offset(2.0F, 0.0F, -1.0F));

		PartDefinition cube_r1 = leftFin.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(13, 0).addBox(0.0F, 0.0F, 0.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.829F));

		PartDefinition rightFin = body.addOrReplaceChild("rightFin", CubeListBuilder.create(), PartPose.offset(-2.0F, 0.0F, -1.0F));

		PartDefinition cube_r2 = rightFin.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(13, 0).mirror().addBox(-3.0F, 0.0F, 0.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}
	public static LayerDefinition createWarbonnetBodyLayer(){
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -3.0F, -6.0F, 3.0F, 3.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(0, 1).addBox(0.0F, -5.0F, -7.0F, 0.0F, 6.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(0, 19).addBox(-1.5F, -4.0F, -5.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -3.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.5F, 0.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, 5.0F));

		PartDefinition leftFin = body.addOrReplaceChild("leftFin", CubeListBuilder.create(), PartPose.offset(1.5F, -1.0F, -2.0F));

		PartDefinition cube_r2 = leftFin.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(6, 0).addBox(0.0F, -1.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition rightFin = body.addOrReplaceChild("rightFin", CubeListBuilder.create(), PartPose.offset(-1.5F, -1.0F, -2.0F));

		PartDefinition cube_r3 = rightFin.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(6, 0).mirror().addBox(-2.0F, -2.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
		PartDefinition tail2 = tail.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(3, 3).addBox(0.0F, 1.5F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition leftPelvic = body.addOrReplaceChild("leftPelvic", CubeListBuilder.create().texOffs(5, 2).addBox(2.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.0F, 2.0F));

		PartDefinition rightPelvic = body.addOrReplaceChild("rightPelvic", CubeListBuilder.create().texOffs(5, 2).mirror().addBox(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, 0.0F, 2.0F));
		return LayerDefinition.create(meshdefinition, 32, 32);
	}
	public static LayerDefinition createLumpBodyLayer(){
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -4.0F, -2.0F, 3.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(0, 4).addBox(0.0F, -5.0F, -1.0F, 0.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 8).addBox(0.0F, -1.5F, 0.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, 3.0F));

		PartDefinition leftFin = body.addOrReplaceChild("leftFin", CubeListBuilder.create(), PartPose.offset(1.5F, 0.0F, 0.0F));

		PartDefinition cube_r1 = leftFin.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(-1, 0).addBox(0.0F, 0.0F, 1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 0.0F, 0.0F, 0.1F));

		PartDefinition rightFin = body.addOrReplaceChild("rightFin", CubeListBuilder.create(), PartPose.offset(-1.5F, 0.0F, 0.0F));

		PartDefinition cube_r2 = rightFin.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(-1, 0).addBox(-1.0F, 0.0F, 1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 0.0F, 0.0F, -0.1F));
		PartDefinition tail2 = tail.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(3, 3).addBox(0.0F, 1.5F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition leftPelvic = body.addOrReplaceChild("leftPelvic", CubeListBuilder.create().texOffs(5, 2).addBox(2.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.0F, 2.0F));

		PartDefinition rightPelvic = body.addOrReplaceChild("rightPelvic", CubeListBuilder.create().texOffs(5, 2).mirror().addBox(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, 0.0F, 2.0F));
		return LayerDefinition.create(meshdefinition, 16, 16);
	}
	public static LayerDefinition createRatfishBodyLayer(){
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -3.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(0, 7).addBox(0.0F, -6.0F, -1.0F, 0.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.5F, -1.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(-2, 3).mirror().addBox(-2.0F, 0.0F, 0.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.5F, 0.0F, 2.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(-2, 3).addBox(0.0F, 0.0F, 0.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 0.0F, 2.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 4).addBox(0.0F, -0.5F, 0.0F, 0.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, 3.0F));

		PartDefinition leftFin = body.addOrReplaceChild("leftFin", CubeListBuilder.create(), PartPose.offset(1.5F, 0.0F, -1.0F));

		PartDefinition cube_r3 = leftFin.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(10, 0).addBox(0.0F, 0.0F, 0.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

		PartDefinition rightFin = body.addOrReplaceChild("rightFin", CubeListBuilder.create(), PartPose.offset(-1.5F, 0.0F, -1.0F));

		PartDefinition cube_r4 = rightFin.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(10, 0).mirror().addBox(-3.0F, 0.0F, 0.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5236F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}
	public static LayerDefinition createCatSharkBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -3.0F, -6.0F, 4.0F, 3.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(12, 15).addBox(0.0F, -5.0F, 0.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 17).addBox(-2.0F, -3.0F, -8.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.0F, -1.0F, 1.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(10, 12).addBox(-1.0F, -1.5F, 0.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.0F, -2.5F, 1.0F, 0.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, 3.0F));

		PartDefinition tail2 = tail.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(0, 7).addBox(0.0F, -3.5F, -1.0F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.0F));

		PartDefinition leftFin = body.addOrReplaceChild("leftFin", CubeListBuilder.create().texOffs(15, 0).addBox(0.0F, 0.0F, 0.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, -3.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition rightFin = body.addOrReplaceChild("rightFin", CubeListBuilder.create().texOffs(15, 0).mirror().addBox(-3.0F, 0.0F, 0.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 0.0F, -3.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition leftPelvic = body.addOrReplaceChild("leftPelvic", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 2.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition rightPelvic = body.addOrReplaceChild("rightPelvic", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.0F, 0.0F, 0.0F, 2.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 0.0F, 1.0F, 0.0F, 0.0F, -0.7854F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}
	public static LayerDefinition createDogfishBodyLayer(){
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -3.0F, -8.0F, 4.0F, 3.0F, 11.0F, new CubeDeformation(0.0F))
				.texOffs(0, 4).addBox(0.0F, -6.0F, -1.0F, 0.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(6, 14).addBox(-1.0F, -0.5F, 0.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.0F, -1.5F, 1.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, 3.0F));

		PartDefinition tail2 = tail.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(0, 11).addBox(0.0F, -3.5F, 0.0F, 0.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 5.0F));

		PartDefinition leftFin = body.addOrReplaceChild("leftFin", CubeListBuilder.create().texOffs(1, 0).addBox(0.0F, 0.0F, 0.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, -3.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition rightFin = body.addOrReplaceChild("rightFin", CubeListBuilder.create().texOffs(1, 0).mirror().addBox(-3.0F, 0.0F, 0.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 0.0F, -3.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition leftPelvic = body.addOrReplaceChild("leftPelvic", CubeListBuilder.create().texOffs(4, 6).addBox(0.0F, 0.0F, 0.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, 2.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition rightPelvic = body.addOrReplaceChild("rightPelvic", CubeListBuilder.create().texOffs(4, 6).mirror().addBox(-2.0F, 0.0F, 0.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 0.0F, 2.0F, 0.0F, 0.0F, -0.7854F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}
	public static LayerDefinition createLancetBodyLayer(){
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -4.0F, -6.0F, 3.0F, 4.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(13, 4).addBox(0.0F, -8.0F, -6.0F, 0.0F, 4.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(20, 3).addBox(-1.0F, -3.0F, -9.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 13).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 2.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(13, 9).addBox(0.0F, -3.5F, 0.0F, 0.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(13, 16).addBox(0.0F, 0.5F, 4.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, 3.0F));

		PartDefinition tail2 = tail.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(0, 19).addBox(0.0F, -4.0F, 0.0F, 0.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 8.0F));

		PartDefinition leftFin = body.addOrReplaceChild("leftFin", CubeListBuilder.create().texOffs(12, 0).addBox(0.0F, 0.0F, 0.0F, 4.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 0.0F, -5.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition rightFin = body.addOrReplaceChild("rightFin", CubeListBuilder.create().texOffs(12, 0).mirror().addBox(-4.0F, 0.0F, 0.0F, 4.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.5F, 0.0F, -5.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition rightPelvic = body.addOrReplaceChild("rightPelvic", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.0F, 0.0F, 0.0F, 2.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.5F, 0.0F, 1.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition leftPelvic = body.addOrReplaceChild("leftPelvic", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 2.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 0.0F, 1.0F, 0.0F, 0.0F, 0.7854F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}
	public static LayerDefinition createLeopardBodyLayer(){
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -5.0F, -8.0F, 6.0F, 5.0F, 11.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.5F, -8.0F, 0.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(10, 16).addBox(-3.0F, 0.0F, -8.0F, 6.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, -1.5F, 0.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(0, 6).addBox(0.0F, -3.5F, 2.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, 3.0F));

		PartDefinition tail2 = tail.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(16, 20).addBox(-0.5F, -5.5F, 0.0F, 1.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 5.0F));

		PartDefinition leftFin = body.addOrReplaceChild("leftFin", CubeListBuilder.create().texOffs(23, 0).addBox(0.0F, -0.5F, 0.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, -3.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition rightFin = body.addOrReplaceChild("rightFin", CubeListBuilder.create().texOffs(23, 0).mirror().addBox(-4.0F, -0.5F, 0.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, 0.0F, -3.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition leftPelvic = body.addOrReplaceChild("leftPelvic", CubeListBuilder.create().texOffs(3, 0).addBox(0.0F, 0.0F, 0.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 2.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition rightPelvic = body.addOrReplaceChild("rightPelvic", CubeListBuilder.create().texOffs(3, 0).mirror().addBox(-2.0F, 0.0F, 0.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, 0.0F, 2.0F, 0.0F, 0.0F, -0.7854F));

		return LayerDefinition.create(meshdefinition, 48, 48);
	}
	public static LayerDefinition createLingcodBodyLayer(){
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -3.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(12, 5).addBox(0.0F, -5.0F, -1.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(12, 11).addBox(-1.5F, -2.0F, -4.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 9).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(0, 12).addBox(0.0F, -3.5F, 0.0F, 0.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, 3.0F));

		PartDefinition tail2 = tail.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.5F, 0.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 6.0F));

		PartDefinition leftFin = body.addOrReplaceChild("leftFin", CubeListBuilder.create(), PartPose.offset(1.5F, 0.0F, 0.0F));

		PartDefinition cube_r1 = leftFin.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(9, 0).addBox(0.0F, 0.0F, 0.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition rightFin = body.addOrReplaceChild("rightFin", CubeListBuilder.create(), PartPose.offset(-1.5F, 0.0F, 0.0F));

		PartDefinition cube_r2 = rightFin.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(9, 0).mirror().addBox(-3.0F, 0.0F, 0.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}
	public static LayerDefinition createSnipeBodyLayer(){
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(12, 0).addBox(-1.0F, -1.5F, -3.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.0F, -1.5F, 0.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.0F, -1.5F, -9.0F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(0, 11).addBox(0.0F, -2.5F, 0.0F, 0.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.5F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 4).addBox(0.0F, -1.5F, 0.0F, 0.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 4.0F));

		PartDefinition tail2 = tail.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.5F, 0.0F, 0.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 8.0F));

		PartDefinition leftFin = body.addOrReplaceChild("leftFin", CubeListBuilder.create(), PartPose.offset(1.0F, 0.5F, 0.0F));

		PartDefinition cube_r1 = leftFin.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(10, 6).addBox(0.0F, 0.0F, 0.0F, 4.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition rightFin = body.addOrReplaceChild("rightFin", CubeListBuilder.create(), PartPose.offset(-1.0F, 0.5F, 0.0F));

		PartDefinition cube_r2 = rightFin.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(10, 6).mirror().addBox(-4.0F, 0.0F, 0.0F, 4.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}
	public static LayerDefinition createWolfBodyLayer(){
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -4.0F, -7.0F, 3.0F, 4.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(13, 10).addBox(0.0F, -6.0F, -3.0F, 0.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 14).addBox(-1.0F, -1.5F, 0.0F, 2.0F, 3.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(13, 5).addBox(0.0F, -3.5F, 0.0F, 0.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, 3.0F));

		PartDefinition tail2 = tail.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(0, 4).addBox(0.0F, -2.5F, 0.0F, 0.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 9.0F));

		PartDefinition leftFin = body.addOrReplaceChild("leftFin", CubeListBuilder.create(), PartPose.offset(1.5F, 0.0F, -3.0F));

		PartDefinition cube_r1 = leftFin.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition rightFin = body.addOrReplaceChild("rightFin", CubeListBuilder.create(), PartPose.offset(-1.5F, 0.0F, -3.0F));

		PartDefinition cube_r2 = rightFin.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3.0F, 0.0F, 0.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}


	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.body.xRot = headPitch * ((float)Math.PI / 170F);
		this.body.yRot = netHeadYaw * ((float)Math.PI / 170F);
		float f = 1.0F;
		float degree = 2f;
		if (!entity.isInWater()) {
			f = 1.5F;
		}
		if (entity.getBodyType() == "ratfish"){
			this.leftFin.zRot = -f * 0.80F * Mth.sin(0.2F * ageInTicks);
			this.rightFin.zRot = -f * 0.80F * Mth.sin(-0.2F * ageInTicks);
			this.tail.yRot = -f * 0.45F * Mth.cos(+1.0F - 0.2F * ageInTicks);
			this.body.xRot = headPitch * ((float)Math.PI / 160F);
			this.body.yRot = netHeadYaw * ((float)Math.PI / 160F);

		} else if (entity.getBodyType() == "ronquil"){
			this.tail.yRot = -f * 0.45F * Mth.sin(+1.0F - 0.6F * ageInTicks);
			this.body.xRot = headPitch * ((float)Math.PI / 160F);
			this.body.yRot = netHeadYaw * ((float)Math.PI / 160F);
			this.leftFin.yRot = -f * 0.15F * Mth.sin(0.3F * ageInTicks);
			this.rightFin.yRot = -f * 0.15F * Mth.sin(-0.3F * ageInTicks);
		} else if (entity.getBodyType() == "warbonnet") {
			this.tail.yRot = -f * 0.45F * Mth.sin(+1.0F - 0.6F * ageInTicks);
			this.body.xRot = headPitch * ((float) Math.PI / 160F);
			this.body.yRot = netHeadYaw * ((float) Math.PI / 160F);
			this.leftFin.yRot = -f * 0.15F * Mth.sin(0.3F * ageInTicks);
			this.rightFin.yRot = -f * 0.15F * Mth.sin(-0.3F * ageInTicks);
		} else if (entity.getBodyType() == "catshark"){
			this.root().getAllParts().forEach(ModelPart::resetPose);
			this.animateWalk(BigKelpFishAnimationDefinitions.swim, limbSwing, limbSwingAmount,2.0F, 2.5F);
			this.animate(entity.swimIdleAnimationState, BigKelpFishAnimationDefinitions.swimIdle, ageInTicks);
			this.body.xRot = headPitch * ((float)Math.PI / 160F);
			this.body.yRot = netHeadYaw * ((float)Math.PI / 160F);
		} else if (entity.getBodyType() == "dogfish"){
			this.root().getAllParts().forEach(ModelPart::resetPose);
			this.animateWalk(BigKelpFishAnimationDefinitions.swim, limbSwing, limbSwingAmount,2.0F, 2.5F);
			this.animate(entity.swimIdleAnimationState, BigKelpFishAnimationDefinitions.swimIdle, ageInTicks);
			this.body.xRot = headPitch * ((float)Math.PI / 160F);
			this.body.yRot = netHeadYaw * ((float)Math.PI / 160F);
		} else if (entity.getBodyType() == "lancet"){
			this.root().getAllParts().forEach(ModelPart::resetPose);
			this.animateWalk(BigKelpFishAnimationDefinitions.swim, limbSwing, limbSwingAmount,2.0F, 2.5F);
			this.animate(entity.swimIdleAnimationState, BigKelpFishAnimationDefinitions.swimIdle, ageInTicks);
			this.body.xRot = headPitch * ((float)Math.PI / 160F);
			this.body.yRot = netHeadYaw * ((float)Math.PI / 160F);
		} else if (entity.getBodyType() == "leopard"){
			this.root().getAllParts().forEach(ModelPart::resetPose);
			this.animateWalk(BigKelpFishAnimationDefinitions.swim, limbSwing, limbSwingAmount,2.0F, 2.5F);
			this.animate(entity.swimIdleAnimationState, BigKelpFishAnimationDefinitions.swimIdle, ageInTicks);
			this.body.xRot = headPitch * ((float)Math.PI / 160F);
			this.body.yRot = netHeadYaw * ((float)Math.PI / 160F);
		} else if (entity.getBodyType() == "lingcod"){
			this.root().getAllParts().forEach(ModelPart::resetPose);
			this.animateWalk(BigKelpFishAnimationDefinitions.swim, limbSwing, limbSwingAmount,2.0F, 2.5F);
			this.animate(entity.swimIdleAnimationState, BigKelpFishAnimationDefinitions.swimIdle, ageInTicks);
			this.body.xRot = headPitch * ((float)Math.PI / 160F);
			this.body.yRot = netHeadYaw * ((float)Math.PI / 160F);
		} else if (entity.getBodyType() == "snipe"){
			this.root().getAllParts().forEach(ModelPart::resetPose);
			this.animateWalk(BigKelpFishAnimationDefinitions.swim, limbSwing, limbSwingAmount,2.0F, 2.5F);
			this.animate(entity.swimIdleAnimationState, BigKelpFishAnimationDefinitions.swimIdle, ageInTicks);
			this.body.xRot = headPitch * ((float)Math.PI / 160F);
			this.body.yRot = netHeadYaw * ((float)Math.PI / 160F);
		} else if (entity.getBodyType() == "wolf"){
			this.root().getAllParts().forEach(ModelPart::resetPose);
			this.animateWalk(BigKelpFishAnimationDefinitions.swim, limbSwing, limbSwingAmount,2.0F, 2.5F);
			this.animate(entity.swimIdleAnimationState, BigKelpFishAnimationDefinitions.swimIdle, ageInTicks);
			this.body.xRot = headPitch * ((float)Math.PI / 160F);
			this.body.yRot = netHeadYaw * ((float)Math.PI / 160F);
		} else {
			//this.body.yRot += -0.05F - 0.05F * Mth.cos(ageInTicks * 0.3F);
			this.tail.yRot = -f * 0.45F * Mth.sin(+1.0F - 0.6F * ageInTicks);

			this.leftFin.zRot = -f * 0.15F * Mth.sin(0.3F * ageInTicks);
			this.rightFin.zRot = -f * 0.15F * Mth.sin(-0.3F * ageInTicks);
		}
	}



	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {

		return this.body;
	}
}