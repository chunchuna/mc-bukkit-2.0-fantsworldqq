package com.chun._.Npc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.NPC;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.plugin.java.JavaPlugin;

public class ManCommand implements CommandExecutor {
    private final JavaPlugin plugin;
    private final int radius = 5; // 村民围绕玩家的半径

    public ManCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("该命令只能由玩家执行！");
            return true;
        }

        Player player = (Player) sender;
        double playerX = player.getLocation().getX();
        double playerZ = player.getLocation().getZ();
        for (int i = 0; i < 10; i++) {
            double angle = i * (2 * Math.PI / 10); // 计算村民在圆周上的角度
            double offsetX = radius * Math.cos(angle); // 计算村民相对于玩家的 X 偏移量
            double offsetZ = radius * Math.sin(angle); // 计算村民相对于玩家的 Z 偏移量

            Villager villager = (Villager) player.getWorld().spawnEntity(player.getLocation().add(offsetX, 0, offsetZ), EntityType.VILLAGER);
            villager.setCustomName("man");
            villager.setCustomNameVisible(true);
            villager.setFireTicks(Integer.MAX_VALUE); // 设置村民着火，但不受伤害
            villager.setAI(false);
        }

        return true;
    }
}
