package net.soundsofthesun.beekeepingage.advancement;

import net.minecraft.advancements.CriteriaTriggers;
import net.soundsofthesun.beekeepingage.BKA;

public class ModCriteria {
    public static final UseExtractorCriterion USE_EXTRACTOR = CriteriaTriggers.register(BKA.MOD_ID + ":use_extractor", new UseExtractorCriterion());
    public static final UseHiveToolCriterion USE_HIVE_TOOL = CriteriaTriggers.register(BKA.MOD_ID + ":use_hive_tool", new UseHiveToolCriterion());
    public static final HarvestWithVeilCriterion HARVEST_WITH_VEIL = CriteriaTriggers.register(BKA.MOD_ID + ":harvest_veil", new HarvestWithVeilCriterion());
    public static final HoneyBucketClutchCriterion HONEY_CLUTCH = CriteriaTriggers.register(BKA.MOD_ID + ":honey_clutch", new HoneyBucketClutchCriterion());

    public static void init() {}
}
