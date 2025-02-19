package com.Kakyoin17.cherrytinker.enity;



import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.TinkerModifiers;

import javax.annotation.Nullable;

public class CherrytinkerFishingBobberEntity extends FishingHook  {
    @Nullable
    private final int luck;
    private final int lureSpeed;

    public CherrytinkerFishingBobberEntity(Player player, Level world, int luck, int lureSpeed){
    super(player,world,luck,lureSpeed);
        this.luck = Math.max(0, luck);
        this.lureSpeed = Math.max(0, lureSpeed);
    }


    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        if (entityHitResult.getEntity() instanceof LivingEntity){

            LivingEntity entity = (LivingEntity) entityHitResult.getEntity();
            Player player = this.getPlayerOwner();
            ItemStack itemstack = player.getMainHandItem();
            ToolStack tool = ToolStack.from(itemstack);
            Float i = tool.getStats().get(ToolStats.PROJECTILE_DAMAGE)*(1+16*(tool.getStats().get(ToolStats.VELOCITY)-1));

            entity.hurt(DamageSource.playerAttack(player),i);
            entity.addEffect(new MobEffectInstance(TinkerModifiers.bleeding.get(),100,0));

        }

    }




}
