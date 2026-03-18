package net.soundsofthesun.beekeepingage.entity.flowergolem;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemDisplayContext;
import net.soundsofthesun.beekeepingage.BKA;
import org.jspecify.annotations.NonNull;

public class FlowerGolemRenderer extends LivingEntityRenderer<FlowerGolem, FlowerGolemRenderState, FlowerGolemModel> {
    public FlowerGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new FlowerGolemModel(context.bakeLayer(FlowerGolemModel.LAYER_LOCATION)), 0.5F);
        itemModel = context.getItemModelResolver();
        this.addLayer(new FlowerGolemHeldItemRenderer(this));
    }

    private final ItemModelResolver itemModel;

    @Override
    public @NonNull Identifier getTextureLocation(FlowerGolemRenderState renderState) {
        return BKA.id("textures/entity/flower_golem/flower_golem.png");
    }

    @Override
    public FlowerGolemRenderState createRenderState() {
        return new FlowerGolemRenderState();
    }

    @Override
    public void extractRenderState(FlowerGolem golem, FlowerGolemRenderState state, float f) {
        super.extractRenderState(golem, state, f);
        state.offerAnimationState.copyFrom(golem.offerAnimationState);
        state.shakeAnimationState.copyFrom(golem.shakeAnimationState);
        state.rotateAnimationState.copyFrom(golem.rotateAnimationState);
        state.walkAnimationState.copyFrom(golem.walkAnimationState);
        itemModel.updateForLiving(state.itemRenderState, golem.getHeldFlower(), ItemDisplayContext.FIXED, golem);
    }

    @Override
    protected boolean shouldShowName(FlowerGolem livingEntity, double d) {
        return false;
    }
}
