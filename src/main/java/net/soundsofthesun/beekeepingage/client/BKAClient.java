package net.soundsofthesun.beekeepingage.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.resources.Identifier;
import net.soundsofthesun.beekeepingage.block.ModBlocks;

public class BKAClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        FluidRenderHandlerRegistry.INSTANCE.register(ModBlocks.HONEY_SOURCE, ModBlocks.HONEY_FLOWING, new SimpleFluidRenderHandler(
                Identifier.withDefaultNamespace("block/honey_block_top"),
                Identifier.withDefaultNamespace("block/honey_block_side"),
                Identifier.withDefaultNamespace("block/honey_block_bottom")));

        BlockRenderLayerMap.putFluids(ChunkSectionLayer.TRANSLUCENT, ModBlocks.HONEY_SOURCE, ModBlocks.HONEY_FLOWING);
    }
}
