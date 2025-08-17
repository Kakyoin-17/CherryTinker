package com.Kakyoin17.cherrytinker.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;


public class EGoldBlock extends Block {
    public EGoldBlock(Properties pProperties) {
        super(pProperties);
    }
    //玩家站在上面消耗饥饿值(通过增加消耗值实现)，增加经验值
    @Override
    public void stepOn(Level Level, BlockPos Pos, BlockState State, Entity Entity) {
        if(Entity instanceof Player) {
            if (((Player) Entity).getFoodData().getFoodLevel()>0||!Level.isClientSide) {
                ((Player) Entity).causeFoodExhaustion(4);
                ((Player) Entity).giveExperiencePoints(1);
                ((Player)Entity).addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE,1,1));
            }
        }
    }
}
