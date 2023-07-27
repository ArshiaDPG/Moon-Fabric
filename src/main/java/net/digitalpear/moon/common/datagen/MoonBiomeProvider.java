package net.digitalpear.moon.common.datagen;


import net.digitalpear.moon.init.TheMoonBiomeCreator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.biome.Biome;

import java.util.concurrent.CompletableFuture;


public class MoonBiomeProvider extends FabricDynamicRegistryProvider {

    public MoonBiomeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {

        TheMoonBiomeCreator.biomes.forEach(configuredFeatureRegistryKey -> add(registries, entries, configuredFeatureRegistryKey));
    }
    private void add(RegistryWrapper.WrapperLookup registries, Entries entries, RegistryKey<Biome> resourceKey) {
        RegistryWrapper.Impl<Biome> configuredFeatureRegistryLookup = registries.getWrapperOrThrow(RegistryKeys.BIOME);

        entries.add(resourceKey, configuredFeatureRegistryLookup.getOrThrow(resourceKey).value());
    }
    @Override
    public String getName() {
        return "worldgen/biome";
    }
}