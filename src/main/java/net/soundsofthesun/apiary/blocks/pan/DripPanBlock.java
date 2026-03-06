package net.soundsofthesun.apiary.blocks.pan;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.soundsofthesun.apiary.blocks.ModBlocks;
import net.soundsofthesun.apiary.blocks.ModProperties;
import net.soundsofthesun.apiary.effects.ModEffects;
import org.jspecify.annotations.Nullable;

public class DripPanBlock extends Block {
    public DripPanBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(ModProperties.ACTIVE_PROPERTY, ModProperties.ACTIVE_STATE.OFF));
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier applier, boolean intersects) {
        if (state.getValue(ModProperties.ACTIVE_PROPERTY) == ModProperties.ACTIVE_STATE.ON && entity.getY()-pos.getY() < 0.33 && entity instanceof LivingEntity livingEntity) {
            livingEntity.addEffect(new MobEffectInstance(ModEffects.HONEY_REGENERATION, 100));
        }
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        if (state.getValue(ModProperties.ACTIVE_PROPERTY) == ModProperties.ACTIVE_STATE.ON) {
            level.setBlockAndUpdate(pos, ModBlocks.HONEY_FLUID_BLOCK.defaultBlockState());
        }
        super.playerDestroy(level, player, pos, state, blockEntity, tool);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ModProperties.ACTIVE_PROPERTY);
    }

    private VoxelShape makeShape() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0, 0, 0, 0.3125, 0.1875, 0.3125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.6875, 0, 0, 1, 0.1875, 0.3125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0, 0.6875, 0.3125, 0.1875, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.6875, 0, 0.6875, 1, 0.1875, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.1875, 0, 1, 0.25, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.125, 0.25, 0, 0.875, 0.5, 0.125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.125, 0.25, 0.875, 0.875, 0.5, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.25, 0.125, 0.125, 0.5, 0.875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.875, 0.25, 0.125, 1, 0.5, 0.875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.25, 0, 0.125, 0.5, 0.125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.25, 0.875, 0.125, 0.5, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.875, 0.25, 0, 1, 0.5, 0.125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.875, 0.25, 0.875, 1, 0.5, 1), BooleanOp.OR);
        return shape;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return makeShape();
    }
}
