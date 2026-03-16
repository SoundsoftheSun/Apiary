package net.soundsofthesun.beekeepingage.entity.flowergolem;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import org.jspecify.annotations.NonNull;

import java.util.function.IntFunction;

public enum FlowerGolemState implements StringRepresentable {
    NONE("none", 0),
    IDLE("idle", 1),
    SHAKE("shake", 2),
    OFFER("offer", 3);

    public static final Codec<FlowerGolemState> CODEC = StringRepresentable.fromEnum(FlowerGolemState::values);
    private static final IntFunction<FlowerGolemState> BY_ID = ByIdMap.continuous(FlowerGolemState::id, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
    public static final StreamCodec<ByteBuf, FlowerGolemState> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, FlowerGolemState::id);
    private final String name;
    private final int id;

    FlowerGolemState(final String name, final int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public @NonNull String getSerializedName() {
        return this.name;
    }

    private int id() {
        return this.id;
    }
}
