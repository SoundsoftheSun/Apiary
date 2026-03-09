package net.soundsofthesun.apiary.advancement;

import net.minecraft.advancements.CriteriaTriggers;
import net.soundsofthesun.apiary.Apiary;

public class ModCriteria {
    public static final UseExtractorCriterion USE_EXTRACTOR = CriteriaTriggers.register(Apiary.MOD_ID + ":use_extractor", new UseExtractorCriterion());
    public static final UseHiveToolCriterion USE_HIVE_TOOL = CriteriaTriggers.register(Apiary.MOD_ID + ":use_hive_tool", new UseHiveToolCriterion());
    public static final HarvestWithVeilCriterion HARVEST_WITH_VEIL = CriteriaTriggers.register(Apiary.MOD_ID + ":harvest_veil", new HarvestWithVeilCriterion());
    public static final HoneyBucketClutchCriterion HONEY_CLUTCH = CriteriaTriggers.register(Apiary.MOD_ID + ":honey_clutch", new HoneyBucketClutchCriterion());

    public static void init() {}
}
