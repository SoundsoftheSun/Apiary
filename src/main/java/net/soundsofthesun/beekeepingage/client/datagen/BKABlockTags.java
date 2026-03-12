package net.soundsofthesun.beekeepingage.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.soundsofthesun.beekeepingage.block.ModBlocks;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class BKABlockTags extends FabricTagProvider.BlockTagProvider {
    public BKABlockTags(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider provider) {
        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.HONEY_EXTRACTOR)
                .add(ModBlocks.DRIP_PAN)
                .setReplace(false);

        valueLookupBuilder(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
                .add(ModBlocks.HONEY_EXTRACTOR)
                .add(ModBlocks.DRIP_PAN)
                .setReplace(false);
    }

}
