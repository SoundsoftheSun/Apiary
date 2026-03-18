package net.soundsofthesun.beekeepingage.entity.flowergolem;

import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.soundsofthesun.beekeepingage.BKA;

public class FlowerGolemModel extends EntityModel<FlowerGolemRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(BKA.id("flower_golem"), "main");
    private final ModelPart head;
    private final ModelPart torso;
    private final ModelPart left_arm;
    private final ModelPart right_arm;
    private final ModelPart left_leg;
    private final ModelPart right_leg;

    private final KeyframeAnimation walkAnimation;
    private final KeyframeAnimation rotateAnimation;
    private final KeyframeAnimation shakeAnimation;
    private final KeyframeAnimation offerAnimation;

    public FlowerGolemModel(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.torso = root.getChild("torso");
        this.left_arm = root.getChild("left_arm");
        this.right_arm = root.getChild("right_arm");
        this.left_leg = root.getChild("left_leg");
        this.right_leg = root.getChild("right_leg");

        this.walkAnimation = FlowerGolemAnimations.walk.bake(root);
        this.rotateAnimation = FlowerGolemAnimations.rotate.bake(root);
        this.shakeAnimation = FlowerGolemAnimations.shake.bake(root);
        this.offerAnimation = FlowerGolemAnimations.offer.bake(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, -8.0F, -4.0F, 18.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(15, 22).addBox(-1.0F, -2.0F, -5.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.0F, 0.0F));

        PartDefinition torso = partdefinition.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(12, 32).addBox(-6.0F, -4.0F, -4.0F, 12.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(26, 21).addBox(-2.0F, -8.0F, -1.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));

        PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 34).addBox(0.0F, -2.0F, -2.0F, 2.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 15.0F, 0.0F));

        PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(52, 34).addBox(-2.0F, -2.0F, -2.0F, 2.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 15.0F, 0.0F));

        PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 54).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 20.0F, 0.0F));

        PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(48, 54).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 20.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(FlowerGolemRenderState renderState) {
        super.setupAnim(renderState);
        this.applyHeadRotation(renderState, renderState.yRot, renderState.xRot);

        this.walkAnimation.applyWalk(renderState.walkAnimationPos, renderState.walkAnimationSpeed, 10F, 1F);
        this.rotateAnimation.apply(renderState.rotateAnimationState, renderState.ageInTicks, 1F);
        this.shakeAnimation.apply(renderState.shakeAnimationState, renderState.ageInTicks, 1F);
        this.offerAnimation.apply(renderState.offerAnimationState, renderState.ageInTicks, 1F);
    }

    private void applyHeadRotation(FlowerGolemRenderState renderState, float yRot, float xRot) {
        yRot = Mth.clamp(yRot, -30.0F, 30.0F);
        xRot = Mth.clamp(xRot, -25.0F, 45.0F);

        this.head.yRot = yRot * (float) (Math.PI / 180.0);
        this.head.xRot = xRot * (float) (Math.PI / 180.0);
    }

}
