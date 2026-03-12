package net.soundsofthesun.beekeepingage.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Attackable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.waypoints.WaypointTransmitter;
import net.soundsofthesun.beekeepingage.advancement.ModCriteria;
import net.soundsofthesun.beekeepingage.block.ModBlocks;
import net.soundsofthesun.beekeepingage.client.datagen.BKAFluidTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Attackable, WaypointTransmitter {
    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @WrapOperation(method = "shouldTravelInFluid", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isInLava()Z"))
    boolean bka$isInHoney(LivingEntity instance, Operation<Boolean> original) {
        // Copy fluid movement behavior from lava
        return original.call(instance) || this.updateFluidHeightAndDoFluidPushing(BKAFluidTags.HONEY_TAG, 0.014D);
    }

    @WrapOperation(method = "causeFallDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;calculateFallDamage(DF)I"))
    int sun$negateHoneyFallDamage(LivingEntity instance, double fallDistance, float damageMultiplier, Operation<Integer> original) {
        // Negate fall damage when landing in honey in the nether
        int og = original.call(instance, fallDistance, damageMultiplier);
        if (og > 0 && this.getInBlockState().is(ModBlocks.HONEY_FLUID_BLOCK) && this.level().dimension() == Level.NETHER) {
            if ((((LivingEntity)(Object)this)) instanceof ServerPlayer serverPlayer && serverPlayer.getHealth() - og <= 0) {
                ModCriteria.HONEY_CLUTCH.trigger(serverPlayer);
            }
            return 0;
        }
        return og;
    }

}
