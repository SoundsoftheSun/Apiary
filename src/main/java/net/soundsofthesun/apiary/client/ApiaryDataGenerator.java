package net.soundsofthesun.apiary.client;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.soundsofthesun.apiary.client.datagen.*;
import net.soundsofthesun.apiary.client.datagen.lang.EnglishProvider;

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
    }
}
