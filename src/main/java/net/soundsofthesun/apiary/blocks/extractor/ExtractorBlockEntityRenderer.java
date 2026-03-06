package net.soundsofthesun.apiary.blocks.extractor;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public class ExtractorBlockEntityRenderer implements BlockEntityRenderer<ExtractorBlockEntity, ExtractorBlockEntityRenderState> {

    public ExtractorBlockEntityRenderer(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public ExtractorBlockEntityRenderState createRenderState() {
        return new ExtractorBlockEntityRenderState();
    }

    @Override
    public void extractRenderState(ExtractorBlockEntity blockEntity, ExtractorBlockEntityRenderState renderState, float partialTick, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {

    }

    @Override
    public void submit(ExtractorBlockEntityRenderState renderState, PoseStack poseStack, SubmitNodeCollector nodeCollector, CameraRenderState cameraRenderState) {

    }


}
