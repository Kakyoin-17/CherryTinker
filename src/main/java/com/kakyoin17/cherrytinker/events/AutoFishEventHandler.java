package com.kakyoin17.cherrytinker.events;

import com.kakyoin17.cherrytinker.modifier.AutoFishModifier;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.common.TinkerTags;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = "cherrytinker")

public class AutoFishEventHandler {

    public static Field NIBBLE_FIELD;

    public static final Map<UUID, Integer> RECAST_QUEUE = new HashMap<>();

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END || event.side.isClient()) {
            return;
        }

        Player player = event.player;
        handleRecastQueue(player);
        handleAutoRetrieve(player);
    }

    public static void handleRecastQueue(Player player) {
        UUID uuid = player.getUUID();
        if (RECAST_QUEUE.containsKey(uuid)) {
            int ticksLeft = RECAST_QUEUE.get(uuid);
            if (ticksLeft > 0) {
                RECAST_QUEUE.put(uuid, ticksLeft - 1);
            } else {
                RECAST_QUEUE.remove(uuid);
                performRecast(player);
            }
        }
    }
    public static void performRecast(Player player) {
        ItemStack stack = player.getMainHandItem();
        InteractionHand hand = InteractionHand.MAIN_HAND;
        if (!stack.canPerformAction(ToolActions.FISHING_ROD_CAST)) {
            stack = player.getOffhandItem();
            hand = InteractionHand.OFF_HAND;
        }
        if (stack.canPerformAction(ToolActions.FISHING_ROD_CAST) && player.fishing == null) {
            if (stack.is(TinkerTags.Items.MODIFIABLE)) {
                stack.use(player.level(), player, hand);
            }
        }
    }

    public static void handleAutoRetrieve(Player player) {
        FishingHook hook = player.fishing;
        if (hook != null && hook.getTags().contains(AutoFishModifier.AUTO_FISH_TAG)) {
            try {
                if (NIBBLE_FIELD == null) {
                    try {
                        NIBBLE_FIELD = ObfuscationReflectionHelper.findField(FishingHook.class, "f_37089_");
                    } catch (Exception e) {
                        NIBBLE_FIELD = FishingHook.class.getDeclaredField("nibble");
                    }
                    NIBBLE_FIELD.setAccessible(true);
                }
                int nibble = NIBBLE_FIELD.getInt(hook);
                if (nibble > 0) {
                    ItemStack stack = player.getMainHandItem();
                    if (!stack.canPerformAction(ToolActions.FISHING_ROD_CAST)) {
                        stack = player.getOffhandItem();
                    }
                    if (stack.canPerformAction(ToolActions.FISHING_ROD_CAST)) {
                        int damage = hook.retrieve(stack);
                        if (stack.is(TinkerTags.Items.MODIFIABLE)) {
                            ToolStack tool = ToolStack.from(stack);
                            InteractionHand hand = (player.getMainHandItem() == stack) ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
                            ToolDamageUtil.damageAnimated(tool, damage, player, hand);
                        } else {
                            stack.hurtAndBreak(damage, player, (p) -> p.broadcastBreakEvent(InteractionHand.MAIN_HAND));
                        }
                        RECAST_QUEUE.put(player.getUUID(), 15);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}