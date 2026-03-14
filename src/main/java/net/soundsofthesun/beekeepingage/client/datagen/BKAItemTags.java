package net.soundsofthesun.beekeepingage.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.soundsofthesun.beekeepingage.BKA;
import net.soundsofthesun.beekeepingage.items.ModItems;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class BKAItemTags extends FabricTagProvider.ItemTagProvider {
    public BKAItemTags(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    public static final TagKey<Item> BEE_PROTECTION = TagKey.create(Registries.ITEM, BKA.id("bee_protection"));

    public static final TagKey<Item> REPAIRS_BEE_PROTECTION = TagKey.create(Registries.ITEM, BKA.id("repairs_bee_protection"));

    public static final TagKey<Item> REPAIRS_HIVE_TOOL = TagKey.create(Registries.ITEM, BKA.id("repairs_hive_tool"));

    @Override
    protected void addTags(HolderLookup.@NonNull Provider provider) {

        valueLookupBuilder(REPAIRS_HIVE_TOOL)
                .add(Items.IRON_INGOT);

        valueLookupBuilder(BEE_PROTECTION)
                .add(ModItems.VEIL);

        valueLookupBuilder(REPAIRS_BEE_PROTECTION)
                .add(ModItems.MESH);

    }
}
