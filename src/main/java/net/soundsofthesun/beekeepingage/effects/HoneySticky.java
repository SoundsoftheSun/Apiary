package net.soundsofthesun.beekeepingage.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class HoneySticky extends MobEffect {
    // No tick effects yet, just a slowness attribute modifier
    public HoneySticky() {
        super(MobEffectCategory.NEUTRAL, 0xffc800);
    }
}
