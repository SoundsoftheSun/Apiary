package net.soundsofthesun.beekeepingage;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;
import net.soundsofthesun.beekeepingage.advancement.ModCriteria;
import net.soundsofthesun.beekeepingage.block.ModBlockEntities;
import net.soundsofthesun.beekeepingage.block.ModBlocks;
import net.soundsofthesun.beekeepingage.components.ModComponents;
import net.soundsofthesun.beekeepingage.effects.ModEffects;
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
        // Not adding the flower golem until i can think of a good reason for it to exist lol
        // ModEntities.init();
        ModVillagers.init();
        ModEffects.init();
        ModCriteria.init();

    }

}
