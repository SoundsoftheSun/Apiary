package net.soundsofthesun.apiary.effects;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class HoneyRegeneration extends MobEffect {
    public HoneyRegeneration() {
        super(MobEffectCategory.BENEFICIAL, 0xffe600);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % 20 == 0;
    }

    @Override
    public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
        if (entity instanceof Player player) player.heal(0.5F);
        return super.applyEffectTick(level, entity, amplifier);
    }
}
