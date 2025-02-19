package com.Kakyoin17.cherrytinker.custom;

import com.Kakyoin17.cherrytinker.registry.ModFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import slimeknights.tconstruct.fluids.TinkerFluids;

public class CherryGemItem extends Item  {

    public CherryGemItem(Properties properties) {
        super(properties);
    }

    public InteractionResult useOn(UseOnContext context){
        Level level = context.getLevel();
        BlockPos blockPos1 = context.getClickedPos().west(1);
        BlockState blockstate1 = level.getBlockState(blockPos1);
        BlockPos blockPos2 = context.getClickedPos().west(1);
        BlockState blockstate2 = level.getBlockState(blockPos2);
        ItemStack itemStack = context.getItemInHand();
        long timeOfDay = level.getDayTime();
        if (context.getPlayer() instanceof Player){
            if (blockstate1 == TinkerFluids.blazingBlood.getBlock().defaultBlockState()){
                if (timeOfDay>0&&timeOfDay<12000){
                    itemStack.shrink(1);
                    level.setBlockAndUpdate(blockPos1,ModFluids.suncherryfuel.getBlock().defaultBlockState());
                }return InteractionResult.sidedSuccess(level.isClientSide);
            }
            if (blockstate2 == TinkerFluids.magma.getBlock().defaultBlockState()){
                if (timeOfDay>12000&&timeOfDay<24000){
                    itemStack.shrink(1);
                    level.setBlockAndUpdate(blockPos2,ModFluids.mooncherryfuel.getBlock().defaultBlockState());
                }return InteractionResult.sidedSuccess(level.isClientSide);
            }

            }
        return  InteractionResult.sidedSuccess(level.isClientSide);
    }

    }















