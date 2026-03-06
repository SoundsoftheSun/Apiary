package net.soundsofthesun.apiary.effects;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.soundsofthesun.apiary.Apiary;

public class ModEffects {

    public static final Holder<MobEffect> HONEY_REGENERATION =
            Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Apiary.id("honey_regeneration"), new HoneyRegeneration());

    public static void init() {}
}
