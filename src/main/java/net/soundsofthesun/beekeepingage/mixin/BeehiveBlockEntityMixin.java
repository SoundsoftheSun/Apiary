package net.soundsofthesun.beekeepingage.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BeehiveBlockEntity.class)
public class BeehiveBlockEntityMixin {

    @WrapOperation(method = "releaseOccupant", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;setBlockAndUpdate(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)Z"))
    private static boolean bka$blockHoneyIfSmokey(Level instance, BlockPos pos, BlockState state, Operation<Boolean> original) {
        // Beehives don't increase honey when smokey :)
        boolean bl = false;
        if (!CampfireBlock.isSmokeyPos(instance, pos)) bl = original.call(instance, pos, state);
        return bl;
    }

}
