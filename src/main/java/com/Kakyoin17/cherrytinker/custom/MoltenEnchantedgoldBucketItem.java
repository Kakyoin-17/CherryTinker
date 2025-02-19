package com.Kakyoin17.cherrytinker.custom;

import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public class MoltenEnchantedgoldBucketItem extends BucketItem {


    public MoltenEnchantedgoldBucketItem(Fluid p_40689_, Properties p_40690_) {
        super(p_40689_, p_40690_);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean isFoil(@NotNull ItemStack itemStack) {
        return true;
    }}

