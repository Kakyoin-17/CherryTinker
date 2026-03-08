package com.kakyoin17.cherrytinker.modifier;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.DamageBlockModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.armor.OnAttackedModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class Egold_P extends Modifier implements OnAttackedModifierHook, DamageBlockModifierHook {

    @Override
    protected void registerHooks(ModuleHookMap.Builder builder) {
        builder.addHook(this, ModifierHooks.ON_ATTACKED);
        builder.addHook(this, ModifierHooks.DAMAGE_BLOCK);
    }

    @Override
    public void onAttacked(@NotNull IToolStackView iToolStackView, @NotNull ModifierEntry modifierEntry, EquipmentContext context, @NotNull EquipmentSlot equipmentSlot, @NotNull DamageSource damageSource, float v, boolean b) {
        LivingEntity entity = context.getEntity();
        if (!(entity instanceof Player)) {
            return;
        }
        int totalLevel = 0;
        for (net.minecraft.world.item.ItemStack stack : entity.getArmorSlots()) {
            totalLevel += ModifierUtil.getModifierLevel(stack, this.getId());
        }
        if (totalLevel > 0) {
            int amplifier = totalLevel - 1;
            entity.addEffect(new MobEffectInstance(MobEffects.HEAL, 1, 1));
            entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 200, amplifier, false, false));
            if (damageSource.getEntity() != null||damageSource.getEntity() != entity) {
                damageSource.getEntity().hurt(entity.damageSources().dragonBreath(),  10);
            }
        }
    }

    @Override
    public boolean isDamageBlocked(@NotNull IToolStackView iToolStackView, @NotNull ModifierEntry modifierEntry, EquipmentContext context, @NotNull EquipmentSlot equipmentSlot, DamageSource source, float v) {
        LivingEntity player = context.getEntity();
        Entity attackerEntity = source.getEntity();
        if (attackerEntity instanceof LivingEntity attacker) {
            if (player.getHealth() >= attacker.getHealth()) {
                player.level().playSound(null, player.getX(), player.getY(), player.getZ(), net.minecraft.sounds.SoundEvents.SHIELD_BLOCK, net.minecraft.sounds.SoundSource.PLAYERS, 1.0f, 1.0f);
                return true;
            }
        }
        return false;
    }
}
