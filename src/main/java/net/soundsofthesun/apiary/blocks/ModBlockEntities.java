package net.soundsofthesun.apiary.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.soundsofthesun.apiary.Apiary;
import net.soundsofthesun.apiary.blocks.extractor.ExtractorBlockEntity;

public class ModBlockEntities {
    public static final BlockEntityType<ExtractorBlockEntity> HONEY_EXTRACTOR_ENTITY =
            register("honey_extractor_entity", ExtractorBlockEntity::new, ModBlocks.HONEY_EXTRACTOR);

    private static <T extends BlockEntity> BlockEntityType<T> register(
            String name,
            FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory,
            Block... blocks
    ) {
        Identifier id = Apiary.id(name);
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, id, FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build());
    }
    public static void init() {}
}
