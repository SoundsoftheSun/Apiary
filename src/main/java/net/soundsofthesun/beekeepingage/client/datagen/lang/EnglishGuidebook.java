package net.soundsofthesun.beekeepingage.client.datagen.lang;

public class EnglishGuidebook {
    public static String getText() {
        return """
                # Beekeeper Villager
                
                Beekeeping Age adds a new villager profession: the Beekeeper Villager! To get a beekeeper villager, simply place a Honey Extractor block near an unemployed villager. This villager offers new items from the mod, as well as new trades to incentivize beekeeping.
                
                
                # Honey Extractor + Drip Pan
                
                The honey extractor is also a functional block. Place a drip pan beneath the spigot of a honey extractor, and place four honeycomb inside the honey extractor to obtain liquid honey!
                
                
                # Liquid Honey + Honey Bucket
                
                After using the honey extractor, the drip pan will be filled with liquid honey. Collect the liquid honey with a bucket! Liquid honey has special properties: Standing in liquid honey will grant you a regenerative effect, but it will also make you Sticky! Liquid honey will not spread very far or break your fall, unless you're in the Nether!
                
                
                # Beekeeping
                
                The Hive Tool is now the preferred tool for beekeeping. Harvesting with the Hive Tool will yield more honey than shears, and breaking hives with the Hive Tool will keep the hive's properties like silk touch. Beehives that are smokey from campfires will not produce honey until the smoke is removed. Alternatively, you can equip the new Beekeeping Veil to prevent bees from attacking you while you harvest and protect you from disgruntled bees.\s
                
                
                # Apiary
                
                In your world, you will find the Apiary which generates in Flower Forests. You may also encounter the Abandoned Apiary, where the beehives have been abandoned and the beekeeper has been zombified. Restore the abandoned beehives with honeycomb for increased honey production, but be sure to pick it up with a hive tool!
                
                
                # Bonus
                
                This guide doubles as an armor trim! Also, be sure to check out the new advancements!
                """
                .replaceAll("# ", "");
    }
}
