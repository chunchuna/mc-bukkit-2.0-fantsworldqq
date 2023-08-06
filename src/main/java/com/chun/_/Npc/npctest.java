package com.chun._.Npc;

import com.chun._._;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.Trait;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.scheduler.BukkitRunnable;

public class npctest extends Trait {
    private String action = "run";

    public npctest() {

        super("npctest");
        try {
            // 你的代码
        } catch (Exception e) {
            Bukkit.getLogger().severe("Error in npctest constructor: " + e.getMessage());
            e.printStackTrace();
        }

    }


    private NPC npc;

    public npctest(NPC npc) {
        super("npctest");
        this.npc = npc;
    }

    @Override
    public void onAttach() {
        npc = this.getNPC();
       // _.instance.getServer().getLogger().info(npc.getName() + " npc run");
    }

    public void followPlayer(Player player) {
        npc.getNavigator().setTarget(player, true);
    }

    public void goToLocation(Location location) {
        npc.getNavigator().setTarget(location);
    }

    // 添加其他方法...
    public void setTargetPlayer(Player player) {
        this.targetPlayer = player;
    }

    private Player targetPlayer;
    private String na_mode;


    @Override
    public void run() {

        //behavior_run_custom_movement();
        behavior_run_custom_movement();
        if (targetPlayer == null || !targetPlayer.isOnline()) {
            // 如果目标玩家不存在或已离线，那么不做任何事情
            //_.instance.getServer().getLogger().info(npc.getName() + " player获取失败");
            //_.instance.getServer().getLogger().info(npc.getName() + " null");
            return;
        }

//        //_.instance.getServer().getLogger().info(npc.getName() + " no null");
//        if(npc==null)return;
//        Location npcLocation = npc.getEntity().getLocation();
//        Location playerLocation = targetPlayer.getLocation();
//        npc.getNavigator().getDefaultParameters().baseSpeed(1.0f);
//
//        double distance = npcLocation.distance(playerLocation);
//
//
//        if (distance < 10) {
//            //_.instance.getServer().getLogger().info(npc.getName() + " fo");
//            behavior_flo_player();
//
//        } else {
//           // _.instance.getServer().getLogger().info(npc.getName() + " 应该走随机");
//            behavior_run_custom_movement();
//        }
    }


    public void behavior_flo_player() {
        if(targetPlayer==null)
        {
            behavior_run_custom_movement();
        }

        Location npcLocation = npc.getEntity().getLocation();
        Location playerLocation = targetPlayer.getLocation();
        npc.getNavigator().getDefaultParameters().baseSpeed(1.0f);
        double distance = npcLocation.distance(playerLocation);

        // _.instance.getServer().getLogger().info(npc.getName() + " 跟随玩家" + distance);
        if (!npc.getNavigator().isNavigating()) {
            followPlayer(targetPlayer);
            na_mode = "flow";
        } else {
            if (na_mode != "flow") {
                followPlayer(targetPlayer);
                na_mode = "flow";
            } else {

            }

        }

        //_.instance.getServer().getLogger().info(npc.getName() + " 跟随玩家");
    }

    public void behavior_run_custom_movement() {
        Entity entity = npc.getEntity();
        if (entity == null) {
            // NPC没有被加载到世界中，处理这种情况的代码
            return;
        }
       // _.instance.getServer().getLogger().info(npc.getName().info(npc.getName() + " 到了随机函数了");
        Location npcLocation = npc.getEntity().getLocation();

        if (targetPlayer == null) {
            // targetPlayer是null，处理这种情况的代码
            return;
        }
        Location playerLocation = targetPlayer.getLocation();
        npc.getNavigator().getDefaultParameters().baseSpeed(1.0f);
        double distance = npcLocation.distance(playerLocation);

        if (!npc.getNavigator().isNavigating()) {
            Location randomLocation = npcLocation.clone().add((Math.random() - 0.5) * 300, 0, (Math.random() - 0.5) * 300);
            //Location randomLocation = npcLocation.clone().add((Math.random() - 0.5) * 60, 0, (Math.random() - 0.5) * 60);
            goToLocation(randomLocation);
            //_.instance.getServer().getLogger().info(npc.getName() + " 随机移动" + distance);
            na_mode = "ramrun";
        } else {
            if (na_mode != "ramrun") {
                Location randomLocation = npcLocation.clone().add((Math.random() - 0.5) * 60, 0, (Math.random() - 0.5) * 60);
                goToLocation(randomLocation);
                //_.instance.getServer().getLogger().info(npc.getName() + " 随机移动" + distance);
                na_mode = "ramrun";
            } else {

            }
        }


    }

    private boolean cuttree_isfindtree;//是否找到了树
    private boolean cuttree_iscuttree;//是否正在砍树
    private int swingCount = 0; //挥手次数

    public void behavior_cuttree() {
        if (!cuttree_isfindtree) //如果还没开始找到树
        {
            //执行找附近10格的树
            _.instance.getServer().getLogger().info("开始找树");
            Location treeLocation = findTree(npc);
            if(treeLocation==null) return;

            Location teleportLocation = treeLocation.clone().add(1, 0, 0);
            teleportLocation.getBlock().setType(Material.AIR);
            teleportLocation.clone().add(0, 1, 0).getBlock().setType(Material.AIR);

            npc.getEntity().teleport(teleportLocation);
            if (!cuttree_iscuttree) { //判断是否正在砍树
                cuttree_iscuttree=true;

                // 然后执行挥手的动作10次
                new BukkitRunnable() {
                    int count = 0;

                    @Override
                    public void run() {
                        if (count >= 10) {
                            this.cancel();

                            // 10次后,NPC附近的树自然break掉,并掉落物品
                            for (int x = -2; x <= 2; x++) {
                                for (int y = -2; y <= 2; y++) {
                                    for (int z = -2; z <= 2; z++) {
                                        Block block = treeLocation.clone().add(x, y, z).getBlock();
                                        Material material = block.getType();
                                        if (material == Material.OAK_LOG || material == Material.SPRUCE_LOG || material == Material.BIRCH_LOG
                                                || material == Material.JUNGLE_LOG || material == Material.ACACIA_LOG || material == Material.DARK_OAK_LOG) {
                                            block.breakNaturally();
                                        }
                                    }
                                }
                            }

                            cuttree_isfindtree = false;
                            cuttree_iscuttree=false;
                        } else {
                            ((Player) npc.getEntity()).swingMainHand();
                            count++;
                        }
                    }
                }.runTaskTimer(_.instance, 0, 20);  // 20 ticks = 1 second
            }


        }
    }


    public Location findTree(NPC npc) {
        Location npcLocation = npc.getEntity().getLocation();
        for (int x = -10; x <= 10; x++) {
            for (int y = -10; y <= 10; y++) {
                for (int z = -10; z <= 10; z++) {
                    Block block = npcLocation.clone().add(x, y, z).getBlock();
                    Material material = block.getType();
                    if ((material == Material.OAK_LOG || material == Material.SPRUCE_LOG || material == Material.BIRCH_LOG
                            || material == Material.JUNGLE_LOG || material == Material.ACACIA_LOG || material == Material.DARK_OAK_LOG)
                            && block.getRelative(BlockFace.DOWN).getType().isSolid()) {
                        return block.getLocation();
                    }
                }
            }
        }
        return null;
    }
}
