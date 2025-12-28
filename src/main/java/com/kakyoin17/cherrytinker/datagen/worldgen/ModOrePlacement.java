package com.kakyoin17.cherrytinker.datagen.worldgen;

import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModOrePlacement {

    /**
     * 通用的矿石放置规则：
     * 1. 数量修饰符 (countModifier): 决定每个区块尝试生成多少次
     * 2. InSquare: 在 X/Z 轴上随机散布
     * 3. 高度修饰符 (heightModifier): 决定生成的高度范围
     * 4. BiomeFilter: 确保只在符合条件的生物群系生成
     */
    public static List<PlacementModifier> orePlacement(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, InSquarePlacement.spread(), heightModifier, BiomeFilter.biome());
    }

    /**
     * 普通矿石（如铁、煤）使用此方法
     * @param count 每个区块尝试生成的次数
     * @param heightModifier 高度范围
     */
    public static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier heightModifier) {
        return orePlacement(CountPlacement.of(count), heightModifier);
    }

    /**
     * 稀有矿石（如钻石、绿宝石）使用此方法
     * @param chance 几率（例如 10 表示 1/10 的几率）
     * @param heightModifier 高度范围
     */
    public static List<PlacementModifier> rareOrePlacement(int chance, PlacementModifier heightModifier) {
        return orePlacement(RarityFilter.onAverageOnceEvery(chance), heightModifier);
    }
}
