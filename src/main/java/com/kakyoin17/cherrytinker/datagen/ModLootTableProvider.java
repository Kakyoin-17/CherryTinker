package com.kakyoin17.cherrytinker.datagen;

import com.kakyoin17.cherrytinker.content.ModBlocks;
import com.kakyoin17.cherrytinker.content.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Set;

public class ModLootTableProvider {
    public static LootTableProvider create(PackOutput output) {
        return new LootTableProvider(output, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(ModBlockLoot::new, LootContextParamSets.BLOCK)
        ));
    }

    public static class ModBlockLoot extends net.minecraft.data.loot.BlockLootSubProvider {
        protected ModBlockLoot() {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags());
        }

        @Override
        protected void generate() {
            // 1. 掉落自身的方块
            this.dropSelf(ModBlocks.ENCHANTEDGOLD_BLOCK.get());
            this.dropSelf(ModBlocks.CHERRYGEM_BLOCK.get());

            // 2. 矿石：掉落对应的物品（如宝石），并处理时运
            this.add(ModBlocks.CHERRY_GEM.get(),
                    block -> createOreDrop(ModBlocks.CHERRY_GEM.get(), ModItems.CHERRYGEM.get()));
            this.add(ModBlocks.DEEPSLATE_CHERRY_GEM.get(),
                    block -> createOreDrop(ModBlocks.DEEPSLATE_CHERRY_GEM.get(), ModItems.CHERRYGEM.get()));
            //
            this.add(ModBlocks.CHERRY_BUSH.get(),
            block -> createSingleItemTable(ModItems.CHERRY.get()));
        }

        @Override
        protected Iterable<net.minecraft.world.level.block.Block> getKnownBlocks() {
            return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
        }
    }
}
