package net.soundsofthesun.beekeepingage.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.animal.bee.Bee;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.soundsofthesun.beekeepingage.items.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Bee.class)
public abstract  class BeeMixin extends Animal implements NeutralMob, FlyingAnimal {
    protected BeeMixin(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @WrapOperation(method = "doHurtTarget", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;hurtServer(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)Z"))
    boolean bka$beeHurtTarget(Entity instance, ServerLevel serverLevel, DamageSource damageSource, float v, Operation<Boolean> original) {
        // Block bee stings when wearing veil

        if (instance instanceof Player player && player.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.VEIL)) {
            // Bees eventually stop being angry
            if (serverLevel.getRandom().nextInt(4) == 0) this.stopBeingAngry();
            return false;
        }
        return original.call(instance, serverLevel, damageSource, v);
    }

}
