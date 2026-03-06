package net.soundsofthesun.apiary.effects;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.soundsofthesun.apiary.Apiary;

public class ModEffects {

    public static final Holder<MobEffect> HONEY_REGENERATION =
            Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Apiary.id("honey_regeneration"), new HoneyRegeneration());

    public static final Holder<MobEffect> HONEY_STICKY =
            Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Apiary.id("honey_sticky"), new HoneySticky()
                    .addAttributeModifier(
                            Attributes.MOVEMENT_SPEED, Apiary.id("effect.slowness"),
                            -0.14F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                    )
            );

    public static void init() {}
}
