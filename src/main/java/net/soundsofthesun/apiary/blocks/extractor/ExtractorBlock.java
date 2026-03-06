package net.soundsofthesun.apiary.blocks.extractor;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
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
import net.soundsofthesun.apiary.blocks.ModBlockEntities;
import net.soundsofthesun.apiary.blocks.ModProperties;
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
    protected @NonNull InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (
            level.getBlockEntity(pos) instanceof ExtractorBlockEntity be &&
            player.getItemInHand(hand).is(Items.HONEYCOMB) &&
            be.getItem(0).getCount() < 4
        ){
            if (be.getItem(0) == ItemStack.EMPTY) {
                be.setItem(0, player.getItemInHand(hand).getItem().getDefaultInstance());
                player.getItemInHand(hand).shrink(1);
                be.setChanged();
            } else if (be.getItem(0).is(Items.HONEYCOMB)) {
                be.getItem(0).grow(1);
                player.getItemInHand(hand).shrink(1);
                be.setChanged();
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
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
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(ExtractorBlock::new);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ExtractorBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, ModBlockEntities.HONEY_EXTRACTOR_ENTITY, ExtractorBlockEntity::tick);
    }

}
