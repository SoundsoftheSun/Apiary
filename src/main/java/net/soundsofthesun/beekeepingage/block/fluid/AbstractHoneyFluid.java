package net.soundsofthesun.beekeepingage.block.fluid;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import org.jspecify.annotations.NonNull;

public abstract class AbstractHoneyFluid extends FlowingFluid {

    // Liquid honey can be exploded
    @Override
    protected float getExplosionResistance() {
        return 10.0F;
    }

    // Faster in nether
    public static boolean isFastLava(LevelReader level) {
        return level.environmentAttributes().getDimensionValue(EnvironmentAttributes.FAST_LAVA);
    }

    @Override
    protected int getSlopeFindDistance(@NonNull LevelReader reader) {
        return isFastLava(reader) ? 2 : 1;
    }

    @Override
    protected int getDropOff(@NonNull LevelReader reader) {
        return isFastLava(reader) ? 1 : 2;
    }

    @Override
    public int getTickDelay(@NonNull LevelReader reader) {
        return isFastLava(reader) ? 20 : 40;
    }

    @Override
    public boolean isSame(@NonNull Fluid fluid) {
        return fluid == getSource() || fluid == getFlowing();
    }

    @Override
    protected void beforeDestroyingBlock(@NonNull LevelAccessor level, @NonNull BlockPos pos, BlockState state) {
        final BlockEntity entity = state.hasBlockEntity() ? level.getBlockEntity(pos) : null;
        Block.dropResources(state, level, pos, entity);
    }

    @Override
    protected boolean canBeReplacedWith(@NonNull FluidState state, @NonNull BlockGetter level, @NonNull BlockPos pos, @NonNull Fluid fluid, @NonNull Direction direction) {
        return false;
    }

}
