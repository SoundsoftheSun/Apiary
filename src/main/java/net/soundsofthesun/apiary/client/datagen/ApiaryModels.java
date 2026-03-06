package net.soundsofthesun.apiary.client.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.soundsofthesun.apiary.items.ModItems;

public class ApiaryModels extends FabricModelProvider {
    public ApiaryModels(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blocks) {
        //blocks.createTrivialBlock(ModBlocks.HONEY_EXTRACTOR, TexturedModel.CUBE_TOP_BOTTOM);
    }

    @Override
    public void generateItemModels(ItemModelGenerators items) {
        items.generateFlatItem(ModItems.HIVE_TOOL, ModelTemplates.FLAT_HANDHELD_ITEM);
        items.generateFlatItem(ModItems.HONEY_BUCKET, ModelTemplates.FLAT_ITEM);
        items.generateFlatItem(ModItems.MESH, ModelTemplates.FLAT_ITEM);
    }
}
