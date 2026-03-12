package net.soundsofthesun.beekeepingage.block;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.soundsofthesun.beekeepingage.BKA;
import net.soundsofthesun.beekeepingage.block.blocks.ExtractorBlockEntity;

public class ModBlockEntities {
    public static final BlockEntityType<ExtractorBlockEntity> HONEY_EXTRACTOR_ENTITY =
            register("honey_extractor_entity", ExtractorBlockEntity::new, ModBlocks.HONEY_EXTRACTOR);

    private static <T extends BlockEntity> BlockEntityType<T> register(
            String name,
            FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory,
            Block... blocks
    ) {
        Identifier id = BKA.id(name);
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, id, FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build());
    }
    public static void init() {}
}
