package net.soundsofthesun.beekeepingage.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.WaterFluid;
import net.soundsofthesun.beekeepingage.effects.ModEffects;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(WaterFluid.class)
public class WaterFluidMixin {

    @WrapMethod(method = "entityInside")
    void bka$entityInWater(Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier, Operation<Void> original) {
        original.call(level, pos, entity, effectApplier);
        if (entity instanceof LivingEntity livingEntity) {
            livingEntity.removeEffect(ModEffects.HONEY_REGENERATION);
            livingEntity.removeEffect(ModEffects.HONEY_STICKY);
        }
    }

}
