package net.soundsofthesun.apiary;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;
import net.soundsofthesun.apiary.blocks.ModBlockEntities;
import net.soundsofthesun.apiary.blocks.ModBlocks;
import net.soundsofthesun.apiary.effects.ModEffects;
import net.soundsofthesun.apiary.entity.ModVillagers;
import net.soundsofthesun.apiary.items.ModItems;

public class Apiary implements ModInitializer {

    public static final String MOD_ID = "apiary";
    public static final String MOD_NAME = "Apiary";
    public static Identifier id(String name) {
        return Identifier.fromNamespaceAndPath(MOD_ID, name);
    }

    @Override
    public void onInitialize() {

        ModBlocks.init();
        ModBlockEntities.init();
        ModItems.init();
        ModVillagers.init();
        ModEffects.init();

    }

}
