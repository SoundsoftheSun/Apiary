package net.soundsofthesun.beekeepingage.components;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.api.item.v1.ComponentTooltipAppenderRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;
import net.soundsofthesun.beekeepingage.BKA;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class ModComponents {

    public record RestoredComponent(Boolean bool) implements TooltipProvider {
        @Override
        public void addToTooltip(Item.@NonNull TooltipContext context, @NonNull Consumer<Component> tooltipAdder, @NonNull TooltipFlag flag, @NonNull DataComponentGetter componentGetter) {
            if (bool) tooltipAdder.accept(Component.translatable(BKA.MOD_ID+".tooltip.restored").withStyle(ChatFormatting.GOLD));
        }
        public static final RestoredComponent DEFAULT = new RestoredComponent(false);
        public static final Codec<RestoredComponent> CODEC = RecordCodecBuilder.create(builder -> builder.group(
                Codec.BOOL.fieldOf("restored").forGetter(RestoredComponent::bool)
        ).apply(builder, RestoredComponent::new));
    }

    public static final DataComponentType<RestoredComponent> BEEHIVE_RESTORED = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            BKA.id("restored"),
            DataComponentType.<RestoredComponent>builder().persistent(RestoredComponent.CODEC).build()
    );

    public static void init() {
        ComponentTooltipAppenderRegistry.addFirst(BEEHIVE_RESTORED);
    }
}
