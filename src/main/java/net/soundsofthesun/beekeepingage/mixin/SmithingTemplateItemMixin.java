package net.soundsofthesun.beekeepingage.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.dialog.Dialog;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SmithingTemplateItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.soundsofthesun.beekeepingage.BKA;
import net.soundsofthesun.beekeepingage.items.ModItems;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.function.Consumer;

@Mixin(SmithingTemplateItem.class)
public abstract class SmithingTemplateItemMixin extends Item {
    public SmithingTemplateItemMixin(Properties properties) {
        super(properties);
    }

    @Unique
    ResourceKey<Dialog> guideDialogKey = ResourceKey.create(Registries.DIALOG, BKA.id("dialog"));

    @Override
    public @NonNull InteractionResult use(@NonNull Level level, Player player, @NonNull InteractionHand hand) {
        if (player.getItemInHand(hand).is(ModItems.BEEKEEPER_SMITHING_TEMPLATE)) level.registryAccess().lookup(Registries.DIALOG).flatMap(registry -> registry.get(guideDialogKey)).ifPresent(player::openDialog);
        return super.use(level, player, hand);
    }

    @WrapMethod(method = "appendHoverText")
    void bka$appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag, Operation<Void> original) {
        if (stack.is(ModItems.BEEKEEPER_SMITHING_TEMPLATE)) tooltipAdder.accept(Component.translatable(BKA.MOD_ID+".template_description").withStyle(ChatFormatting.GRAY));
        original.call(stack, context, tooltipDisplay, tooltipAdder, flag);
    }

}
