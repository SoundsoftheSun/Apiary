package net.soundsofthesun.beekeepingage.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.soundsofthesun.beekeepingage.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class BKALootTables extends FabricBlockLootTableProvider {
    public BKALootTables(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        dropSelf(ModBlocks.HONEY_EXTRACTOR);
        dropSelf(ModBlocks.DRIP_PAN);
        dropSelf(ModBlocks.HONEYCOMB_STAIRS);
        dropSelf(ModBlocks.HONEYCOMB_WALL);
        dropSelf(ModBlocks.HONEYCOMB_SLAB);
    }
}
