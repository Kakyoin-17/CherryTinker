package com.Kakyoin17.cherrytinker.world.feature;


import com.Kakyoin17.cherrytinker.cherrytinker;
import com.Kakyoin17.cherrytinker.registry.ModBlocks;
import com.google.common.base.Suppliers;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class ModConfiguredFeatures {
    // ConfiguredFeature描述世界生成时候的结构和地形，例如矿物
    // CONFIGURED_FEATURE_REGISTRY 存储ConfiguredFeature注册表。
    public static final DeferredRegister<ConfiguredFeature<?,?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, cherrytinker.MODID);
    //创建一个Supplier对象，提供一个包含了两个OreConfiguration.TargetBlockState对象的列表
    //    OreConfiguration.TargetBlockState 描述矿物生成时候的目标方块和替代方块。
    //    其中第一个参数：RuleTest 表示了替代的规则。 第二个参数BlockState表示了替代的方块和方块的状态。
    //    OreFeatures.STONE_ORE_REPLACEABLES 表示替代的规则是替代：石头、花岗岩、安山岩
    //    OreFeatures.DEEPSLATE_ORE_REPLACEABLES 替代深渊的石头。
    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_CHERRY_GEMS = Suppliers.memoize(()-> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.CherryGem.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES,ModBlocks.DeepslateCherryGem.get().defaultBlockState())
    ));
    //注册描述cherry_gem矿物生成的方式，即将我们刚刚写的内容注册。
    //    ConfiguredFeature描述世界生成时候的结构和地形，例如矿物
    //    Feature.ORE 表示了生成时特定的类型。生成的逻辑。
    //    OreConfiguration 提供生成的额外数据，其中第一一个参数是一个list<TargetBlockState>类型，第二是个参数表示了每个矿脉的生成数量。
    public static final RegistryObject<ConfiguredFeature<?,?>> CHERRY_GEM = CONFIGURED_FEATURES.register("cherry_gem",
            ()->new ConfiguredFeature<>(Feature.ORE,new OreConfiguration(OVERWORLD_CHERRY_GEMS.get(),7)));

    public static final RegistryObject<ConfiguredFeature<?,?>> CHERRYBUSH =
            CONFIGURED_FEATURES.register("cherrybush",
                    () -> new ConfiguredFeature<>(Feature.FLOWER,
                            new RandomPatchConfiguration(32,6,2,
                                    PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                            new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.Cherry_Bush.get()))))));



    public static void register(IEventBus eventBus){
        CONFIGURED_FEATURES.register(eventBus);
    }

}
