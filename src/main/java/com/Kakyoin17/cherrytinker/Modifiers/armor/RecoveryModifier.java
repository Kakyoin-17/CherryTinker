package com.Kakyoin17.cherrytinker.Modifiers.armor;

import com.Kakyoin17.cherrytinker.registry.ModModifiers;
import com.Kakyoin17.cherrytinker.superclass.ArmorModifier;
import com.Kakyoin17.cherrytinker.until.method.ModifierLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;


public class RecoveryModifier extends ArmorModifier {
    @Override
    public void LivingHurtEvent(LivingHurtEvent event) {
        if (event.getEntity() != null) {
            LivingEntity entity = event.getEntity();
            if (ModifierLevel.getTotalArmorModifierlevel(entity, ModModifiers.recovery.getId()) > 0) {
                int level =ModifierLevel.getTotalArmorModifierlevel(event.getEntity(),this.getId());
                if (entity instanceof Player player) {
                    entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION,200,level));
                    event.setAmount(event.getAmount());
                }
            }
        }
    }
}




