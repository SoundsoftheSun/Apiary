package net.soundsofthesun.beekeepingage.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import net.soundsofthesun.beekeepingage.BKA;
import net.soundsofthesun.beekeepingage.block.ModBlocks;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class BKAFluidTags extends FabricTagProvider.FluidTagProvider {
    public BKAFluidTags(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    public static final TagKey<Fluid> HONEY_TAG = TagKey.create(Registries.FLUID, BKA.id("honey_tag"));

    @Override
    protected void addTags(HolderLookup.@NonNull Provider provider) {
        valueLookupBuilder(HONEY_TAG)
                .add(ModBlocks.HONEY_FLOWING)
                .add(ModBlocks.HONEY_SOURCE);
    }
}
