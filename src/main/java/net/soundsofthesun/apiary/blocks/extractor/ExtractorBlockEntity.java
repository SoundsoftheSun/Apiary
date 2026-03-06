package net.soundsofthesun.apiary.blocks.extractor;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.soundsofthesun.apiary.blocks.ModBlockEntities;

public class ExtractorBlockEntity extends BlockEntity {
    public ExtractorBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.HONEY_EXTRACTOR_ENTITY, pos, blockState);
    }

    public static void tick(Level level, BlockPos pos, BlockState blockState, ExtractorBlockEntity entity) {

    }

    public static void drip(Level level, BlockPos pos) {
        level.addParticle(ParticleTypes.FALLING_HONEY, pos.getX()+.5, pos.getY()+.75, pos.getZ()+.5, 1, 1, 1);
    }

}
