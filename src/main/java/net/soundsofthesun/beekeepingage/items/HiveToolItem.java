package net.soundsofthesun.beekeepingage.items;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;

public class HiveToolItem extends Item {
    public HiveToolItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NonNull InteractionResult useOn(UseOnContext context) {
        if (!context.getLevel().isClientSide()) return InteractionResult.PASS;
        BlockState state = context.getLevel().getBlockState(context.getClickedPos());
        if (state.is(Blocks.BEEHIVE) || state.is(Blocks.BEE_NEST)) return InteractionResult.SUCCESS;
        return InteractionResult.PASS;
    }

    @Override
    public @NonNull ItemUseAnimation getUseAnimation(@NonNull ItemStack stack) {
        return ItemUseAnimation.BUNDLE;
    }
}
