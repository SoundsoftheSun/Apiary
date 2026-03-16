package net.soundsofthesun.beekeepingage.entity.flowergolem;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.entity.AnimationState;

public class FlowerGolemRenderState extends LivingEntityRenderState {
    final ItemStackRenderState itemRenderState = new ItemStackRenderState();
    public final AnimationState walkAnimationState = new AnimationState();
    public final AnimationState rotateAnimationState = new AnimationState();
    public final AnimationState shakeAnimationState = new AnimationState();
    public final AnimationState offerAnimationState = new AnimationState();
}
