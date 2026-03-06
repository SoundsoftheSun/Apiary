package net.soundsofthesun.apiary.blocks;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.jspecify.annotations.NonNull;

public class ModProperties {

    public enum EXTRACTOR_STATE implements StringRepresentable {
        OFF(0, "off"), ON(1, "on");

        private final int index;
        private final String id;

        EXTRACTOR_STATE(int index, String id) {
            this.index = index;
            this.id = id;
        }

        @Override
        public @NonNull String getSerializedName() {
            return this.id;
        }

        public static final StringRepresentable.EnumCodec<EXTRACTOR_STATE> CODEC = StringRepresentable.fromEnum(EXTRACTOR_STATE::values);

    }

    public static final EnumProperty<EXTRACTOR_STATE> EXTRACTOR_PROPERTY = EnumProperty.create("active", EXTRACTOR_STATE.class);

}
