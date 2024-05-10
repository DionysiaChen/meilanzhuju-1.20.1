package net.dionysiachen.meilanzhuju.entity.client;// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.dionysiachen.meilanzhuju.entity.MuskDeerEntity;
import net.dionysiachen.meilanzhuju.entity.animations.ModAnimationDefinitions;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class MuskDeerModel <T extends MuskDeerEntity> extends HierarchicalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    private final ModelPart musk_deer;
    private final ModelPart head;

    public MuskDeerModel (ModelPart root) {

        this.musk_deer = root.getChild("bone");
        this.head = musk_deer.getChild("head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(-14.0F, 24.0F, 16.0F));

        PartDefinition head = bone.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(-3.0F, -22.0F, -29.0F));

        PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(-6, -6).addBox(-6.0F, -32.0F, -31.0F, 6.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 22.0F, 29.0F, 0.1745F, 0.0F, 0.0F));

        PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(-2, -2).addBox(-5.0F, -30.0F, -29.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 23.0F, 23.0F, 0.1745F, 0.0F, 0.0F));

        PartDefinition ears = head.addOrReplaceChild("ears", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -4.3933F, -0.2969F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-7.0F, -4.3933F, -0.2969F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -3.0F, -2.0F));

        PartDefinition neck = bone.addOrReplaceChild("neck", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r3 = neck.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(-3, -3).addBox(-6.0F, -5.2492F, -5.1278F, 6.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -16.0F, -23.0F, 0.7418F, 0.0F, 0.0F));

        PartDefinition cube_r4 = neck.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(-3, -3).addBox(-6.0F, -27.0F, -28.0F, 6.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

        PartDefinition body = bone.addOrReplaceChild("body", CubeListBuilder.create().texOffs(-16, -16).addBox(-8.0F, -19.0F, -25.0F, 9.0F, 9.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition left_front_leg = bone.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(-1, -1).addBox(-1.0F, -11.0F, -24.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(-1, -1).addBox(-1.0F, -6.0F, -24.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition right_front_leg = bone.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(-1, -1).addBox(-8.0F, -11.0F, -24.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(-1, -1).addBox(-8.0F, -6.0F, -24.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition left_back_leg = bone.addOrReplaceChild("left_back_leg", CubeListBuilder.create().texOffs(-1, -1).addBox(-1.0F, -8.0F, -11.0F, 2.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(-3, -3).addBox(-1.5F, -14.0F, -13.0F, 3.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition right_back_leg = bone.addOrReplaceChild("right_back_leg", CubeListBuilder.create().texOffs(-1, -1).addBox(-15.0F, -8.0F, 5.0F, 2.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(-3, -3).addBox(-15.5F, -14.0F, 3.0F, 3.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, 0.0F, -16.0F));

        PartDefinition tail = bone.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r5 = tail.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -6.2127F, -6.1311F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.0F, 0.0F, 0.3491F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(MuskDeerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(entity, netHeadYaw, headPitch, ageInTicks);
        this.animateWalk(ModAnimationDefinitions.WALK, limbSwing, limbSwingAmount,2f,2.5f);
        this.animate(entity.idleAnimationState, ModAnimationDefinitions.IDLE, ageInTicks, 1f);

    }

    private void applyHeadRotation(MuskDeerEntity pEntity, float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
        pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
        pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

        this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        musk_deer.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return musk_deer;
    }
}