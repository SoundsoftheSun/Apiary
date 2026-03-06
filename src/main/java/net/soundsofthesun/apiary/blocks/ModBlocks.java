package net.soundsofthesun.apiary.blocks;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.soundsofthesun.apiary.Apiary;
import net.soundsofthesun.apiary.blocks.extractor.ExtractorBlock;
import net.soundsofthesun.apiary.blocks.fluid.HoneyFluid;
import net.soundsofthesun.apiary.blocks.pan.DripPanBlock;

import java.util.function.Function;

public class ModBlocks {

    //TODO workstation behavior
    public static final Block HONEY_EXTRACTOR = register(
            "honey_extractor",
            ExtractorBlock::new,
            BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .sound(SoundType.IRON)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .ignitedByLava(),
            true
    );

    //TODO drip pan behavior
    public static final Block DRIP_PAN = register(
            "drip_pan",
            DripPanBlock::new,
            BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .sound(SoundType.IRON)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F),
            true
    );

    public static FlowingFluid HONEY_SOURCE = Registry.register(BuiltInRegistries.FLUID, Apiary.id("honey_source"), new HoneyFluid.Still());
    public static FlowingFluid HONEY_FLOWING = Registry.register(BuiltInRegistries.FLUID, Apiary.id("honey_flowing"), new HoneyFluid.Flowing());

    public static final Block HONEY_FLUID_BLOCK = register(
            "honey_fluid_block",
            properties -> new LiquidBlock(HONEY_SOURCE, properties),
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_ORANGE)
                    .replaceable()
                    .noCollision()
                    .strength(100.0F)
                    .pushReaction(PushReaction.DESTROY)
                    .noLootTable()
                    .liquid()
                    .sound(SoundType.EMPTY),
            false
            );

    private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties settings, boolean shouldRegisterItem) {
        ResourceKey<Block> blockKey = keyOfBlock(name);
        Block block = blockFactory.apply(settings.setId(blockKey));
        if (shouldRegisterItem) {
            ResourceKey<Item> itemKey = keyOfItem(name);
            BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix());
            Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
        }
        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }
    private static ResourceKey<Block> keyOfBlock(String name) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Apiary.MOD_ID, name));
    }
    private static ResourceKey<Item> keyOfItem(String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Apiary.MOD_ID, name));
    }
    public static void init() {}
}
