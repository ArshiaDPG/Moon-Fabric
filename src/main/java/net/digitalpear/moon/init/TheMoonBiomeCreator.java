package net.digitalpear.moon.init;


import net.digitalpear.moon.Moon;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.ArrayList;
import java.util.List;

public class TheMoonBiomeCreator {

    private static Biome createMoonBiome(GenerationSettings.LookupBackedBuilder builder) {
        SpawnSettings.Builder builder2 = new SpawnSettings.Builder().spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.COW, 8, 1, 5));
        builder2.creatureSpawnProbability(0.07f);
        return new Biome.Builder().precipitation(false).temperature(-0.5f).downfall(0.5f).effects(new BiomeEffects.Builder().waterColor(0).waterFogColor(0xFFFFFF).fogColor(0xA080A0).skyColor(0).foliageColor(0xFFFFFF).grassColor(0x696969).particleConfig(new BiomeParticleConfig(ParticleTypes.WHITE_ASH, 0.001f)).moodSound(BiomeMoodSound.CAVE).build()).spawnSettings(builder2.build()).generationSettings(builder.build()).build();
    }

    public static Biome createTheMoon(RegistryEntryLookup<PlacedFeature> featureLookup, RegistryEntryLookup<ConfiguredCarver<?>> carverLookup) {
        GenerationSettings.LookupBackedBuilder lookupBackedBuilder = new GenerationSettings.LookupBackedBuilder(featureLookup, carverLookup);
        lookupBackedBuilder.feature(GenerationStep.Feature.RAW_GENERATION, MoonPlacedFeatures.CRATER_MEGA);
        lookupBackedBuilder.feature(GenerationStep.Feature.RAW_GENERATION, MoonPlacedFeatures.CRATER_LARGE);
        lookupBackedBuilder.feature(GenerationStep.Feature.RAW_GENERATION, MoonPlacedFeatures.CRATER_SMALL);
        lookupBackedBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MoonPlacedFeatures.LUNAR_BASE);
        return TheMoonBiomeCreator.createMoonBiome(lookupBackedBuilder);
    }
    public static List<RegistryKey<Biome>> biomes = new ArrayList<>();
    public static RegistryKey<Biome> register(String id){
        RegistryKey<Biome> registryKey = RegistryKey.of(RegistryKeys.BIOME, new Identifier(Moon.MOD_ID, id));
        biomes.add(registryKey);
        return registryKey;
    }
    public static final RegistryKey<Biome> THE_MOON = register("the_moon");



    public static void bootstrap(Registerable<Biome> bootstapContext) {
        RegistryEntryLookup<PlacedFeature> placeddFeatureHolder = bootstapContext.getRegistryLookup(RegistryKeys.PLACED_FEATURE);
        RegistryEntryLookup<ConfiguredCarver<?>> configuredWorldCarverHolderGetter = bootstapContext.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER);

        bootstapContext.register(THE_MOON, createTheMoon(placeddFeatureHolder, configuredWorldCarverHolderGetter));
    }
}

