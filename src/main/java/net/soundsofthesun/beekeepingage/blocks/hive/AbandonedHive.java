package net.soundsofthesun.beekeepingage.blocks.hive;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.soundsofthesun.beekeepingage.blocks.ModBlocks;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class AbandonedHive extends HorizontalDirectionalBlock {
    public AbandonedHive(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(HorizontalDirectionalBlock.FACING, Direction.NORTH)
        );
    }

    @Override
    protected @NonNull InteractionResult useItemOn(ItemStack stack, @NonNull BlockState state, @NonNull Level level, @NonNull BlockPos pos, @NonNull Player player, @NonNull InteractionHand hand, @NonNull BlockHitResult hitResult) {
        if (stack.is(Items.HONEYCOMB)) {
            if (!player.hasInfiniteMaterials()) stack.shrink(1);
            level.setBlockAndUpdate(pos, ModBlocks.RESTORED_BEEHIVE.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, state.getValue(HorizontalDirectionalBlock.FACING)));
            level.playSound(null, pos.getX()+0.5F, pos.getY()+0.5F, pos.getZ()+0.5F, SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS, 1F, 1F);
            return InteractionResult.SUCCESS;
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HorizontalDirectionalBlock.FACING);
    }

    @Override
    protected @NonNull MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return simpleCodec(AbandonedHive::new);
    }

}
