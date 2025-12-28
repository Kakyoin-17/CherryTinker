package com.kakyoin17.cherrytinker.datagen.worldgen;

import com.kakyoin17.cherrytinker.Cherrytinker;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {
    //
    public static final ResourceKey<PlacedFeature> CHERRY_GEM_PLACED_KEY = registerKey("cherry_gem_placed");
    public static final ResourceKey<PlacedFeature> DEEP_CHERRY_GEM_PLACED_KEY = registerKey("deep_cherry_gem_placed");

    public static final ResourceKey<PlacedFeature> CHERRY_BERRY_BUSH_PLACED_KEY = registerKey("cherry_berry_bush_placed");
    //
    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        var configuredFeatureRegistry = context.lookup(Registries.CONFIGURED_FEATURE);
        register(context, CHERRY_GEM_PLACED_KEY,
                configuredFeatureRegistry.getOrThrow(ModConfiguredFeatures.CHERRY_GEM_KEY),
                ModOrePlacement.commonOrePlacement(4,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(50))));
        register(context, DEEP_CHERRY_GEM_PLACED_KEY,
                configuredFeatureRegistry.getOrThrow(ModConfiguredFeatures.DEEP_CHERRY_GEM_KEY),
                ModOrePlacement.commonOrePlacement(2,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(0))));

        register(context, CHERRY_BERRY_BUSH_PLACED_KEY,
                configuredFeatureRegistry.getOrThrow(ModConfiguredFeatures.CHERRY_BERRY_BUSH_KEY),
                List.of(
                        //
                        RarityFilter.onAverageOnceEvery(32),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE, // 生成在地表
                        BiomeFilter.biome() // 生物群系检查
                ));
    }
    //
    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE,ResourceLocation.fromNamespaceAndPath(Cherrytinker.MOD_ID, name));
    }
    //
    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}