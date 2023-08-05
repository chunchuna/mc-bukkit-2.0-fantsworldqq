package com.chun._;
import com.chun._.Fakebb.FakeChatSimulator;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

import org.bukkit.event.Listener;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        String message = ChatColor.WHITE + "玩家 " + ChatColor.YELLOW +playerName + ChatColor.WHITE +" 加入了当前对局！";
        Bukkit.getServer().broadcastMessage(message);
        FakeChatSimulator simulator = new FakeChatSimulator();
        simulator.simulateChat();
    }




}
