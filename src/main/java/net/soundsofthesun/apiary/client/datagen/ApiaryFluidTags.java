package net.soundsofthesun.apiary.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import net.soundsofthesun.apiary.Apiary;
import net.soundsofthesun.apiary.blocks.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ApiaryFluidTags extends FabricTagProvider.FluidTagProvider {
    public ApiaryFluidTags(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    public static final TagKey<Fluid> HONEY_TAG = TagKey.create(Registries.FLUID, Apiary.id("honey_tag"));

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        valueLookupBuilder(HONEY_TAG)
                .add(ModBlocks.HONEY_FLOWING)
                .add(ModBlocks.HONEY_SOURCE);
    }
}
