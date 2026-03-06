package net.soundsofthesun.apiary.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.animal.bee.Bee;
import net.minecraft.world.entity.player.Player;
import net.soundsofthesun.apiary.items.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Bee.class)
public class BeeMixin {

    @WrapOperation(method = "doHurtTarget", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;hurtServer(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)Z"))
    boolean sun$beeHurtTarget(Entity instance, ServerLevel serverLevel, DamageSource damageSource, float v, Operation<Boolean> original) {
        // Bee strings do quarter damage if wearing veil
        return original.call(instance, serverLevel, damageSource, (instance instanceof Player player && player.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.VEIL)) ? v*0.25F : v);
    }

}
