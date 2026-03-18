package net.soundsofthesun.beekeepingage.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricTrackedDataRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.soundsofthesun.beekeepingage.BKA;
import net.soundsofthesun.beekeepingage.entity.flowergolem.FlowerGolem;
import net.soundsofthesun.beekeepingage.entity.flowergolem.FlowerGolemState;

public class ModEntities {
    private static final ResourceKey<EntityType<?>> FLOWER_GOLEM_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, BKA.id("flower_golem"));

    public static final EntityType<FlowerGolem> FLOWER_GOLEM = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            BKA.id("flower_golem"),
            EntityType.Builder.of(FlowerGolem::new, MobCategory.CREATURE)
                    .sized(0.85f, 1.4f).build(FLOWER_GOLEM_KEY));

    public static final EntityDataSerializer<FlowerGolemState> FLOWER_GOLEM_STATE = EntityDataSerializer.forValueType(FlowerGolemState.STREAM_CODEC);

    public static void init() {
        FabricDefaultAttributeRegistry.register(FLOWER_GOLEM, FlowerGolem.createAttributes());
        FabricTrackedDataRegistry.register(BKA.id("flower_golem_state"), FLOWER_GOLEM_STATE);
    }
}
