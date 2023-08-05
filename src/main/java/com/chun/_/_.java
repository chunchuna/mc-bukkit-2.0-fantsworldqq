package com.chun._;

import CustItem.CustomItemListener;
import com.chun._.CommandHelper.CommandHandler;
import com.chun._.Fakebb.Fakebb;
import com.chun._.Npc.*;
import com.sun.tools.javac.util.List;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.trait.TraitInfo;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;

public final class _ extends JavaPlugin {
    public static _ instance;
    private CommandHandler commandHandler;
    //public static SkinsRestorerAPI skinsRestorerAPI;
    @Override
    public void onEnable() {

        instance = this;
        commandHandler = new CommandHandler();


       Fakebb.generateFakePlayerNames();

        //net.citizensnpcs.api.CitizensAPI.getTraitFactory().registerTrait(net.citizensnpcs.api.trait.TraitInfo.create(EmptyTrait.class).withName("emptytrait"));
        //CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(NPCManagerbase.NPCBehavior.class).withName("NPCBehavior"));
        this.getCommand("take").setExecutor(new ManCommandNEW());
        getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                //CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(npctest.class).withName("npctest"));


            }
        }, 10L);

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



}

