package net.soundsofthesun.beekeepingage.entity.flowergolem;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.Identifier;
import net.soundsofthesun.beekeepingage.BKA;
import org.jspecify.annotations.NonNull;

public class FlowerGolemRenderer extends LivingEntityRenderer<FlowerGolem, FlowerGolemRenderState, FlowerGolemModel> {
    public FlowerGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new FlowerGolemModel(context.bakeLayer(FlowerGolemModel.LAYER_LOCATION)), 1F);
    }

    @Override
    public @NonNull Identifier getTextureLocation(FlowerGolemRenderState renderState) {
        return BKA.id("textures/entity/flower_golem/flower_golem.png");
    }

    @Override
    public FlowerGolemRenderState createRenderState() {
        return new FlowerGolemRenderState();
    }

    @Override
    public void extractRenderState(FlowerGolem livingEntity, FlowerGolemRenderState livingEntityRenderState, float f) {
        super.extractRenderState(livingEntity, livingEntityRenderState, f);
        livingEntityRenderState.offerAnimationState.copyFrom(livingEntity.offerAnimationState);
        livingEntityRenderState.shakeAnimationState.copyFrom(livingEntity.shakeAnimationState);
        livingEntityRenderState.rotateAnimationState.copyFrom(livingEntity.rotateAnimationState);
        livingEntityRenderState.walkAnimationState.copyFrom(livingEntity.walkAnimationState);
    }
}
