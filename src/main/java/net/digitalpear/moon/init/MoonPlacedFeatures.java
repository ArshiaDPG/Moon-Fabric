package net.digitalpear.moon.init;

import net.digitalpear.moon.Moon;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

import java.util.ArrayList;
import java.util.List;

public class MoonPlacedFeatures {
    public static List<RegistryKey<PlacedFeature>> features = new ArrayList<>();

    public static RegistryKey<PlacedFeature> of(String id){
        RegistryKey<PlacedFeature> registryKey = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(Moon.MOD_ID, id));
        features.add(registryKey);
        return registryKey;
    }


    public static final RegistryKey<PlacedFeature> CRATER_MEGA = of("crater_mega");
    public static final RegistryKey<PlacedFeature> CRATER_LARGE = of("crater_large");
    public static final RegistryKey<PlacedFeature> CRATER_SMALL = of("crater_small");
    public static final RegistryKey<PlacedFeature> LUNAR_BASE = of("lunar_base");

    public static void bootstrap(Registerable<PlacedFeature> featureRegisterable) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> registryEntryLookup = featureRegisterable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        RegistryEntry<ConfiguredFeature<?, ?>> craterMega = registryEntryLookup.getOrThrow(MoonConfiguredFeatures.MEGA_CRATER);
        RegistryEntry<ConfiguredFeature<?, ?>> craterLarge = registryEntryLookup.getOrThrow(MoonConfiguredFeatures.LARGE_CRATER);
        RegistryEntry<ConfiguredFeature<?, ?>> craterSmall = registryEntryLookup.getOrThrow(MoonConfiguredFeatures.SMALL_CRATER);
        RegistryEntry<ConfiguredFeature<?, ?>> lunarBase = registryEntryLookup.getOrThrow(MoonConfiguredFeatures.LUNAR_BASE);


        PlacedFeatures.register(featureRegisterable, CRATER_MEGA, craterMega, RarityFilterPlacementModifier.of(256), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_120_RANGE, BiomePlacementModifier.of());
        PlacedFeatures.register(featureRegisterable, CRATER_LARGE, craterLarge, RarityFilterPlacementModifier.of(12), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_120_RANGE, BiomePlacementModifier.of());
        PlacedFeatures.register(featureRegisterable, CRATER_SMALL, craterSmall, RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_120_RANGE, BiomePlacementModifier.of());
        PlacedFeatures.register(featureRegisterable, LUNAR_BASE, lunarBase, RarityFilterPlacementModifier.of(1024), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_120_RANGE, BiomePlacementModifier.of());
    }
}
