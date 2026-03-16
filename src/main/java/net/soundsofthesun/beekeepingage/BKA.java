package net.soundsofthesun.beekeepingage;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;
import net.soundsofthesun.beekeepingage.advancement.ModCriteria;
import net.soundsofthesun.beekeepingage.block.ModBlockEntities;
import net.soundsofthesun.beekeepingage.block.ModBlocks;
import net.soundsofthesun.beekeepingage.components.ModComponents;
import net.soundsofthesun.beekeepingage.effects.ModEffects;
import net.soundsofthesun.beekeepingage.entity.ModEntities;
import net.soundsofthesun.beekeepingage.entity.ModVillagers;
import net.soundsofthesun.beekeepingage.items.ModItems;

public class BKA implements ModInitializer {

    public static final String MOD_ID = "beekeepingage";
    public static final String MOD_NAME = "Beekeeping Age";
    public static Identifier id(String name) {
        return Identifier.fromNamespaceAndPath(MOD_ID, name);
    }

    @Override
    public void onInitialize() {

        ModComponents.init();
        ModBlocks.init();
        ModBlockEntities.init();
        ModItems.init();
        ModEntities.init();
        ModVillagers.init();
        ModEffects.init();
        ModCriteria.init();

    }

}
