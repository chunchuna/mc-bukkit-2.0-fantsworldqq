package com.chun._.Npc;
import com.chun._._;
import jdk.javadoc.internal.doclets.formats.html.Navigation;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

import static org.bukkit.ChatColor.*;


public class SmartVillager implements CommandExecutor, Listener {
    private static Villager villagerHim;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("vel")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(RED + "只有玩家可以使用此命令!");
                return true;
            }

            Player player = (Player) sender;
            Location location = player.getLocation().add(2, 0, 2); // Generate near the player

            if (this.villagerHim != null && !this.villagerHim.isDead()) {
                this.villagerHim.teleport(location);
            } else {
                this.villagerHim = player.getWorld().spawn(location, Villager.class);
                this.villagerHim.setCustomName("him");
            }
        }
        return false;
    }

//    @EventHandler
//    public void onRightClickVillager(PlayerInteractEntityEvent event) {
//        if (event.getRightClicked().equals(this.villagerHim)) {
//            event.getPlayer().sendMessage(ChatColor.GREEN + "请选择:");
//            event.getPlayer().sendMessage(ChatColor.GREEN + "1. 帮我砍树");
//            event.getPlayer().sendMessage(ChatColor.GREEN + "2. 帮我修房子");
//            event.getPlayer().sendMessage(ChatColor.GREEN + "3. 防御模式");
//        }
//    }

    @EventHandler
    public void onRightClickVillager(PlayerInteractEntityEvent event) {
        if (event.getRightClicked().equals(this.villagerHim)) {
            TextComponent message1 = new TextComponent("1. 帮我砍树");

            message1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/helpcuttree"));
            event.getPlayer().spigot().sendMessage(message1);
            TextComponent message2 = new TextComponent("2. 帮我修房子");

            message2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/helpbuildhouse"));
            event.getPlayer().spigot().sendMessage(message2);

            TextComponent message3 = new TextComponent("3. 防御模式");
            message3.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/defendmode"));
            event.getPlayer().spigot().sendMessage(message3);
        }
    }


//    public static class CutTreeCommand implements CommandExecutor {
//        @Override
//        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//            if (!(sender instanceof Player)) {
//                return false;
//            }
//
//            Player player = (Player) sender;
//            player.sendMessage(ChatColor.GREEN + "开始砍树!");
//
//            // Your code to cut the tree here
//
//            return true;
//        }
//    }


    public static class CutTreeCommand implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (!(sender instanceof Player)) {
                return false;
            }

            Player player = (Player) sender;
            player.sendMessage(ChatColor.GREEN + "开始砍树!");

            // Teleport the villager to the player
            villagerHim.teleport(player.getLocation().add(2, 0, 2));

            // Check for tree within 30 blocks radius
            Block baseLog = findNearestBlock(villagerHim, 30);  // Assuming trees are made of logs

            if(baseLog != null) {
                player.sendMessage(ChatColor.GREEN + "找到了一棵树，开始砍树!");

                // Find all logs above the base log and destroy them
                List<Block> treeLogs = new ArrayList<>();
                for(int y = 0; y <= 30; y++) {  // Assuming trees are not taller than 30 blocks
                    Block currentBlock = baseLog.getRelative(BlockFace.UP, y);
                    if(isLog(currentBlock.getType())) {
                        treeLogs.add(currentBlock);
                    } else {
                        break;
                    }
                }

                Bukkit.getScheduler().runTaskLater(_.instance, new Runnable() {
                    public void run() {
                        for(Block log : treeLogs) {
                            log.setType(Material.AIR);  // Remove the log
                        }
                        villagerHim.getWorld().dropItemNaturally(baseLog.getLocation(), new ItemStack(Material.OAK_LOG, treeLogs.size()));  // Drop logs
                        player.sendMessage(ChatColor.GREEN + "砍树完成! 掉落了 " + treeLogs.size() + " 个原木!");
                    }
                }, 80L); // Wait for 2 seconds (20 ticks = 1 second)

            } else {
                player.sendMessage(ChatColor.RED + "附近没有树可以砍!");
            }

            return true;
        }

        private Block findNearestBlock(LivingEntity entity, int radius) {
            Set<Material> treeMaterials = new HashSet<>(Arrays.asList(    //自动遍历所有的树木种类
                    Material.OAK_LOG,
                    Material.BIRCH_LOG,
                    Material.SPRUCE_LOG,
                    Material.JUNGLE_LOG,
                    Material.ACACIA_LOG,
                    Material.DARK_OAK_LOG
            ));

            Block block = null;

            for(int x = -radius; x <= radius; x++) {
                for(int z = -radius; z <= radius; z++) {
                    for(int y = -radius; y <= radius; y++) {  // Include blocks above and below the entity
                        Block currentBlock = entity.getWorld().getBlockAt(entity.getLocation().add(x, y, z));
                        if(treeMaterials.contains(currentBlock.getType())) {
                            if(block == null || entity.getLocation().distanceSquared(currentBlock.getLocation()) < entity.getLocation().distanceSquared(block.getLocation())) {
                                block = currentBlock;
                            }
                        }
                    }
                }
            }

            return block;
        }
        private boolean isLog(Material material) {
            return material.equals(Material.OAK_LOG) ||
                    material.equals(Material.BIRCH_LOG) ||
                    material.equals(Material.SPRUCE_LOG) ||
                    material.equals(Material.JUNGLE_LOG) ||
                    material.equals(Material.ACACIA_LOG) ||
                    material.equals(Material.DARK_OAK_LOG);
        }
    }
    public static class BuildHouseCommand implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (!(sender instanceof Player)) {
                return false;
            }

            Player player = (Player) sender;
            player.sendMessage(ChatColor.GREEN + "开始修房子!");

            // Your code to build the house here

            return true;
        }
    }

    public static class DefendModeCommand implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (!(sender instanceof Player)) {
                return false;
            }

            Player player = (Player) sender;
            player.sendMessage(ChatColor.GREEN + "进入防御模式!");

            // Your code for defense mode here

            return true;
        }
    }

}

