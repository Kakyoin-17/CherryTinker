package com.kakyoin17.cherrytinker.modifier;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;

public class Egold_A extends Modifier implements MeleeDamageModifierHook, MeleeHitModifierHook, ProjectileHitModifierHook {

    @Override
    protected void registerHooks(ModuleHookMap.Builder builder) {

        builder.addHook(this, ModifierHooks.MELEE_DAMAGE);
        builder.addHook(this, ModifierHooks.MELEE_HIT);
        builder.addHook(this, ModifierHooks.PROJECTILE_HIT);
    }

    @Override
    public float getMeleeDamage(@NotNull IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        LivingEntity attacker = context.getAttacker();
        float maxHealth = attacker.getMaxHealth();
        float bonus = (maxHealth / 2.0f) * modifier.getLevel();
        return damage + bonus;
    }

    @Override
    public void afterMeleeHit(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        LivingEntity target = context.getLivingTarget();
        LivingEntity attacker = context.getAttacker();
        if (target != null  && !context.isExtraAttack()) {
            target.invulnerableTime = 0;
            target.hurt(attacker.damageSources().magic(), 10.0f);
        }
    }

    @Override
    public boolean onProjectileHitEntity(@NotNull ModifierNBT modifiers, @NotNull ModDataNBT persistentData, @NotNull ModifierEntry modifier, @NotNull Projectile projectile, @NotNull EntityHitResult hit, @Nullable LivingEntity attacker, @Nullable LivingEntity target, boolean notBlocked) {
        if (attacker != null && target != null) {
            float maxHealth = attacker.getMaxHealth();
            float healthBonus = (maxHealth / 2.0f) * modifier.getLevel();
            float magicBonus = 10.0f;
            target.invulnerableTime = 0;
            target.hurt(attacker.damageSources().thrown(projectile, attacker), healthBonus);
            target.hurt(attacker.damageSources().magic(),  magicBonus);
        }
        return false;
    }
}
