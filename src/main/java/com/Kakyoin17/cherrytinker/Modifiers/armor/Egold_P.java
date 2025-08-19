package com.Kakyoin17.cherrytinker.Modifiers.armor;

import com.Kakyoin17.cherrytinker.registry.ModModifiers;
import com.Kakyoin17.cherrytinker.superclass.ArmorModifier;
import com.Kakyoin17.cherrytinker.until.method.ModifierLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;

public class Egold_P extends ArmorModifier {
    @Override
    public void LivingHurtEvent(LivingHurtEvent event) {
            if (event.getEntity() != null) {
                LivingEntity entity = event.getEntity();
                if (ModifierLevel.getTotalArmorModifierlevel(entity, ModModifiers.egold_p.getId()) > 0) {
                    int level = ModifierLevel.getTotalArmorModifierlevel(event.getEntity(), this.getId());
                    if (event.getSource().getEntity() instanceof LivingEntity enemy) {
                        if (enemy.getMaxHealth() < entity.getMaxHealth()) {
                            event.setCanceled(true);
                        } else {
                            if (entity instanceof Player player ) {
                                if (event.getSource() instanceof EntityDamageSource source && !source.isThorns()) {
                                enemy.hurt(new EntityDamageSource("egold", entity).setThorns().bypassArmor(), 10);
                                entity.addEffect(new MobEffectInstance(MobEffects.HEAL, 1, 1));
                                entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 200, level - 1));
                                event.setAmount(event.getAmount());
                            }
                            }
                        }
                    }
                }
            }

    }
}
