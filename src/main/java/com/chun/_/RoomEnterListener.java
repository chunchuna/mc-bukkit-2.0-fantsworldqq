package com.chun._;
import CustItem.CustomItem;
import CustItem.ItemGiver;
import com.chun._.BuildingSystem.BuidlingBoy;
import com.chun._.Npc.NPCManagerbase;
import com.chun._.Npc.npctest;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class RoomEnterListener implements Listener {

    private boolean hasSentWelcomeMessage = false;
    private Set<UUID> playersWithWelcomeMessage = new HashSet<>();
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID playerId = player.getUniqueId();

        if (!playersWithWelcomeMessage.contains(playerId)) {
            //player.sendMessage("欢迎进入房间！");
            player.sendMessage("插件加载成功");

            // 生成建筑
            playersWithWelcomeMessage.add(playerId);
            for (int i = 0; i < 20; i++) {
                BuidlingBoy.generateBuilding_3(player);
            }

            BuidlingBoy.generateBuilding_4(player,50);
            BuidlingBoy.generateBuilding_5(player,30);
            BuidlingBoy.generateBuilding_6(player,90);

            //获得特殊道具
            ItemGiver.GetApple(event);

            //Npc相关
            player.performCommand("npc remove all");
            NPCManagerbase npcManager = new NPCManagerbase();

            // 获取玩家所在的世界
            World world = player.getWorld();
            // 设置天气为下雨（在雪地生物群系中会变为下雪）
            world.setStorm(true);

            // 生成NPC
            Bukkit.getScheduler().scheduleSyncDelayedTask(_.instance, new Runnable() {
                @Override
                public void run() {
                    NPCManagerbase npcManager = new NPCManagerbase();

                    // 生成30个NPC
                    for (int i = 0; i < 110; i++) {
                        player.performCommand("take");

                    }
                }
            }, 60L); // 延迟60个tick，约等于3秒


        }
    }
}
