package net.soundsofthesun.beekeepingage.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.resources.Identifier;
import net.soundsofthesun.beekeepingage.block.ModBlocks;
import net.soundsofthesun.beekeepingage.entity.flowergolem.FlowerGolemModel;
import net.soundsofthesun.beekeepingage.entity.flowergolem.FlowerGolemRenderer;
import net.soundsofthesun.beekeepingage.entity.ModEntities;

public class BKAClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        FluidRenderHandlerRegistry.INSTANCE.register(ModBlocks.HONEY_SOURCE, ModBlocks.HONEY_FLOWING, new SimpleFluidRenderHandler(
                Identifier.withDefaultNamespace("block/honey_block_top"),
                Identifier.withDefaultNamespace("block/honey_block_side"),
                Identifier.withDefaultNamespace("block/honey_block_bottom")));

        BlockRenderLayerMap.putFluids(ChunkSectionLayer.TRANSLUCENT, ModBlocks.HONEY_SOURCE, ModBlocks.HONEY_FLOWING);

        EntityModelLayerRegistry.registerModelLayer(FlowerGolemModel.LAYER_LOCATION, FlowerGolemModel::createBodyLayer);
        EntityRendererRegistry.register(ModEntities.FLOWER_GOLEM, FlowerGolemRenderer::new);
    }
}
