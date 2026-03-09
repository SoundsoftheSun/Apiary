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


        // Blocks
        translationBuilder.add("block."+MOD_ID+".honey_extractor",
                "Honey Extractor");

        translationBuilder.add("block."+MOD_ID+".drip_pan",
                "Drip Pan");



        // Items

        translationBuilder.add("item."+MOD_ID+".hive_tool",
                "Hive Tool");

        translationBuilder.add("item."+MOD_ID+".beekeeper_veil",
                "Beekeeper Veil");

        translationBuilder.add("item."+MOD_ID+".mesh",
                "Mesh");

        translationBuilder.add("item."+MOD_ID+".honey_bucket",
                "Bucket of Honey");

        translationBuilder.add("item."+MOD_ID+".beekeeper_smithing_template",
                "Stele of Beekeeping Wisdom");

        translationBuilder.add(MOD_ID+".template_description",
                "Contains Ancient Beekeeping Knowledge");

        translationBuilder.add("trim_pattern."+MOD_ID+".beekeeper_pattern",
                "Beekeeper Armor Trim");



        // Entities

        translationBuilder.add(MOD_ID+".villager.beekeeper",
                "Beekeeper");



        // Effects

        translationBuilder.add("effect."+MOD_ID+".honey_regeneration",
                "Apitherapy");//https://en.wikipedia.org/wiki/Apitherapy

        translationBuilder.add("effect."+MOD_ID+".honey_sticky",
                "Sticky");



        // Tags

        translationBuilder.add("tag.item."+MOD_ID+".bee_protection",
                "Bee Protection");

        translationBuilder.add("tag.item."+MOD_ID+".repairs_bee_protection",
                "Repairs Bee Protection");



        // Advancements

        translationBuilder.add(MOD_ID+".advancement.get_bee_items",
                "Apiary: Beekeeping Age");

        translationBuilder.add(MOD_ID+".advancement.get_bee_items.description",
                "Start your beekeeping journey!");



        translationBuilder.add(MOD_ID+".advancement.get_beehive",
                "Mutualism");

        translationBuilder.add(MOD_ID+".advancement.get_beehive.description",
                "Obtain a Beehive!");



        translationBuilder.add(MOD_ID+".advancement.use_hive_tool",
                "Worker Bee");

        translationBuilder.add(MOD_ID+".advancement.use_hive_tool.description",
                "Obtain and use a Hive Tool!");



        translationBuilder.add(MOD_ID+".advancement.harvest_veil",
                "Ethical Apiculture");

        translationBuilder.add(MOD_ID+".advancement.harvest_veil.description",
                "Harvest honey while wearing a Veil without using a campfire.");



        translationBuilder.add(MOD_ID+".advancement.use_extractor",
                "Efficient Extraction");

        translationBuilder.add(MOD_ID+".advancement.use_extractor.description",
                "Obtain and use a Honey Extractor!");



        translationBuilder.add(MOD_ID+".advancement.honey_clutch",
                "Flight of the Bumblebee");

        translationBuilder.add(MOD_ID+".advancement.honey_clutch.description",
                "Survive a lethal fall in the Nether using Honey!");



        // Guidebook

        translationBuilder.add("book."+MOD_ID+".title",
                "Apiary");

        translationBuilder.add("book."+MOD_ID+".body_one",
                "Guide body one");

        translationBuilder.add("book."+MOD_ID+".body_two",
                """
                        Guidebook body two!
                        Guidebook body two!
                        Guidebook body two!
                        Guidebook body two!
                        Guidebook body two!
                        Guidebook body two!
                        Guidebook body two!
                        Guidebook body two!
                        Guidebook body two!
                        Guidebook body two!
                        Guidebook body two!
                        Guidebook body two!

                        """
        );



    }
}
