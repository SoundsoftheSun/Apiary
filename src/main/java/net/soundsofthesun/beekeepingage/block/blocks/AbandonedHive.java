package net.soundsofthesun.beekeepingage.block.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponentMap;
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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.soundsofthesun.beekeepingage.components.ModComponents;
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

            level.setBlockAndUpdate(pos, Blocks.BEEHIVE.defaultBlockState());
            if (level.getBlockEntity(pos) instanceof BeehiveBlockEntity be) {
                be.setComponents(DataComponentMap.builder()
                    .addAll(be.components())
                    .set(ModComponents.BEEHIVE_RESTORED, new ModComponents.RestoredComponent(true))
                    .build());
                be.setChanged();
            }

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
