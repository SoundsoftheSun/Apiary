package net.soundsofthesun.apiary.blocks.fluid;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.InsideBlockEffectType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.soundsofthesun.apiary.blocks.ModBlocks;
import net.soundsofthesun.apiary.items.ModItems;

import java.util.Optional;

public abstract class HoneyFluid extends AbstractHoneyFluid {

    @Override
    public ParticleOptions getDripParticle() {
        return ParticleTypes.DRIPPING_HONEY;
    }

    @Override
    public Fluid getSource() {
        return ModBlocks.HONEY_SOURCE;
    }

    @Override
    public Fluid getFlowing() {
        return ModBlocks.HONEY_FLOWING;
    }

    @Override
    public Item getBucket() {
        return ModItems.HONEY_BUCKET;
    }

    @Override
    public Optional<SoundEvent> getPickupSound() {
        return Optional.of(SoundEvents.HONEY_BLOCK_BREAK);
    }

    @Override
    protected BlockState createLegacyBlock(FluidState state) {
        return ModBlocks.HONEY_FLUID_BLOCK.defaultBlockState().setValue(BlockStateProperties.LEVEL, getLegacyLevel(state));
    }

    @Override
    protected boolean canConvertToSource(ServerLevel level) {
        return false;
    }

    public static class Flowing extends HoneyFluid {

        @Override
        protected void entityInside(Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier) {
            // "Melt" flowing honey if on fire
            if (entity.isOnFire()) level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
        }

        @Override
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        @Override
        public boolean isSource(FluidState state) {
            return false;
        }

        @Override
        public int getAmount(FluidState state) {
            return state.getValue(LEVEL);
        }
    }

    public static class Still extends HoneyFluid {

        @Override
        protected void entityInside(Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier) {
            // "Melt" source honey if on fire and extinguish
            if (entity.isOnFire()) {
                level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                effectApplier.apply(InsideBlockEffectType.EXTINGUISH);
            }
        }

        @Override
        public boolean isSource(FluidState state) {
            return true;
        }

        @Override
        public int getAmount(FluidState state) {
            return 5;
        }
    }
}
