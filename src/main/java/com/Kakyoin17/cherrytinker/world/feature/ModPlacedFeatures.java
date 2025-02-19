package com.Kakyoin17.cherrytinker.world.feature;


import com.Kakyoin17.cherrytinker.cherrytinker;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;


public class ModPlacedFeatures {

    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, cherrytinker.MODID);

    public static final RegistryObject<PlacedFeature> CHERRY_GEM_PLACED = PLACED_FEATURES.register("cherry_gem_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.CHERRY_GEM.getHolder().get(),
                    commonOrePlacement(7,
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80),VerticalAnchor.aboveBottom(80)))));

    private static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    private static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }

    public static final RegistryObject<PlacedFeature> cherry_PLACED =
            PLACED_FEATURES.register("cherry_placed",()->new PlacedFeature(ModConfiguredFeatures.CHERRYBUSH.getHolder().get(),
                    List.of(RarityFilter.onAverageOnceEvery(16),InSquarePlacement.spread(),
                            PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));

    public static void register(IEventBus eventBus){
        PLACED_FEATURES.register(eventBus);
    }
}

