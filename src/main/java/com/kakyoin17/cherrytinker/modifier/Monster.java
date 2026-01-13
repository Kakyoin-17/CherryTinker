package com.kakyoin17.cherrytinker.modifier;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

public class Monster extends Modifier implements MeleeHitModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.@NotNull Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.MELEE_HIT);
    }

    private static final List<EntityType<? extends Mob>> SUMMON_POOL = List.of(
            EntityType.ZOMBIE,          // Lv 1
            EntityType.SKELETON,        // Lv 2
            EntityType.SPIDER,          // Lv 3
            EntityType.CREEPER,         // Lv 4
            EntityType.ENDERMAN         // Lv 5
    );

    @Override
    public void afterMeleeHit(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, ToolAttackContext context, float damageDealt) {

        LivingEntity attacker = context.getAttacker();
        LivingEntity target = context.getLivingTarget();

        if (attacker.level().isClientSide || target == null || !(attacker instanceof Player)) {
            return;
        }

        ServerLevel level = (ServerLevel) attacker.level();
        BlockPos targetPos = target.blockPosition();

        List<Mob> nearbyMobs = level.getEntitiesOfClass(Mob.class, new AABB(targetPos).inflate(10));
        if (nearbyMobs.size() >= 20) {
            return;
        }

        int currentLevel = modifier.getLevel();

        if (currentLevel <= 0) {
            return;
        }

        int unlockCount = Math.min(currentLevel, SUMMON_POOL.size());

        int selectedIndex = attacker.getRandom().nextInt(unlockCount);

        EntityType<? extends Mob> typeToSummon = SUMMON_POOL.get(selectedIndex);

        Mob minion = typeToSummon.create(level);
        if (minion != null) {
            Vec3 pos = target.position();
            minion.moveTo(pos.x, pos.y, pos.z, 0, 0);
            minion.setTarget(target);

            if (minion instanceof net.minecraft.world.entity.monster.Creeper creeper) {
                net.minecraft.nbt.CompoundTag nbt = new net.minecraft.nbt.CompoundTag();
                creeper.addAdditionalSaveData(nbt);
                nbt.putShort("Fuse", Short.MAX_VALUE);
                nbt.putByte("ExplosionRadius", (byte) 0);
                creeper.readAdditionalSaveData(nbt);
                creeper.setCustomName(net.minecraft.network.chat.Component.translatable("cherry_monster_creeper"));
                creeper.setTarget(attacker);
            } else {
                minion.setTarget(target);
            }

            level.addFreshEntity(minion);
        }
    }

}
