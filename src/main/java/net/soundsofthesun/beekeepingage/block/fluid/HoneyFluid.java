package net.soundsofthesun.beekeepingage.block.fluid;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.InsideBlockEffectType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.soundsofthesun.beekeepingage.block.ModBlocks;
import net.soundsofthesun.beekeepingage.effects.ModEffects;
import net.soundsofthesun.beekeepingage.items.ModItems;
import org.jspecify.annotations.NonNull;

import java.util.Map;
import java.util.Optional;

public abstract class HoneyFluid extends AbstractHoneyFluid {

    public static void entityInHoney(LivingEntity livingEntity) {
        Map<Holder<MobEffect>, MobEffectInstance> effects = livingEntity.getActiveEffectsMap();
        boolean hasEffect = effects.containsKey(ModEffects.HONEY_REGENERATION);
        if (!hasEffect || effects.get(ModEffects.HONEY_REGENERATION).getDuration() <= ModEffects.regenDuration-20/*This prevents instant healing*/) {
            livingEntity.addEffect(new MobEffectInstance(ModEffects.HONEY_REGENERATION, 100));
        }
    }

    @Override
    protected boolean canBeReplacedWith(@NonNull FluidState state, @NonNull BlockGetter level, @NonNull BlockPos pos, @NonNull Fluid fluid, @NonNull Direction direction) {
        return state.is(ModBlocks.HONEY_SOURCE);
    }

    @Override
    public ParticleOptions getDripParticle() {
        return ParticleTypes.DRIPPING_HONEY;
    }

    @Override
    public @NonNull Fluid getSource() {
        return ModBlocks.HONEY_SOURCE;
    }

    @Override
    public @NonNull Fluid getFlowing() {
        return ModBlocks.HONEY_FLOWING;
    }

    @Override
    public @NonNull Item getBucket() {
        return ModItems.HONEY_BUCKET;
    }

    @Override
    public @NonNull Optional<SoundEvent> getPickupSound() {
        return Optional.of(SoundEvents.HONEY_BLOCK_BREAK);
    }

    @Override
    protected @NonNull BlockState createLegacyBlock(@NonNull FluidState state) {
        return ModBlocks.HONEY_FLUID_BLOCK.defaultBlockState().setValue(BlockStateProperties.LEVEL, getLegacyLevel(state));
    }

    @Override
    protected boolean canConvertToSource(@NonNull ServerLevel level) {
        return false;
    }

    public static class Flowing extends HoneyFluid {

        @Override
        protected void entityInside(@NonNull Level level, @NonNull BlockPos pos, @NonNull Entity entity, @NonNull InsideBlockEffectApplier effectApplier) {
            if (!(entity instanceof LivingEntity livingEntity)) return;
            // "Melt" flowing honey if on fire, or apply healing effect
            if (livingEntity.isOnFire()) {
                level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
            } else {
                entityInHoney(livingEntity);
            }
        }

        @Override
        protected void createFluidStateDefinition(StateDefinition.@NonNull Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        @Override
        public boolean isSource(@NonNull FluidState state) {
            return false;
        }

        @Override
        public int getAmount(FluidState state) {
            return state.getValue(LEVEL);
        }
    }

    public static class Still extends HoneyFluid {

        @Override
        protected void entityInside(@NonNull Level level, @NonNull BlockPos pos, @NonNull Entity entity, @NonNull InsideBlockEffectApplier effectApplier) {
            if (!(entity instanceof LivingEntity livingEntity)) return;
            // "Melt" source honey if on fire and extinguish, or apply healing effect
            if (livingEntity.isOnFire()) {
                level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                effectApplier.apply(InsideBlockEffectType.EXTINGUISH);
            } else {
                entityInHoney(livingEntity);
            }
        }

        @Override
        public boolean isSource(@NonNull FluidState state) {
            return true;
        }

        @Override
        public int getAmount(@NonNull FluidState state) {
            return 5;
        }
    }
}
