package net.digitalpear.moon.init;

import net.digitalpear.moon.Moon;
import net.digitalpear.moon.common.worldgen.CraterFeatureConfig;
import net.digitalpear.moon.common.worldgen.MoonFeature;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.ArrayList;
import java.util.List;

public class MoonConfiguredFeatures {
    public static List<RegistryKey<ConfiguredFeature<?, ?>>> features = new ArrayList<>();

    public static RegistryKey<ConfiguredFeature<?, ?>> of(String id){
        RegistryKey<ConfiguredFeature<?, ?>> registryKey = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(Moon.MOD_ID, id));
        features.add(registryKey);
        return registryKey;
    }
    public static final RegistryKey<ConfiguredFeature<?, ?>> MEGA_CRATER = of("mega_crater");
    public static final RegistryKey<ConfiguredFeature<?, ?>> LARGE_CRATER = of("large_crater");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SMALL_CRATER = of("small_crater");
    public static final RegistryKey<ConfiguredFeature<?, ?>> LUNAR_BASE = of("lunar_base");


    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> featureRegisterable) {
        ConfiguredFeatures.register(featureRegisterable, MEGA_CRATER, MoonFeature.CRATER, new CraterFeatureConfig(UniformIntProvider.create(32, 48), UniformIntProvider.create(8, 16)));
        ConfiguredFeatures.register(featureRegisterable, LARGE_CRATER, MoonFeature.CRATER, new CraterFeatureConfig(UniformIntProvider.create(12, 15), UniformIntProvider.create(3, 5)));
        ConfiguredFeatures.register(featureRegisterable, SMALL_CRATER, MoonFeature.CRATER, new CraterFeatureConfig(UniformIntProvider.create(4, 7), UniformIntProvider.create(2, 3)));
        ConfiguredFeatures.register(featureRegisterable, LUNAR_BASE, MoonFeature.LUNAR_BASE, new DefaultFeatureConfig());
    }
}
