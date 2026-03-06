package net.soundsofthesun.apiary.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BeehiveBlock;
import net.soundsofthesun.apiary.client.datagen.ApiaryItemTags;
import net.soundsofthesun.apiary.items.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BeehiveBlock.class)
public class BeehiveBlockMixin {
    @WrapOperation(method = "useItemOn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z", ordinal = 0))
    boolean sun$isHoneyTool(ItemStack instance, Item item, Operation<Boolean> original) {
        // Replace shears check with custom tag
        return instance.is(ApiaryItemTags.HONEY_HARVESTERS);
    }

    @WrapOperation(method = "useItemOn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;playSound(Lnet/minecraft/world/entity/Entity;DDDLnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V"))
    void sun$playHarvestSound(Level instance, Entity entity, double x, double y, double z, SoundEvent sound, SoundSource source, float volume, float pitch, Operation<Void> original, @Local ItemStack stack) {
        // Play different sound when using hive tool
        boolean usedHiveTool = stack.is(ModItems.HIVE_TOOL);
        original.call(instance, entity, x, y, z, usedHiveTool ? SoundEvents.HONEY_BLOCK_HIT : sound, source, usedHiveTool ? 1.2F : volume, pitch);
    }

    @WrapOperation(method = "useItemOn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/CampfireBlock;isSmokeyPos(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)Z"))
    boolean sun$angerBees(Level level, BlockPos pos, Operation<Boolean> original, @Local Player player) {
        // Anger bees if not smokey or wearing beekeeper veil
        return original.call(level, pos) || player.getItemBySlot(EquipmentSlot.HEAD).is(ApiaryItemTags.BEE_PROTECTION);
    }

    @WrapOperation(method = "playerDestroy", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;hasTag(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/tags/TagKey;)Z"))
    boolean sun$isBrokenWithHiveTool(ItemStack stack, TagKey<Enchantment> tag, Operation<Boolean> original) {
        // Add hive tool check to silk touch check
        return original.call(stack, tag) || stack.is(ModItems.HIVE_TOOL);
    }
}
