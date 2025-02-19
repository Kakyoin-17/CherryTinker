package com.Kakyoin17.tools.item;

import com.Kakyoin17.cherrytinker.enity.CherrytinkerFishingBobberEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class Rod extends ModifiableItem {
    public Rod(Properties properties, ToolDefinition toolDefinition) {
        super(properties, toolDefinition);
    }
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        int i;
        ToolStack tool = ToolStack.from(itemstack);
        if (tool.isBroken()){
            return InteractionResultHolder.fail(itemstack);
        }
        else {if (player.fishing != null) {
            if (!level.isClientSide) {
                i = player.fishing.retrieve(itemstack);
                itemstack.hurtAndBreak(i, player, (player1) -> {
                    player1.broadcastBreakEvent(hand);
                });
            }

            level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.FISHING_BOBBER_RETRIEVE, SoundSource.NEUTRAL, 1.0F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
            player.gameEvent(GameEvent.ITEM_INTERACT_FINISH);
        } else {
            level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.FISHING_BOBBER_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
            if (!level.isClientSide) {

                int j = (int) Math.min(50,tool.getStats().get(ToolStats.DURABILITY)/200+1);
                i = (int) Math.min(5,3*(tool.getStats().get(ToolStats.DRAW_SPEED)-1));

                CherrytinkerFishingBobberEntity bobberEntity = new CherrytinkerFishingBobberEntity(player, level, j, i);

                level.addFreshEntity(bobberEntity);

            }

            player.awardStat(Stats.ITEM_USED.get(this));
            player.gameEvent(GameEvent.ITEM_INTERACT_START);
        }

        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());}
    }
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        return ToolActions.DEFAULT_FISHING_ROD_ACTIONS.contains(toolAction);
    }

}
