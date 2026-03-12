package net.soundsofthesun.beekeepingage.items;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Util;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.trim.TrimPattern;
import net.minecraft.world.level.block.Blocks;
import net.soundsofthesun.beekeepingage.BKA;
import net.soundsofthesun.beekeepingage.block.ModBlocks;
import net.soundsofthesun.beekeepingage.components.ModComponents;

import java.util.function.Function;

public class ModItems {

    public static final Item HIVE_TOOL = register("hive_tool", HiveToolItem::new, new Item.Properties().durability(212));

    public static final Item VEIL = register("beekeeper_veil", Item::new, new Item.Properties().equippable(EquipmentSlot.HEAD).durability(212).rarity(Rarity.RARE));

    public static final Item MESH = register("mesh", Item::new, new Item.Properties());

    public static final ResourceKey<TrimPattern> SMITHING_BEEKEEPER_PATTERN = ResourceKey.create(Registries.TRIM_PATTERN, BKA.id("beekeeper_pattern"));
    public static final Item BEEKEEPER_SMITHING_TEMPLATE = register("beekeeper_smithing_template", SmithingTemplateItem::createArmorTrimTemplate, new Item.Properties().rarity(Rarity.UNCOMMON));

    public static final Item HONEY_BUCKET = register("honey_bucket", properties -> new BucketItem(ModBlocks.HONEY_SOURCE, properties), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1));

    public static final CreativeModeTab.DisplayItemsGenerator TAB_ITEMS = (params, output) -> {
        output.accept(ModBlocks.HONEY_EXTRACTOR);
        output.accept(ModBlocks.DRIP_PAN);
        output.accept(ModBlocks.ABANDONED_BEEHIVE);
        ItemStack restoredHive = new ItemStack(Blocks.BEEHIVE.asItem());
        restoredHive.set(ModComponents.BEEHIVE_RESTORED, new ModComponents.RestoredComponent(true));
        output.accept(restoredHive);
        output.accept(ModItems.HONEY_BUCKET);
        output.accept(ModItems.HIVE_TOOL);
        output.accept(ModItems.BEEKEEPER_SMITHING_TEMPLATE);
        output.accept(ModItems.VEIL);
        output.accept(ModItems.MESH);
    };

    public static void bootstrapTrims(BootstrapContext<TrimPattern> context) {
        registerTrim(context, ModItems.BEEKEEPER_SMITHING_TEMPLATE, SMITHING_BEEKEEPER_PATTERN);
    }

    public static void init() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, BEEKEEPER_TAB_KEY, BEEKEEPER_TAB);
    }

    public static final ResourceKey<CreativeModeTab> BEEKEEPER_TAB_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), BKA.id("creative_tab"));
    public static final CreativeModeTab BEEKEEPER_TAB = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.HIVE_TOOL))
            .title(Component.translatable(BKA.MOD_ID+".itemGroup"))
            .displayItems(TAB_ITEMS)
            .build();

    public static <T extends Item> T register(String name, Function<Item.Properties, T> itemFactory, Item.Properties settings) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(BKA.MOD_ID, name));
        T item = itemFactory.apply(settings.setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);
        return item;
    }

    private static void registerTrim(BootstrapContext<TrimPattern> context, Item item, ResourceKey<TrimPattern> key) {
        TrimPattern trimPattern = new TrimPattern(key.identifier(),
                Component.translatable(Util.makeDescriptionId("trim_pattern", key.identifier())), false);

        context.register(key, trimPattern);
    }

}
