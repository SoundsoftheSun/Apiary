package net.soundsofthesun.apiary.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SmithingTemplateItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.soundsofthesun.apiary.Apiary;
import net.soundsofthesun.apiary.items.ModItems;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.Mixin;

import java.util.function.Consumer;

@Mixin(SmithingTemplateItem.class)
public abstract class SmithingTemplateItemMixin extends Item {
    public SmithingTemplateItemMixin(Properties properties) {
        super(properties);
    }

    @Override
    public @NonNull InteractionResult use(Level level, Player player, InteractionHand hand) {

        return super.use(level, player, hand);
    }

    @WrapMethod(method = "appendHoverText")
    void sun$appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag, Operation<Void> original) {
        if (stack.is(ModItems.BEEKEEPER_SMITHING_TEMPLATE)) tooltipAdder.accept(Component.translatable(Apiary.MOD_ID+".template_description").withStyle(ChatFormatting.GRAY));
        original.call(stack, context, tooltipDisplay, tooltipAdder, flag);
    }

}
