package com.kakyoin17.cherrytinker.modifier;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.OnAttackedModifierHook; // 注意包名，IDEA会自动修正
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class RecoveryModifier extends Modifier implements OnAttackedModifierHook {

    @Override
    protected void registerHooks(ModuleHookMap.Builder builder) {
        builder.addHook(this, ModifierHooks.ON_ATTACKED);
    }

    @Override
    public void onAttacked(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, EquipmentContext context, @NotNull EquipmentSlot slotType, @NotNull DamageSource source, float amount, boolean isDirectDamage) {
        LivingEntity entity = context.getEntity();
        if (!(entity instanceof Player)) {
            return;
        }
        int totalLevel = 0;
        for (net.minecraft.world.item.ItemStack stack : entity.getArmorSlots()) {
            totalLevel += ModifierUtil.getModifierLevel(stack, this.getId());
        }
        if (totalLevel > 0) {
            int amplifier = Math.min(totalLevel - 1,3);
            entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200, amplifier, false, false));
        }
    }
}




