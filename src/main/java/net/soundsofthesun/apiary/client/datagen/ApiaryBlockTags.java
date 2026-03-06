package net.soundsofthesun.apiary.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.soundsofthesun.apiary.blocks.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ApiaryBlockTags extends FabricTagProvider.BlockTagProvider {
    public ApiaryBlockTags(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.HONEY_EXTRACTOR)
                .add(ModBlocks.DRIP_PAN)
                .setReplace(false);
    }

}
