package net.soundsofthesun.beekeepingage.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.DispensibleContainerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.soundsofthesun.beekeepingage.block.ModBlocks;
import net.soundsofthesun.beekeepingage.block.ModProperties;
import net.soundsofthesun.beekeepingage.items.ModItems;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BucketItem.class)
public abstract class BucketItemMixin extends Item implements DispensibleContainerItem {
    @Mutable @Final @Shadow private Fluid content;
    public BucketItemMixin(Properties properties, Fluid content) {
        super(properties);
        this.content = content;
    }

    @WrapMethod(method = "use")// This could probably be better
    InteractionResult bka$bucketUse(Level level, Player player, InteractionHand hand, Operation<InteractionResult> original) {
        InteractionResult result = original.call(level, player, hand);
        if (result == InteractionResult.FAIL) {
            BlockHitResult hit = getPlayerPOVHitResult(level, player, this.content == Fluids.EMPTY ? ClipContext.Fluid.SOURCE_ONLY : ClipContext.Fluid.NONE);
            if (hit.getType() == HitResult.Type.BLOCK) {
                BlockPos pos = hit.getBlockPos();
                BlockState state = level.getBlockState(pos);
                if (state.is(ModBlocks.DRIP_PAN) && state.getValue(ModProperties.ACTIVE_PROPERTY) == ModProperties.ACTIVE_STATE.ON) {
                    level.setBlockAndUpdate(pos, state.setValue(ModProperties.ACTIVE_PROPERTY, ModProperties.ACTIVE_STATE.OFF));
                    return InteractionResult.SUCCESS.heldItemTransformedTo(ItemUtils.createFilledResult(player.getItemInHand(hand), player, ModItems.HONEY_BUCKET.getDefaultInstance()));
                }
            }
        }
        return result;
    }

}
