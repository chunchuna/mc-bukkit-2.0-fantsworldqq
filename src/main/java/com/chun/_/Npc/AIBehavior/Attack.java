package com.chun._.Npc.AIBehavior;
import net.citizensnpcs.api.ai.Goal;
import net.citizensnpcs.api.ai.GoalSelector;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Attack implements Goal {
    private final NPC npc;
    private LivingEntity target;

    public Attack(NPC npc) {
        this.npc = npc;
    }

    @Override
    public void reset() {
        target = null;
    }

    @Override
    public void run(GoalSelector selector) {
        if (target == null || !target.isValid()) {
            return;
        }

        // 让NPC向目标移动
        npc.getNavigator().setTarget(target,true);

        // 如果NPC在攻击范围内，让NPC攻击目标
        if (npc.getEntity().getLocation().distance(target.getLocation()) < 5) {
            ((Player) npc.getEntity()).attack(target);
        }
    }

    @Override
    public boolean shouldExecute(GoalSelector selector) {
        // 寻找附近的僵尸
        for (Entity entity : npc.getEntity().getNearbyEntities(50, 50, 50)) {
            if (entity.getType() == EntityType.ZOMBIE) {
                target = (LivingEntity) entity;
                return true;
            }
        }

        return false;
    }
}
