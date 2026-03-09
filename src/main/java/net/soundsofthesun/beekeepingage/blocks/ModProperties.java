package net.soundsofthesun.beekeepingage.blocks;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.jspecify.annotations.NonNull;

public class ModProperties {

    public enum ACTIVE_STATE implements StringRepresentable {
        OFF("off"), ON("on");

        private final String id;

        ACTIVE_STATE(String id) {
            this.id = id;
        }

        @Override
        public @NonNull String getSerializedName() {
            return this.id;
        }

    }

    public static final EnumProperty<ACTIVE_STATE> ACTIVE_PROPERTY = EnumProperty.create("active", ACTIVE_STATE.class);

}
