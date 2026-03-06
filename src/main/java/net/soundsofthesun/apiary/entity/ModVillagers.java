package net.soundsofthesun.apiary.entity;

import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.soundsofthesun.apiary.Apiary;
import net.soundsofthesun.apiary.blocks.ModBlocks;
import net.soundsofthesun.apiary.items.ModItems;

public class ModVillagers {

    public static final ResourceKey<VillagerProfession> BEEKEEPER = ResourceKey.create(Registries.VILLAGER_PROFESSION, Apiary.id("beekeeper"));
    public static final ResourceKey<PoiType> BEEKEEPER_POI = ResourceKey.create(Registries.POINT_OF_INTEREST_TYPE, Apiary.id("beekeeper_poi"));

    public static void init() {
        PointOfInterestHelper.register(Apiary.id("beekeeper_poi"),1, 1, ModBlocks.HONEY_EXTRACTOR);
        Registry.register(BuiltInRegistries.VILLAGER_PROFESSION, BEEKEEPER, new VillagerProfession(Component.translatable(Apiary.MOD_ID+".villager.beekeeper"), entry -> entry.is(BEEKEEPER_POI), entry -> entry.is(BEEKEEPER_POI), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.BEE_STING)); //TODO sound

        // Level 1
        TradeOfferHelper.registerVillagerOffers(BEEKEEPER, 1, factories -> {
            factories.add((world,entity, random) -> new MerchantOffer(
                    new ItemCost(Items.BEE_NEST, 1),
                    new ItemStack(Items.EMERALD, 3), 7, 2, 0.04f));

            factories.add((world,entity, random) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 3),
                    new ItemStack(Items.HONEYCOMB, 1), 9, 4, 0.04f));
        });

        // Level 2
        TradeOfferHelper.registerVillagerOffers(BEEKEEPER, 2, factories -> {
            factories.add((world,entity, random) -> new MerchantOffer(
                    new ItemCost(Items.HONEY_BOTTLE, 1),
                    new ItemStack(Items.EMERALD, 4), 9, 7, 0.04f));

            factories.add((world,entity, random) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 9),
                    new ItemStack(Items.BEEHIVE, 1), 3, 7, 0.04f));
        });

        // Level 3
        TradeOfferHelper.registerVillagerOffers(BEEKEEPER, 3, factories -> {
            factories.add((world,entity, random) -> new MerchantOffer(
                    new ItemCost(Items.HONEYCOMB_BLOCK, 1),
                    new ItemStack(Items.EMERALD, 4), 9, 7, 0.04f));

            factories.add((world,entity, random) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    new ItemStack(ModItems.HIVE_TOOL, 1), 3, 7, 0.04f));
        });

        // Level 4
        TradeOfferHelper.registerVillagerOffers(BEEKEEPER, 4, factories -> {
            factories.add((world,entity, random) -> new MerchantOffer(
                    new ItemCost(ModItems.HONEY_BUCKET, 1),
                    new ItemStack(Items.EMERALD, 7), 9, 7, 0.04f));

            factories.add((world,entity, random) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 4),
                    new ItemStack(Items.HONEY_BLOCK, 1), 9, 7, 0.04f));
        });

        // Level 5
        TradeOfferHelper.registerVillagerOffers(BEEKEEPER, 5, factories -> {
            factories.add((world,entity, random) -> new MerchantOffer(
                    new ItemCost(Items.HONEYCOMB_BLOCK, 1),
                    new ItemStack(Items.EMERALD, 7), 9, 7, 0.04f));

            factories.add((world,entity, random) -> new MerchantOffer(
                    new ItemCost(Items.HONEY_BOTTLE, 1),
                    new ItemStack(Items.EMERALD, 7), 3, 7, 0.04f));
        });
    }

}
