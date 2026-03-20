package net.soundsofthesun.beekeepingage.items;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.soundsofthesun.beekeepingage.block.ModBlocks;
import net.soundsofthesun.beekeepingage.block.ModProperties;
import org.jspecify.annotations.NonNull;

public class HoneyBucketItem extends BucketItem {
    public HoneyBucketItem(Properties properties) {
        super(ModBlocks.HONEY_SOURCE, properties);
    }

    @Override
    public @NonNull InteractionResult use(@NonNull Level level, @NonNull Player player, @NonNull InteractionHand hand) {
        BlockHitResult hit = getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);
        if (hit.getType() == HitResult.Type.BLOCK) {
            BlockPos pos = hit.getBlockPos();
            BlockState state = level.getBlockState(pos);
            if (state.is(ModBlocks.DRIP_PAN) && state.getValue(ModProperties.ACTIVE_PROPERTY) == ModProperties.ACTIVE_STATE.OFF) {
                level.setBlockAndUpdate(pos, state.setValue(ModProperties.ACTIVE_PROPERTY, ModProperties.ACTIVE_STATE.ON));
                level.playSound(null, pos.getX()+0.5F, pos.getY()+0.5F, pos.getZ()+0.5F, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS);
                return InteractionResult.SUCCESS.heldItemTransformedTo(ItemUtils.createFilledResult(player.getItemInHand(hand), player, Items.BUCKET.getDefaultInstance()));
            }
        }
        return super.use(level, player, hand);
    }
}
