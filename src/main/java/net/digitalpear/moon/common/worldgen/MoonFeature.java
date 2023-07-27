package net.digitalpear.moon.common.worldgen;

import net.digitalpear.moon.Moon;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

public class MoonFeature {
    public static final Feature<CraterFeatureConfig> CRATER = register("crater", new CraterFeature(CraterFeatureConfig.CODEC));
    public static final Feature<DefaultFeatureConfig> LUNAR_BASE = register("lunar_base", new LunarBaseFeature(DefaultFeatureConfig.CODEC));


    private static <C extends FeatureConfig, F extends Feature<C>> Feature register(String name, F feature) {
        return Registry.register(Registries.FEATURE, new Identifier(Moon.MOD_ID, name), feature);
    }

    public static void init() {
    }
}
