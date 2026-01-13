package com.kakyoin17.cherrytinker.modifier;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;

public class CherrygemAttack extends Modifier implements MeleeDamageModifierHook,ProjectileHitModifierHook {

    @Override
    protected void registerHooks(ModuleHookMap.Builder builder) {

        builder.addHook(this, ModifierHooks.MELEE_DAMAGE);
        builder.addHook(this, ModifierHooks.PROJECTILE_HIT);
    }

    @Override
    public float getMeleeDamage(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        LivingEntity attacker = context.getAttacker();
        if (attacker instanceof Player player) {
            float a = (player.getFoodData().getFoodLevel());
            if (player.getFoodData().getFoodLevel() > 10){
                return damage + (a*0.5f*modifier.getLevel());
            }
        }
        return damage;
    }

    @Override
    public boolean onProjectileHitEntity(ModifierNBT modifiers, ModDataNBT persistentData, ModifierEntry modifier, Projectile projectile, EntityHitResult hit, @Nullable LivingEntity attacker, @Nullable LivingEntity target, boolean notBlocked) {
        if (attacker instanceof Player player) {
            float b = (player.getFoodData().getFoodLevel());
            if (player.getFoodData().getFoodLevel() > 10){
                if (target != null) {
                    target.hurt(attacker.damageSources().thrown(projectile, attacker), b*0.5f*modifier.getLevel());
                }
            }
        }
        return false;
    }
}