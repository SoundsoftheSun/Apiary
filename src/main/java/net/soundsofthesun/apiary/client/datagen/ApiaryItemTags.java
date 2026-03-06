package net.soundsofthesun.apiary.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.soundsofthesun.apiary.Apiary;
import net.soundsofthesun.apiary.items.ModItems;

import java.util.concurrent.CompletableFuture;

public class ApiaryItemTags extends FabricTagProvider.ItemTagProvider {
    public ApiaryItemTags(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    public static final TagKey<Item> HONEY_HARVESTERS = TagKey.create(Registries.ITEM, Apiary.id("honey_harvesters"));

    public static final TagKey<Item> BEE_PROTECTION = TagKey.create(Registries.ITEM, Apiary.id("bee_protection"));

    public static final TagKey<Item> REPAIRS_BEE_PROTECTION = TagKey.create(Registries.ITEM, Apiary.id("repairs_bee_protection"));

    public static final TagKey<Item> BEE_STUFF = TagKey.create(Registries.ITEM, Apiary.id("bee_stuff"));

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        valueLookupBuilder(REPAIRS_BEE_PROTECTION)
                .add(ModItems.MESH);

        valueLookupBuilder(HONEY_HARVESTERS)
                .add(Items.SHEARS)
                .add(ModItems.HIVE_TOOL);

        valueLookupBuilder(BEE_PROTECTION)
                .add(ModItems.VEIL);

        valueLookupBuilder(BEE_STUFF)
                .add(ModItems.VEIL)
                .add(ModItems.HONEY_BUCKET)
                .add(ModItems.HIVE_TOOL)
                .add(ModItems.MESH)
                .add(Items.HONEY_BLOCK)
                .add(Items.HONEYCOMB_BLOCK)
                .add(Items.HONEYCOMB)
                .add(Items.HONEY_BOTTLE)
                .add(Items.BEE_NEST)
                .add(Items.BEEHIVE);
    }
}
