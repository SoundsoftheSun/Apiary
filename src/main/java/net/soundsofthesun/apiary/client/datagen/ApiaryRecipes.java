package net.soundsofthesun.apiary.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.soundsofthesun.apiary.Apiary;
import net.soundsofthesun.apiary.blocks.ModBlocks;
import net.soundsofthesun.apiary.items.ModItems;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ApiaryRecipes extends FabricRecipeProvider {
    public ApiaryRecipes(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected net.minecraft.data.recipes.@NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider provider, @NonNull RecipeOutput recipeOutput) {
        return new net.minecraft.data.recipes.RecipeProvider(provider, recipeOutput) {
            @Override
            public void buildRecipes() {
                //HolderLookup.RegistryLookup<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);

                shapeless(RecipeCategory.TOOLS, ModItems.HIVE_TOOL)
                        .requires(Items.STICK)
                        .requires(Items.IRON_INGOT)
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
                        .group(Apiary.MOD_ID)
                        .unlockedBy(getHasName(Items.HONEYCOMB), has(Items.HONEYCOMB))
                        .save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DRIP_PAN)
                        .pattern("   ")
                        .pattern("w w")
                        .pattern("www")
                        .define('w', Items.IRON_INGOT)
                        .group(Apiary.MOD_ID)
                        .unlockedBy(getHasName(Items.HONEYCOMB), has(Items.HONEYCOMB))
                        .save(output);

                shaped(RecipeCategory.MISC, ModItems.MESH)
                        .pattern("SSS")
                        .pattern("SSS")
                        .pattern("SSS")
                        .define('S', Items.STRING)
                        .group(Apiary.MOD_ID)
                        .unlockedBy(getHasName(Items.STRING), has(Items.STRING))
                        .save(output);

                shaped(RecipeCategory.TOOLS, ModItems.VEIL)
                        .pattern(" c ")
                        .pattern("ccc")
                        .pattern(" M ")
                        .define('c', ItemTags.WOOL_CARPETS)
                        .define('M', ModItems.MESH)
                        .unlockedBy(getHasName(ModItems.MESH), has(ModItems.MESH))
                        .save(output);

                trimSmithing(ModItems.BEEKEEPER_SMITHING_TEMPLATE, ModItems.SMITHING_BEEKEEPER_PATTERN, ResourceKey.create(Registries.RECIPE, Apiary.id("beekeeper_pattern")));
            }
        };
    }

    @Override
    public @NonNull String getName() {
        return Apiary.MOD_ID+" RecipeProvider";
    }
}
