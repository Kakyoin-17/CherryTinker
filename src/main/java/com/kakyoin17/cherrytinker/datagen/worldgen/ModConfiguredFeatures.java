
package com.kakyoin17.cherrytinker.datagen.worldgen;

import com.kakyoin17.cherrytinker.Cherrytinker;
import com.kakyoin17.cherrytinker.content.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    //Key
    public static final ResourceKey<ConfiguredFeature<?, ?>> CHERRY_GEM_KEY = registerKey("cherry_gem");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEEP_CHERRY_GEM_KEY = registerKey("deep_cherry_gem");

    public static final ResourceKey<ConfiguredFeature<?, ?>> CHERRY_BERRY_BUSH_KEY = registerKey("cherry_berry_bush");
    //
    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        List<OreConfiguration.TargetBlockState> overworldCherryGems = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.CHERRY_GEM.get().defaultBlockState())
        );
        RuleTest deepstoneReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        List<OreConfiguration.TargetBlockState> overworldDeepCherryGems = List.of(
                OreConfiguration.target(deepstoneReplaceables, ModBlocks.DEEPSLATE_CHERRY_GEM.get().defaultBlockState())
        );
        //
        register(context, CHERRY_GEM_KEY, Feature.ORE, new OreConfiguration(overworldCherryGems, 6));
        register(context, DEEP_CHERRY_GEM_KEY, Feature.ORE, new OreConfiguration(overworldDeepCherryGems, 9));

        register(context, CHERRY_BERRY_BUSH_KEY, Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(
                                ModBlocks.CHERRY_BUSH.get().defaultBlockState().setValue(SweetBerryBushBlock.AGE, 3)
                        ))
                )
        );

    }
    //
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Cherrytinker.MOD_ID, name));
    }
    //
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}