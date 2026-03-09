package net.soundsofthesun.apiary.client;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.soundsofthesun.apiary.client.datagen.*;
import net.soundsofthesun.apiary.client.datagen.lang.EnglishProvider;
import net.soundsofthesun.apiary.items.ModItems;

public class ApiaryDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(EnglishProvider::new);
        pack.addProvider(ApiaryModels::new);
        pack.addProvider(ApiaryLootTables::new);
        pack.addProvider(ApiaryRecipes::new);
        pack.addProvider(ApiaryBlockTags::new);
        pack.addProvider(ApiaryFluidTags::new);
        pack.addProvider(ApiaryItemTags::new);
        pack.addProvider(ApiaryRegistries::new);
        pack.addProvider(ApiaryAdvancements::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.TRIM_PATTERN, ModItems::bootstrapTrims);
    }
}
