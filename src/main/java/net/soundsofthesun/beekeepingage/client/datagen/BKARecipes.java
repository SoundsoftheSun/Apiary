package net.soundsofthesun.beekeepingage.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.soundsofthesun.beekeepingage.BKA;
import net.soundsofthesun.beekeepingage.blocks.ModBlocks;
import net.soundsofthesun.beekeepingage.items.ModItems;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class BKARecipes extends FabricRecipeProvider {
    public BKARecipes(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected net.minecraft.data.recipes.@NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider provider, @NonNull RecipeOutput recipeOutput) {
        return new net.minecraft.data.recipes.RecipeProvider(provider, recipeOutput) {
            @Override
            public void buildRecipes() {
                //HolderLookup.RegistryLookup<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);

                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.HONEY_EXTRACTOR)
                        .pattern(" ci")
                        .pattern(" i ")
                        .pattern("i  ")
                        .define('c', Items.COPPER_INGOT)
                        .define('i', Items.IRON_INGOT)
                        .unlockedBy(getHasName(Items.HONEYCOMB), has(Items.HONEYCOMB))
                        .save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.HONEY_EXTRACTOR)
                        .pattern("wxw")
                        .pattern("wmw")
                        .pattern("wcw")
                        .define('w', Items.IRON_INGOT)
                        .define('x', Items.HONEYCOMB)
                        .define('m', ModItems.MESH)
                        .define('c', Items.CAULDRON)
                        .unlockedBy(getHasName(Items.HONEYCOMB), has(Items.HONEYCOMB))
                        .save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DRIP_PAN)
                        .pattern("   ")
                        .pattern("w w")
                        .pattern("www")
                        .define('w', Items.IRON_INGOT)
                        .unlockedBy(getHasName(Items.HONEYCOMB), has(Items.HONEYCOMB))
                        .save(output);

                shaped(RecipeCategory.MISC, ModItems.MESH)
                        .pattern("SSS")
                        .pattern("SSS")
                        .pattern("SSS")
                        .define('S', Items.STRING)
                        .unlockedBy(getHasName(Items.HONEYCOMB), has(Items.HONEYCOMB))
                        .save(output);

                shaped(RecipeCategory.TOOLS, ModItems.VEIL)
                        .pattern(" c ")
                        .pattern("ccc")
                        .pattern(" M ")
                        .define('c', ItemTags.WOOL_CARPETS)
                        .define('M', ModItems.MESH)
                        .unlockedBy(getHasName(Items.HONEYCOMB), has(Items.HONEYCOMB))
                        .save(output);

                trimSmithing(ModItems.BEEKEEPER_SMITHING_TEMPLATE, ModItems.SMITHING_BEEKEEPER_PATTERN, ResourceKey.create(Registries.RECIPE, BKA.id("beekeeper_pattern")));

                shapeless(RecipeCategory.MISC, ModItems.BEEKEEPER_SMITHING_TEMPLATE)
                        .requires(Items.HONEYCOMB)
                        .requires(Items.STONE)
                        .unlockedBy(getHasName(Items.HONEYCOMB), has(Items.HONEYCOMB))
                        .save(output);

                shapeless(RecipeCategory.MISC, ModItems.BEEKEEPER_SMITHING_TEMPLATE)
                        .requires(ModItems.BEEKEEPER_SMITHING_TEMPLATE)
                        .requires(Items.STONE)
                        .unlockedBy(getHasName(ModItems.BEEKEEPER_SMITHING_TEMPLATE), has(ModItems.BEEKEEPER_SMITHING_TEMPLATE))
                        .save(output);

            }
        };
    }

    @Override
    public @NonNull String getName() {
        return BKA.MOD_ID+" RecipeProvider";
    }
}
