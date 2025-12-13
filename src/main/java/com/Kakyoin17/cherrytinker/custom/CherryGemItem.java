package com.Kakyoin17.cherrytinker.custom;

import com.Kakyoin17.cherrytinker.registry.ModBlocks;
import com.Kakyoin17.cherrytinker.registry.ModFluids;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.List;

public class CherryGemItem extends Item {
    private static final ResourceLocation TINKERS_BLAZING_BLOOD = new ResourceLocation("tconstruct", "blazing_blood");
    private static final ResourceLocation TINKERS_MAGMA = new ResourceLocation("tconstruct", "magma"); // 如果不对，进游戏F3+H查看
    public CherryGemItem(Properties properties) {
        super(properties);
    }
    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        Level level = entity.level;
        if (!level.isClientSide) {
            if (entity.tickCount % 4 == 0) {
                checkConversion(level, entity);
            }
        }
        return super.onEntityItemUpdate(stack, entity);
    }
    private void checkConversion(Level level, ItemEntity entity) {
        BlockPos pos = entity.blockPosition();
        FluidState fluidState = level.getFluidState(pos);
        if (fluidState.isEmpty()) {
            BlockPos below = pos.below();
            FluidState belowState = level.getFluidState(below);
            if (!belowState.isEmpty()) {
                pos = below;
                fluidState = belowState;
            }
        }
        if (!fluidState.isSource()) {
            return;
        }
        ResourceLocation currentFluidId = ForgeRegistries.FLUIDS.getKey(fluidState.getType());
        if (currentFluidId == null) return;
        long time = level.getDayTime() % 24000;
        boolean isDay = time >= 0 && time < 13000; // 稍微放宽一点白天的判定
        Block targetBlock = null;
        if (isDay && currentFluidId.equals(TINKERS_BLAZING_BLOOD)) {
            targetBlock = ModFluids.suncherryfuel.getBlock();
        }
        else if (!isDay && currentFluidId.equals(TINKERS_MAGMA)) {
            targetBlock = ModFluids.mooncherryfuel.getBlock();
        }
        if (targetBlock != null) {
            performTransformation(level, pos, entity, targetBlock);
        }
    }
    private void performTransformation(Level level, BlockPos pos, ItemEntity entity, Block targetBlock) {
        level.setBlock(pos, targetBlock.defaultBlockState(), 3);
        level.playSound(null, pos, SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 0.5F, 1.0F);
        ItemStack stack = entity.getItem();
        if (stack.getCount() > 1) {
            stack.shrink(1);
            entity.setPos(entity.getX(), pos.getY() + 1.2, entity.getZ());
            entity.setDeltaMovement(0, 0.25, 0);
            entity.setPickUpDelay(20);
        } else {
            entity.discard();
        }
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> component, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, level, component, tooltipFlag);
        component.add(Component.translatable("tooltip.yourmod.cherry_gem.day_transform").withStyle(ChatFormatting.GOLD));
        component.add(Component.translatable("tooltip.yourmod.cherry_gem.night_transform") .withStyle(ChatFormatting.BLUE));
    }
}