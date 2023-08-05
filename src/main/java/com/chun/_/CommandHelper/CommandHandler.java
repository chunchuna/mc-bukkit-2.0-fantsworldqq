package com.chun._.CommandHelper;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.world.block.BlockTypes;
import com.sk89q.worldedit.WorldEditException;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.Vector;

public class CommandHandler {
    private final WorldEditPlugin worldEdit;

    public CommandHandler() {
        this.worldEdit = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
    }

    public boolean handleCommand(CommandSender sender, Command command, String label, String[] args) {
//        if (command.getName().equalsIgnoreCase("p-box")) {
//            return handlePBoxCommand(sender, args);
//        }
        // 在这里添加其他的指令处理
        return false;
    }

    private boolean handlePBoxCommand(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            LocalSession session = worldEdit.getSession(player);
            try (EditSession editSession = session.createEditSession(BukkitAdapter.adapt(player))) {
                for (Entity entity : player.getNearbyEntities(10, 10, 10)) {
                    if (entity instanceof Player) {
                        BlockVector3 center = BukkitAdapter.asBlockVector(entity.getLocation());
                        CuboidRegion region = new CuboidRegion(center.subtract(1, 1, 1), center.add(1, 1, 1));
                        try {
                            editSession.setBlocks(region, BlockTypes.GLASS.getDefaultState());
                        } catch (WorldEditException e) {
                            e.printStackTrace();
                        }
                    }
                }
                player.sendMessage("已为周围的玩家绘制方框！");
                return true;
            }
        }
        return false;
    }

    // 在这里添加其他的指令处理方法
}