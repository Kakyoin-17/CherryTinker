package com.Kakyoin17.cherrytinker.enity;



import com.Kakyoin17.cherrytinker.registry.ModModifiers;
import com.Kakyoin17.tools.item.Rod;
import net.minecraft.world.InteractionHand;
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

public class CherrytinkerFishingBobberEntity extends FishingHook  {


    public CherrytinkerFishingBobberEntity(Player player, Level world, int luck, int lureSpeed){
    super(player,world,luck,lureSpeed);
    }
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        if (entityHitResult.getEntity() instanceof LivingEntity entity){
            Player player = this.getPlayerOwner();
            if (player != null) {
            ItemStack itemstack = player.getMainHandItem();
            ToolStack tool = ToolStack.from(itemstack);
            float i = tool.getStats().get(ToolStats.PROJECTILE_DAMAGE)*(1+16*(tool.getStats().get(ToolStats.VELOCITY)-1));
            entity.hurt(DamageSource.playerAttack(player),i);
            entity.addEffect(new MobEffectInstance(TinkerModifiers.bleeding.get(),100,0));
            }
        }
    }
    @Override
    public void tick() {
        if (this.entityData.get(DATA_BITING)) {
            var player=this.getPlayerOwner();
            if(player==null)return;
            var stack=player.getMainHandItem();
            var view=ToolStack.from(stack);
            if(view.getModifierLevel(ModModifiers.autofishing.getId())>0&&stack.getItem() instanceof Rod rod){
                rod.use(getPlayerOwner().level, getPlayerOwner(), InteractionHand.MAIN_HAND);
            }
        }
        super.tick();
    }
}
