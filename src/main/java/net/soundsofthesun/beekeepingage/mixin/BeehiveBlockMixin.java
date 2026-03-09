package net.soundsofthesun.beekeepingage.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
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
import net.soundsofthesun.beekeepingage.advancement.ModCriteria;
import net.soundsofthesun.beekeepingage.client.datagen.BKAItemTags;
import net.soundsofthesun.beekeepingage.items.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BeehiveBlock.class)
public class BeehiveBlockMixin {
    @WrapOperation(method = "useItemOn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z", ordinal = 0))
    boolean bka$isHoneyTool(ItemStack instance, Item item, Operation<Boolean> original, @Local(argsOnly = true) Player player) {
        // Add hive tool to harvesters
        boolean bl = instance.is(ModItems.HIVE_TOOL);
        if (player instanceof ServerPlayer serverPlayer && bl) ModCriteria.USE_HIVE_TOOL.trigger(serverPlayer);
        return original.call(instance, item) || bl;
    }

    @WrapOperation(method = "useItemOn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;playSound(Lnet/minecraft/world/entity/Entity;DDDLnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V"))
    void bka$playHarvestSound(Level instance, Entity entity, double x, double y, double z, SoundEvent sound, SoundSource source, float volume, float pitch, Operation<Void> original, @Local(argsOnly = true) ItemStack stack) {
        // Play different sound when using hive tool
        boolean usedHiveTool = stack.is(ModItems.HIVE_TOOL);
        original.call(instance, entity, x, y, z, usedHiveTool ? SoundEvents.HONEY_BLOCK_HIT : sound, source, usedHiveTool ? 1.2F : volume, pitch);
    }

    @WrapOperation(method = "useItemOn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/CampfireBlock;isSmokeyPos(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)Z"))
    boolean bka$angerBees(Level level, BlockPos pos, Operation<Boolean> original, @Local(argsOnly = true) Player player) {
        // Anger bees if not smokey or wearing beekeeper veil
        boolean bl = player.getItemBySlot(EquipmentSlot.HEAD).is(BKAItemTags.BEE_PROTECTION);
        if (player instanceof ServerPlayer serverPlayer && bl) ModCriteria.HARVEST_WITH_VEIL.trigger(serverPlayer);
        return original.call(level, pos) || bl;
    }

    @WrapOperation(method = "playerDestroy", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;hasTag(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/tags/TagKey;)Z"))
    boolean bka$isBrokenWithHiveTool(ItemStack stack, TagKey<Enchantment> tag, Operation<Boolean> original, @Local(argsOnly = true) Player player) {
        // Add hive tool check to silk touch check
        boolean bl = stack.is(ModItems.HIVE_TOOL);
        if (player instanceof ServerPlayer serverPlayer && bl) ModCriteria.USE_HIVE_TOOL.trigger(serverPlayer);
        return original.call(stack, tag) || bl;
    }
}
