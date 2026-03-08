package net.soundsofthesun.apiary.client.datagen.lang;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

import static net.soundsofthesun.apiary.Apiary.MOD_ID;
import static net.soundsofthesun.apiary.Apiary.MOD_NAME;

public class EnglishProvider extends FabricLanguageProvider {
    public EnglishProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.@NonNull Provider provider, TranslationBuilder translationBuilder) {

        translationBuilder.add(MOD_ID+".itemGroup",
                MOD_NAME);

        translationBuilder.add("block."+MOD_ID+".honey_extractor",
                "Honey Extractor");

        translationBuilder.add("block."+MOD_ID+".drip_pan",
                "Drip Pan");

        translationBuilder.add("item."+MOD_ID+".hive_tool",
                "Hive Tool");

        translationBuilder.add("item."+MOD_ID+".beekeeper_veil",
                "Beekeeper Veil");

        translationBuilder.add("item."+MOD_ID+".mesh",
                "Mesh");

        translationBuilder.add("item."+MOD_ID+".honey_bucket",
                "Bucket of Honey");

        translationBuilder.add(MOD_ID+".villager.beekeeper",
                "Beekeeper");

        translationBuilder.add("effect."+MOD_ID+".honey_regeneration",
                "Apitherapy");//https://en.wikipedia.org/wiki/Apitherapy

        translationBuilder.add("effect."+MOD_ID+".honey_sticky",
                "Sticky");

        translationBuilder.add("tag.item."+MOD_ID+".honey_harvesters",
                "Honey Harvesters");

        translationBuilder.add("tag.item."+MOD_ID+".bee_protection",
                "Bee Protection");

        translationBuilder.add("tag.item."+MOD_ID+".repairs_bee_protection",
                "Repairs Bee Protection");

        translationBuilder.add("tag.item."+MOD_ID+".bee_stuff",
                "Beekeeping Items");

    }
}
