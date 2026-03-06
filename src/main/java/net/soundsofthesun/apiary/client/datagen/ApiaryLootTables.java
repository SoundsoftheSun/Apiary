package net.soundsofthesun.apiary.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.soundsofthesun.apiary.blocks.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ApiaryLootTables extends FabricBlockLootTableProvider {
    public ApiaryLootTables(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        dropSelf(ModBlocks.HONEY_EXTRACTOR);
        dropSelf(ModBlocks.DRIP_PAN);
    }
}
