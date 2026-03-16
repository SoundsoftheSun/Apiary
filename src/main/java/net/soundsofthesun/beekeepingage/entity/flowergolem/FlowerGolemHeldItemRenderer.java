package net.soundsofthesun.beekeepingage.entity.flowergolem;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import org.jspecify.annotations.NonNull;

public class FlowerGolemHeldItemRenderer extends RenderLayer<FlowerGolemRenderState, FlowerGolemModel> {
    public FlowerGolemHeldItemRenderer(RenderLayerParent<FlowerGolemRenderState, FlowerGolemModel> renderer) {
        super(renderer);
    }

    @Override
    public void submit(@NonNull PoseStack poseStack, @NonNull SubmitNodeCollector nodeCollector, int packedLight, FlowerGolemRenderState renderState, float yRot, float xRot) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.XP.rotationDegrees(180F));
        poseStack.scale(0.85F, 0.85F, 0.85F);
        poseStack.translate(0, -0.7, 0.5);
        renderState.itemRenderState.submit(poseStack, nodeCollector, packedLight, OverlayTexture.NO_OVERLAY, 0);
        poseStack.popPose();
    }
}
