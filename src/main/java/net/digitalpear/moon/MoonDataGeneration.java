package net.digitalpear.moon;

import net.digitalpear.moon.common.datagen.MoonBiomeProvider;
import net.digitalpear.moon.common.datagen.MoonConfiguredFeatureProvider;
import net.digitalpear.moon.common.datagen.MoonPlacedFeatureProvider;
import net.digitalpear.moon.init.MoonConfiguredFeatures;
import net.digitalpear.moon.init.MoonPlacedFeatures;
import net.digitalpear.moon.init.TheMoonBiomeCreator;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class MoonDataGeneration implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(MoonConfiguredFeatureProvider::new);
        pack.addProvider(MoonPlacedFeatureProvider::new);
        pack.addProvider(MoonBiomeProvider::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, MoonConfiguredFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, MoonPlacedFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.BIOME, TheMoonBiomeCreator::bootstrap);
    }
}
