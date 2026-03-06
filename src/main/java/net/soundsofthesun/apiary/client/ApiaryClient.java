package net.soundsofthesun.apiary.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.resources.Identifier;
import net.soundsofthesun.apiary.blocks.ModBlockEntities;
import net.soundsofthesun.apiary.blocks.ModBlocks;
import net.soundsofthesun.apiary.blocks.extractor.ExtractorBlockEntityRenderer;

public class ApiaryClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        FluidRenderHandlerRegistry.INSTANCE.register(ModBlocks.HONEY_SOURCE, ModBlocks.HONEY_FLOWING, new SimpleFluidRenderHandler(
                Identifier.withDefaultNamespace("block/honey_block_top"),
                Identifier.withDefaultNamespace("block/honey_block_side"),
                Identifier.withDefaultNamespace("block/honey_block_bottom")));

        BlockRenderLayerMap.putFluids(ChunkSectionLayer.TRANSLUCENT, ModBlocks.HONEY_SOURCE, ModBlocks.HONEY_FLOWING);

        BlockEntityRenderers.register(ModBlockEntities.HONEY_EXTRACTOR_ENTITY, ExtractorBlockEntityRenderer::new);
    }
}
