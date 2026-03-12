package net.soundsofthesun.beekeepingage.block.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.soundsofthesun.beekeepingage.advancement.ModCriteria;
import net.soundsofthesun.beekeepingage.block.ModBlockEntities;
import net.soundsofthesun.beekeepingage.block.ModProperties;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class ExtractorBlock extends BaseEntityBlock {
    public ExtractorBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(ModProperties.ACTIVE_PROPERTY, ModProperties.ACTIVE_STATE.OFF)
                .setValue(HorizontalDirectionalBlock.FACING, Direction.NORTH)
        );
    }

    @Override
    protected @NonNull InteractionResult useItemOn(@NonNull ItemStack stack, @NonNull BlockState state, Level level, @NonNull BlockPos pos, @NonNull Player player, @NonNull InteractionHand hand, @NonNull BlockHitResult hitResult) {
        if (level.isClientSide()) return InteractionResult.SUCCESS;
        if (
            level.getBlockEntity(pos) instanceof ExtractorBlockEntity be &&
            player.canInteractWithLevel() &&
            player.getItemInHand(hand).is(Items.HONEYCOMB) &&
            state.getValue(ModProperties.ACTIVE_PROPERTY) == ModProperties.ACTIVE_STATE.OFF
        ) {
            if (be.getItem(0).getCount() == 3) {
                if (!player.hasInfiniteMaterials()) player.getItemInHand(hand).shrink(1);
                be.clearContent();
                level.setBlockAndUpdate(pos, state.setValue(ModProperties.ACTIVE_PROPERTY, ModProperties.ACTIVE_STATE.ON));
                ModCriteria.USE_EXTRACTOR.trigger((ServerPlayer) player);
                be.ticks = 0;
                be.setChanged();
            } else if (be.getItem(0).isEmpty()) {
                be.setItem(0, player.getItemInHand(hand).getItem().getDefaultInstance());
                if (!player.hasInfiniteMaterials()) player.getItemInHand(hand).shrink(1);
                level.playSound(null, pos, SoundEvents.HONEY_BLOCK_STEP, SoundSource.BLOCKS, 0.2F, 1F);
                be.setChanged();
            } else if (be.getItem(0).is(Items.HONEYCOMB)) {
                be.getItem(0).grow(1);
                if (!player.hasInfiniteMaterials()) player.getItemInHand(hand).shrink(1);
                level.playSound(null, pos, SoundEvents.HONEY_BLOCK_STEP, SoundSource.BLOCKS, 0.2F, 1F);
                be.setChanged();
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void playerDestroy(@NonNull Level level, @NonNull Player player, @NonNull BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, @NonNull ItemStack tool) {
        if (state.getValue(ModProperties.ACTIVE_PROPERTY) == ModProperties.ACTIVE_STATE.ON && level instanceof ServerLevel serverLevel) {
            serverLevel.addFreshEntity(new ItemEntity(level, pos.getX()+0.5F, pos.getY()+0.5F, pos.getZ()+0.5F, new ItemStack(Items.HONEYCOMB, 4)));
        }
        super.playerDestroy(level, player, pos, state, blockEntity, tool);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ModProperties.ACTIVE_PROPERTY);
        builder.add(HorizontalDirectionalBlock.FACING);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected @NonNull MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(ExtractorBlock::new);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NonNull BlockPos pos, @NonNull BlockState state) {
        return new ExtractorBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NonNull Level level, @NonNull BlockState state, @NonNull BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, ModBlockEntities.HONEY_EXTRACTOR_ENTITY, ExtractorBlockEntity::tick);
    }

    public VoxelShape makeShape(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0, 0, 0, 0.1875, 0.1875, 0.1875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.8125, 0, 0, 1, 0.1875, 0.1875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0, 0.8125, 0.1875, 0.1875, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.8125, 0, 0.8125, 1, 0.1875, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.1875, 0, 1, 1, 1), BooleanOp.OR);
        return shape;
    }

    @Override
    protected @NonNull VoxelShape getShape(@NonNull BlockState state, @NonNull BlockGetter level, @NonNull BlockPos pos, @NonNull CollisionContext context) {
        return makeShape();
    }
}
