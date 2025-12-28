package com.kakyoin17.cherrytinker.datagen;

import com.kakyoin17.cherrytinker.Cherrytinker;
import com.kakyoin17.cherrytinker.content.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider  extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Cherrytinker.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // 设置哪些方块可以用镐子挖掘
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.ENCHANTEDGOLD_BLOCK.get())
                .add(ModBlocks.CHERRYGEM_BLOCK.get())
                .add(ModBlocks.CHERRY_GEM.get())
                .add(ModBlocks.DEEPSLATE_CHERRY_GEM.get());

        // 设置挖掘等级：需要铁镐
        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.CHERRYGEM_BLOCK.get())
                .add(ModBlocks.CHERRY_GEM.get())
                .add(ModBlocks.DEEPSLATE_CHERRY_GEM.get());

        // 设置挖掘等级：需要钻石镐
        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.ENCHANTEDGOLD_BLOCK.get());
    }
}
