package com.chun._.Npc.AIBehavior;
import net.citizensnpcs.api.ai.Goal;
import net.citizensnpcs.api.ai.GoalSelector;
import net.citizensnpcs.api.ai.NavigatorParameters;
import net.citizensnpcs.api.ai.speech.SpeechContext;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Random;

public class FollowPlayerBehavior implements Goal {
    private final NPC npc;
    private final Player player;

    public FollowPlayerBehavior(NPC npc, Player player) {
        this.npc = npc;
        this.player = player;
    }

    @Override
    public void reset() {
        // 当行为被重置时调用
    }

    @Override
    public void run(GoalSelector selector) {
        if(npc.getEntity().getLocation().distance(player.getLocation()) >= 1)
        {return;}

        if(!npc.getNavigator().isNavigating()){
            //player.sendMessage("寻路玩家导航器被禁用了");
        }else
        {
            //player.sendMessage("寻路玩家导航器正在工作");
        }
        // 每个游戏刻调用一次，用于更新行为
        NavigatorParameters params = npc.getNavigator().getDefaultParameters();
        params.distanceMargin(6); // NPC离玩家的距离
        npc.getNavigator().setTarget(player, false);

    }

    @Override
    public boolean shouldExecute(GoalSelector selector) {
        // 每个游戏刻调用一次，如果返回 true，run 方法将被调用
        return npc.isSpawned() && player.isOnline() && npc.getEntity().getWorld().equals(player.getWorld()) && npc.getEntity().getLocation().distance(player.getLocation()) < 1;
    }
}
