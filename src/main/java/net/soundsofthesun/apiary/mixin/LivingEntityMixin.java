package net.soundsofthesun.apiary.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.Holder;
import net.minecraft.world.damagesource.CombatTracker;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Attackable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.waypoints.WaypointTransmitter;
import net.soundsofthesun.apiary.blocks.ModBlocks;
import net.soundsofthesun.apiary.client.datagen.ApiaryFluidTags;
import net.soundsofthesun.apiary.effects.ModEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Collection;
import java.util.Map;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Attackable, WaypointTransmitter {
    @Shadow
    public abstract Collection<MobEffectInstance> getActiveEffects();

    @Shadow
    public abstract Map<Holder<MobEffect>, MobEffectInstance> getActiveEffectsMap();

    @Shadow
    public abstract boolean addEffect(MobEffectInstance effectInstance);

    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    // Copy movement behavior for lava
    @WrapOperation(method = "shouldTravelInFluid", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isInLava()Z"))
    boolean sun$isInHoney(LivingEntity instance, Operation<Boolean> original) {
        return original.call(instance) || this.updateFluidHeightAndDoFluidPushing(ApiaryFluidTags.HONEY_TAG, 0.014D);
    }

    @WrapMethod(method = "causeFallDamage")
    boolean sun$negateHoneyFallDamage(double fallDistance, float damageMultiplier, DamageSource damageSource, Operation<Boolean> original) {
        if (this.getInBlockState().is(ModBlocks.HONEY_FLUID_BLOCK) && this.level().dimension() == Level.NETHER) return false;
        return original.call(fallDistance, damageMultiplier, damageSource);
    }

    @WrapOperation(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/damagesource/CombatTracker;recheckStatus()V"))//I'm not sure if this is the best place to handle this
    void sun$checkInHoneyEffects(CombatTracker instance, Operation<Void> original) {
        if (this.getInBlockState().is(ModBlocks.HONEY_FLUID_BLOCK) && !this.getActiveEffectsMap().containsKey(ModEffects.HONEY_REGENERATION)) {
            this.addEffect(new MobEffectInstance(ModEffects.HONEY_REGENERATION, 100));
        }
    }

}
