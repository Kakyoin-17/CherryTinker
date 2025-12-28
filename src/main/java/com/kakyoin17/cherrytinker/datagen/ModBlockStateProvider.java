package com.kakyoin17.cherrytinker.datagen;

import com.kakyoin17.cherrytinker.Cherrytinker;
import com.kakyoin17.cherrytinker.content.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Cherrytinker.MOD_ID, exFileHelper);
    }
    @Override
    protected void registerStatesAndModels() {
        // 普通立方体方块
        simpleBlockWithItem(ModBlocks.ENCHANTEDGOLD_BLOCK.get());
        simpleBlockWithItem(ModBlocks.CHERRYGEM_BLOCK.get());
        simpleBlockWithItem(ModBlocks.CHERRY_GEM.get());
        simpleBlockWithItem(ModBlocks.DEEPSLATE_CHERRY_GEM.get());

    }

    // 辅助方法：自动生成方块模型和对应的 Item 模型
    private void simpleBlockWithItem(net.minecraft.world.level.block.Block block) {
        simpleBlock(block, cubeAll(block));
        simpleBlockItem(block, cubeAll(block));
    }
}
