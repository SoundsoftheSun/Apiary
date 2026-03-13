package net.soundsofthesun.beekeepingage.client.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.world.level.block.Blocks;
import net.soundsofthesun.beekeepingage.block.ModBlocks;
import net.soundsofthesun.beekeepingage.items.ModItems;
import org.jspecify.annotations.NonNull;

public class BKAModels extends FabricModelProvider {
    public BKAModels(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(@NonNull BlockModelGenerators blocks) {
        blocks.family(Blocks.HONEYCOMB_BLOCK)
                .slab(ModBlocks.HONEYCOMB_SLAB)
                .stairs(ModBlocks.HONEYCOMB_STAIRS)
                .wall(ModBlocks.HONEYCOMB_WALL);
    }

    @Override
    public void generateItemModels(ItemModelGenerators items) {
        items.generateFlatItem(ModItems.HIVE_TOOL, ModelTemplates.FLAT_HANDHELD_ITEM);
        items.generateFlatItem(ModItems.HONEY_BUCKET, ModelTemplates.FLAT_ITEM);
        items.generateFlatItem(ModItems.MESH, ModelTemplates.FLAT_ITEM);
        items.generateFlatItem(ModItems.BEEKEEPER_SMITHING_TEMPLATE, ModelTemplates.FLAT_ITEM);
    }
}
