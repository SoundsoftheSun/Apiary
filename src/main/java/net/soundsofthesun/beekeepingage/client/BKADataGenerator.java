package net.soundsofthesun.beekeepingage.client;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.soundsofthesun.beekeepingage.client.datagen.*;
import net.soundsofthesun.beekeepingage.client.datagen.lang.EnglishProvider;
import net.soundsofthesun.beekeepingage.items.ModItems;

public class BKADataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(EnglishProvider::new);
        pack.addProvider(BKAModels::new);
        pack.addProvider(BKALootTables::new);
        pack.addProvider(BKARecipes::new);
        pack.addProvider(BKABlockTags::new);
        pack.addProvider(BKAFluidTags::new);
        pack.addProvider(BKAItemTags::new);
        pack.addProvider(BKARegistries::new);
        pack.addProvider(BKAAdvancements::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.TRIM_PATTERN, ModItems::bootstrapTrims);
    }
}
