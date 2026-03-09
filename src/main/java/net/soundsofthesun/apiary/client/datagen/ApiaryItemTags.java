package net.soundsofthesun.apiary.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.soundsofthesun.apiary.Apiary;
import net.soundsofthesun.apiary.items.ModItems;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ApiaryItemTags extends FabricTagProvider.ItemTagProvider {
    public ApiaryItemTags(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    public static final TagKey<Item> BEE_PROTECTION = TagKey.create(Registries.ITEM, Apiary.id("bee_protection"));

    public static final TagKey<Item> REPAIRS_BEE_PROTECTION = TagKey.create(Registries.ITEM, Apiary.id("repairs_bee_protection"));

    @Override
    protected void addTags(HolderLookup.@NonNull Provider provider) {

        valueLookupBuilder(REPAIRS_BEE_PROTECTION)
                .add(ModItems.MESH);

        valueLookupBuilder(BEE_PROTECTION)
                .add(ModItems.VEIL);

    }
}
