package net.soundsofthesun.apiary.blocks;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.jspecify.annotations.NonNull;

public class ModProperties {

    public enum HONEY_STATE implements StringRepresentable {
        NONE(0, "none"), BOTTOM(1, "bottom"), TOP(2, "top"), BOTH(3, "both");

        private final int index;
        private final String id;

        HONEY_STATE(int index, String id) {
            this.index = index;
            this.id = id;
        }

        @Override
        public @NonNull String getSerializedName() {
            return this.id;
        }

        public static final StringRepresentable.EnumCodec<HONEY_STATE> CODEC = StringRepresentable.fromEnum(HONEY_STATE::values);

    }

    public static final EnumProperty<HONEY_STATE> HONEY_PROPERTY = EnumProperty.create("honey_state", HONEY_STATE.class);

}
