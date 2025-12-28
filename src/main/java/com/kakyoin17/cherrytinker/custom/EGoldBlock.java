package com.kakyoin17.cherrytinker.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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
    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (!level.isClientSide && entity instanceof Player player) {
            if (level.getGameTime() % 20 == 0) {
                if (player.getFoodData().getFoodLevel() > 0) {
                    player.causeFoodExhaustion(4.0F);
                    player.giveExperiencePoints(20);
                    player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200, 0, false, false, true));
                    level.playSound(null, pos, SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.BLOCKS, 0.5F, 1.0F);
                }
            }
        }
        super.stepOn(level, pos, state, entity);
    }
}