package net.soundsofthesun.apiary.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Items;
import net.soundsofthesun.apiary.Apiary;
import net.soundsofthesun.apiary.advancement.*;
import net.soundsofthesun.apiary.blocks.ModBlocks;
import net.soundsofthesun.apiary.items.ModItems;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static net.soundsofthesun.apiary.Apiary.MOD_ID;

public class ApiaryAdvancements extends FabricAdvancementProvider {
    public ApiaryAdvancements(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(HolderLookup.Provider provider, Consumer<AdvancementHolder> consumer) {
        AdvancementHolder beginning = Advancement.Builder.advancement()
                .display(
                        Items.HONEYCOMB,
                        Component.translatable(MOD_ID+".advancement.get_bee_items"),
                        Component.translatable(MOD_ID+".advancement.get_bee_items.description"),
                        Identifier.withDefaultNamespace("textures/gui/advancements/backgrounds/adventure.png"), // Background image for the tab in the advancements page, if this is a root advancement (has no parent)
                        AdvancementType.TASK,
                        true, // Show toast
                        true, // Announce
                        false // Hide until achieved
                )
                .addCriterion("get_bee_items", InventoryChangeTrigger.TriggerInstance.hasItems(Items.HONEYCOMB))
                .save(consumer, Apiary.MOD_ID + ":get_bee_items");

        AdvancementHolder mutualism = Advancement.Builder.advancement()
                .parent(beginning)
                .display(
                        Items.BEEHIVE,
                        Component.translatable(MOD_ID+".advancement.get_beehive"),
                        Component.translatable(MOD_ID+".advancement.get_beehive.description"),
                        null,
                        AdvancementType.TASK,
                        true, // Show toast
                        true, // Announce
                        false // Hide until achieved
                )
                .addCriterion("get_beehive", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BEEHIVE))
                .save(consumer, Apiary.MOD_ID + ":get_beehive");

        AdvancementHolder workerBee = Advancement.Builder.advancement()
                .parent(mutualism)
                .display(
                        ModItems.HIVE_TOOL,
                        Component.translatable(MOD_ID+".advancement.use_hive_tool"),
                        Component.translatable(MOD_ID+".advancement.use_hive_tool.description"),
                        null,
                        AdvancementType.TASK,
                        true, // Show toast
                        true, // Announce
                        false // Hide until achieved
                )
                .addCriterion("use_hive_tool", ModCriteria.USE_HIVE_TOOL.createCriterion(new UseHiveToolCriterion.Conditions(Optional.empty())))
                .save(consumer, Apiary.MOD_ID + ":use_hive_tool");

        AdvancementHolder ethical = Advancement.Builder.advancement()
                .parent(mutualism)
                .display(
                        ModItems.VEIL,
                        Component.translatable(MOD_ID+".advancement.harvest_veil"),
                        Component.translatable(MOD_ID+".advancement.harvest_veil.description"),
                        null,
                        AdvancementType.GOAL,
                        true, // Show toast
                        true, // Announce
                        false // Hide until achieved
                )
                .addCriterion("harvest_veil", ModCriteria.HARVEST_WITH_VEIL.createCriterion(new HarvestWithVeilCriterion.Conditions(Optional.empty())))
                .save(consumer, Apiary.MOD_ID + ":harvest_veil");

        AdvancementHolder extractor = Advancement.Builder.advancement()
                .parent(workerBee)
                .display(
                        ModBlocks.HONEY_EXTRACTOR.asItem(),
                        Component.translatable(MOD_ID+".advancement.use_extractor"),
                        Component.translatable(MOD_ID+".advancement.use_extractor.description"),
                        null,
                        AdvancementType.GOAL,
                        true, // Show toast
                        true, // Announce
                        false // Hide until achieved
                )
                .addCriterion("use_extractor", ModCriteria.USE_EXTRACTOR.createCriterion(new UseExtractorCriterion.Conditions(Optional.empty())))
                .save(consumer, Apiary.MOD_ID + ":use_extractor");

        AdvancementHolder clutch = Advancement.Builder.advancement()
                .parent(extractor)
                .display(
                        ModItems.HONEY_BUCKET,
                        Component.translatable(MOD_ID+".advancement.honey_clutch"),
                        Component.translatable(MOD_ID+".advancement.honey_clutch.description"),
                        null,
                        AdvancementType.CHALLENGE,
                        true, // Show toast
                        true, // Announce
                        false // Hide until achieved
                )
                .addCriterion("honey_clutch", ModCriteria.HONEY_CLUTCH.createCriterion(new HoneyBucketClutchCriterion.Conditions(Optional.empty())))
                .save(consumer, Apiary.MOD_ID + ":honey_clutch");


    }
}
