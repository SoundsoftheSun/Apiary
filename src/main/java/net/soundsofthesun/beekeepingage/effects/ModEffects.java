package net.soundsofthesun.beekeepingage.effects;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.soundsofthesun.beekeepingage.BKA;

public class ModEffects {

    public static final int regenDuration = 100;
    public static final Holder<MobEffect> HONEY_REGENERATION =
            Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, BKA.id("honey_regeneration"), new HoneyRegeneration());

    public static final int stickyDuration = 120;
    public static final Holder<MobEffect> HONEY_STICKY =
            Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, BKA.id("honey_sticky"), new HoneySticky()
                    .addAttributeModifier(
                            Attributes.MOVEMENT_SPEED, BKA.id("effect.slowness"),
                            -0.14F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                    )
                    .addAttributeModifier(
                            Attributes.JUMP_STRENGTH, BKA.id("jump.debuff"),
                            -0.14, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                    )
            );

    public static void init() {}
}
