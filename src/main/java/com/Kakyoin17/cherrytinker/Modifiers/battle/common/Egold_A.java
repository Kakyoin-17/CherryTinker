package com.Kakyoin17.cherrytinker.Modifiers.battle.common;

import com.Kakyoin17.cherrytinker.superclass.BattleModifier;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;

public class Egold_A extends BattleModifier {

    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt){
        if (context.getAttacker() instanceof Player player){
            context.getTarget().hurt(DamageSource.MAGIC,10*modifier.getLevel());
        }
    }

    @Override
    public float staticdamage(IToolStackView tool, int level, ToolAttackContext context, LivingEntity attacker, LivingEntity livingTarget, float baseDamage, float damage){
        if (attacker instanceof Player player){
            float a = (player.getMaxHealth());
            if (player.getHealth() > 17){
                return damage + (a*0.5f*level);
            }
        }
        return damage;
    }
    @Override
    public void arrowhurt(ModifierNBT modifiers, NamespacedNBT persistentData, int level, Projectile projectile, EntityHitResult hit, AbstractArrow arrow, LivingEntity attacker, LivingEntity target) {
        if (attacker instanceof Player player) {
            float a = (player.getMaxHealth());
            if (player.getHealth() > 17){
                arrow.setBaseDamage(arrow.getBaseDamage() + (a * 0.5 * level));
            }
        }
    }


}
