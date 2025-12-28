package com.kakyoin17.cherrytinker.datagen;

import com.kakyoin17.cherrytinker.content.ModBlocks;
import com.kakyoin17.cherrytinker.content.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import slimeknights.tconstruct.world.TinkerWorld;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        // 1. 有序合成 (Shaped)
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD,ModItems.ENCHANTED_GOLDEN_CARROT.get(),8)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.GOLDEN_CARROT)
                .define('B', ModItems.ENCHANTEDGOLD.get())
                .unlockedBy("has item",has(ModItems.ENCHANTEDGOLD.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocks.ENCHANTEDGOLD_BLOCK.get(),1)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.GOLD_BLOCK)
                .define('B', Items.ENCHANTED_GOLDEN_APPLE)
                .unlockedBy("has item",has(Items.ENCHANTED_GOLDEN_APPLE))
                .save(consumer,ResourceLocation.fromNamespaceAndPath("cherrytinker", "enchantedgold_block_from_apple"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItems.ENCHANTEDGOLD.get(),4)
                .pattern("ABA")
                .pattern("CDE")
                .pattern("AFA")
                .define('A', Items.EXPERIENCE_BOTTLE)
                .define('B', Items.AMETHYST_SHARD)
                .define('C', TinkerWorld.ichorGeode)
                .define('D', Items.GOLD_BLOCK)
                .define('E',TinkerWorld.skyGeode)
                .define('F',TinkerWorld.earthGeode )
                .unlockedBy("has item",has(Items.EXPERIENCE_BOTTLE))
                .save(consumer, ResourceLocation.fromNamespaceAndPath("cherrytinker", "enchantedgold_from_geodes"));

        // 2. 无序合成 (Shapeless)
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS,ModBlocks.CHERRYGEM_BLOCK.get(), 1)
                .requires(ModItems.CHERRYGEM.get(),9)
                .unlockedBy("has_item", has(ModItems.CHERRYGEM.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.CHERRYGEM.get(), 9)
                .requires(ModBlocks.CHERRYGEM_BLOCK.get())
                .unlockedBy("has_block", has(ModBlocks.CHERRYGEM_BLOCK.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ENCHANTEDGOLD_BLOCK.get())
                .requires(ModItems.ENCHANTEDGOLD.get(), 9)
                .unlockedBy("has_item", has(ModItems.ENCHANTEDGOLD.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ENCHANTEDGOLD.get(), 9)
                .requires(ModBlocks.ENCHANTEDGOLD_BLOCK.get())
                .unlockedBy("has_block", has(ModBlocks.ENCHANTEDGOLD_BLOCK.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MONSTER_CLUSTER.get(), 1)
                .requires(Items.ROTTEN_FLESH)
                .requires(Items.BONE)
                .requires(Items.STRING)
                .requires(Items.GUNPOWDER)
                .requires(Items.ENDER_PEARL)
                .unlockedBy("has_item", has(Items.ROTTEN_FLESH))
                .save(consumer);
    }

}
