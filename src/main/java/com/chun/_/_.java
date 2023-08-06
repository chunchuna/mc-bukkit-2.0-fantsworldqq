package com.chun._;

import CustItem.CustomItemListener;
import com.chun._.CommandHelper.CommandHandler;
import com.chun._.Fakebb.Fakebb;
import com.chun._.Npc.*;
import com.sun.tools.javac.util.List;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.NPCDeathEvent;
import net.citizensnpcs.api.event.NPCSpawnEvent;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.TraitInfo;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.Random;

public final class _ extends JavaPlugin implements Listener {
    public static _ instance;
    private CommandHandler commandHandler;
    //public static SkinsRestorerAPI skinsRestorerAPI;
    @Override
    public void onEnable() {

        instance = this;
        commandHandler = new CommandHandler();
        getServer().getPluginManager().registerEvents(this, this);

       Fakebb.generateFakePlayerNames();

        //net.citizensnpcs.api.CitizensAPI.getTraitFactory().registerTrait(net.citizensnpcs.api.trait.TraitInfo.create(EmptyTrait.class).withName("emptytrait"));
        //CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(NPCManagerbase.NPCBehavior.class).withName("NPCBehavior"));
        this.getCommand("take").setExecutor(new ManCommandNEW());
        SmartVillager smartVillager = new SmartVillager();
        getServer().getPluginManager().registerEvents(smartVillager, this);
        getCommand("vel").setExecutor(smartVillager);
        getCommand("helpcuttree").setExecutor(new SmartVillager.CutTreeCommand());
        getCommand("helpbuildhouse").setExecutor(new SmartVillager.BuildHouseCommand());
        getCommand("defendmode").setExecutor(new SmartVillager.DefendModeCommand());

        getCommand("man").setExecutor(new ManCommand(this));
        Bukkit.getServer().getPluginManager().registerEvents(new CustomItemListener(), this);
        getLogger().info("插件加载成功");
        getServer().getPluginManager().registerEvents(new RoomEnterListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);

        net.citizensnpcs.api.CitizensAPI.getTraitFactory().registerTrait(net.citizensnpcs.api.trait.TraitInfo.create(EmptyTrait.class).withName("emptytrait"));
        CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(npctest.class).withName("npctest"));






    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return commandHandler.handleCommand(sender, command, label, args);
    }
    private Random random = new Random();

//    public void onNPCDeath(NPCDeathEvent event) {
//        getLogger().info("NPC death");
//        NPC npc = event.getNPC();
//
//        // 获取NPC死亡的位置
//        Location deathLocation = npc.getEntity().getLocation();
//
//        // 在死亡位置基础上，随机生成一个在150格范围内的位置
//        double x = deathLocation.getX() + (random.nextDouble() * 800 - 500);
//        double z = deathLocation.getZ() + (random.nextDouble() * 800 - 500);
//        double y = deathLocation.getWorld().getHighestBlockYAt((int) x, (int) z);
//        Location respawnLocation = new Location(deathLocation.getWorld(), x, y, z);
//
//        // 设置NPC复活的位置
//        Bukkit.getScheduler().runTaskLater(this, () -> npc.spawn(respawnLocation), 100L);  // 延迟20刻（1秒）后复活
//    }


    @EventHandler
    public void onNPCSpawn(NPCSpawnEvent event) {
        getLogger().info("NPC spwan");
        NPC npc = event.getNPC();

        // 获取NPC的位置
        Location spawnLocation = npc.getEntity().getLocation();

        // 在复活位置基础上，随机生成一个在150格范围内的位置
        double x = spawnLocation.getX() + (random.nextDouble() * 70 - 35);
        double z = spawnLocation.getZ() + (random.nextDouble() * 70 - 35);
        double y = spawnLocation.getWorld().getHighestBlockYAt((int) x, (int) z);
        Location teleportLocation = new Location(spawnLocation.getWorld(), x, y, z);

        // 将NPC传送到新的位置
        npc.teleport(teleportLocation, PlayerTeleportEvent.TeleportCause.PLUGIN);
    }



}

