package net.digitalpear.moon.common.datagen;



import net.digitalpear.moon.init.MoonPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.concurrent.CompletableFuture;

public class MoonPlacedFeatureProvider extends FabricDynamicRegistryProvider {


    public MoonPlacedFeatureProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {

        MoonPlacedFeatures.features.forEach(placedFeatureRegistryKey -> add(registries, entries, placedFeatureRegistryKey));
    }

    private void add(RegistryWrapper.WrapperLookup registries, Entries entries, RegistryKey<PlacedFeature> resourceKey) {
        final RegistryWrapper.Impl<PlacedFeature> configuredFeatureRegistryLookup = registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE);

        entries.add(resourceKey, configuredFeatureRegistryLookup.getOrThrow(resourceKey).value());
    }

    @Override
    public String getName() {
        return "worldgen/placed_feature";
    }
}