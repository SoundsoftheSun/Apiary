package net.soundsofthesun.beekeepingage.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.soundsofthesun.beekeepingage.BKA;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class BKARegistries extends FabricDynamicRegistryProvider {
    public BKARegistries(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider provider, Entries entries) {
        entries.addAll(provider.lookupOrThrow(Registries.TRIM_PATTERN));
    }

    @Override
    public @NonNull String getName() {
        return BKA.MOD_ID+" DynamicRegistryProvider";
    }
}
