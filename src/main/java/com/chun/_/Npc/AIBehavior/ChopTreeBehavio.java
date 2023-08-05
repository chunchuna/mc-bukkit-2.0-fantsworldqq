package com.chun._.Npc.AIBehavior;

import com.chun._._;
import net.citizensnpcs.api.ai.Goal;
import net.citizensnpcs.api.ai.GoalSelector;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.*;


public class ChopTreeBehavio implements Goal {
    private final NPC npc;
    private final Player player;
    private Block targetTree = null;
    private int swingCount = 0;

    public ChopTreeBehavio(NPC npc, Player player) {
        this.npc = npc;
        this.player = player;
    }

    @Override
    public void reset() {
        targetTree = null;
        swingCount = 0;
    }
    private static final Random random = new Random();
    @Override
    public void run(GoalSelector selector) {
        if (targetTree == null) {
            targetTree = findNearestTree();
            if (targetTree != null) {
                Location targetLocation = targetTree.getLocation().add(-1, 0, 0);
                boolean isBlocked = false;

                // 检查目标位置周围上下5格方块
                for (int y = -1; y <= 1; y++) {
                    for (int x = -1; x <= 1; x++) {
                        for (int z = -1; z <= 1; z++) {
                            Block block = targetLocation.clone().add(x, y, z).getBlock();
                            if (block.getType() != Material.AIR) {
                                isBlocked = true;
                                break;
                            }
                        }
                        if (isBlocked) {
                            break;
                        }
                    }
                    if (isBlocked) {
                        break;
                    }
                }

                // 如果目标位置没有被阻挡，那么设置NPC的目标位置
                if (!isBlocked) {
                    npc.getNavigator().setTarget(targetLocation);
                    player.sendMessage("开始移动到树");
                } else {
                    //player.sendMessage("目标位置被阻挡，正在寻找新的目标位置");
                    // 在这里寻找一个新的目标位置
                    if(!npc.getNavigator().isNavigating())
                    {
                        Location npcLocation = npc.getEntity().getLocation();
                        int randomDistance = random.nextInt(6) + 5; // 生成5至10之间的随机距离
                        double randomAngle = random.nextDouble() * 2 * Math.PI; // 生成一个随机角度
                        double offsetX = randomDistance * Math.cos(randomAngle);
                        double offsetZ = randomDistance * Math.sin(randomAngle);
                        Location nearbyLocation = npcLocation.clone().add(offsetX, 0, offsetZ); // 根据随机距离和角度计算新位置
                        npc.getNavigator().setTarget(nearbyLocation);
                        reset();
                    }


                }


            } else {
                //player.sendMessage("没有找到附近的树，正在重新尝试...");
                //npc移动到自己附近3格
                Location npcLocation = npc.getEntity().getLocation();
                int randomDistance = random.nextInt(6) + 5; // 生成5至10之间的随机距离
                double randomAngle = random.nextDouble() * 2 * Math.PI; // 生成一个随机角度
                double offsetX = randomDistance * Math.cos(randomAngle);
                double offsetZ = randomDistance * Math.sin(randomAngle);
                Location nearbyLocation = npcLocation.clone().add(offsetX, 0, offsetZ); // 根据随机距离和角度计算新位置
                npc.getNavigator().setTarget(nearbyLocation);

            }
        } else if (npc.getEntity().getLocation().distance(targetTree.getLocation()) < 6) {
            if (swingCount < 250) {
                if (npc.getEntity() instanceof LivingEntity) {
                    ((LivingEntity) npc.getEntity()).swingMainHand();
                    //player.sendMessage("NPC正在挥手");
                }
                swingCount++;
            } else {
                // 摧毁目标上空所有的东西 并自然掉落
                Location targetLocation = targetTree.getLocation();
                int maxHeight = targetLocation.getWorld().getMaxHeight();
                for (int y = targetLocation.getBlockY() + 1; y < maxHeight; y++) {
                    Block block = targetLocation.getWorld().getBlockAt(targetLocation.getBlockX(), y, targetLocation.getBlockZ());

                    // 如果这个方块不是空气，那么摧毁它
                    if (block.getType() != Material.AIR) {
                        // 掉落物品
                        block.breakNaturally();
                    }
                }
                //targetTree.setType(Material.AIR);
                Location dropLocation = targetTree.getLocation().add(0, 1, 0);
                Item droppedItem = dropLocation.getWorld().dropItem(dropLocation, new ItemStack(Material.OAK_LOG));
                droppedItem.setVelocity(new Vector(0, 0, 0));
                //player.sendMessage("掉落原木");
                reset();
            }
        }else {
            //player.sendMessage("开始继续移动到树");
            if (!npc.getNavigator().isNavigating()) {

                    Location npcLocation = npc.getEntity().getLocation();
                    int randomDistance = random.nextInt(6) + 5; // 生成5至10之间的随机距离
                    double randomAngle = random.nextDouble() * 2 * Math.PI; // 生成一个随机角度
                    double offsetX = randomDistance * Math.cos(randomAngle);
                    double offsetZ = randomDistance * Math.sin(randomAngle);
                    Location nearbyLocation = npcLocation.clone().add(offsetX, 0, offsetZ); // 根据随机距离和角度计算新位置
                    npc.getNavigator().setTarget(nearbyLocation);
                    reset();

            }

        }

    }

    @Override
    public boolean shouldExecute(GoalSelector selector) {
        return npc.isSpawned() && player.isOnline() && npc.getEntity().getWorld().equals(player.getWorld());
    }

    private static final Set<Material> LOG_TYPES = new HashSet<>(Arrays.asList(Material.OAK_LOG, Material.SPRUCE_LOG, Material.BIRCH_LOG, Material.JUNGLE_LOG, Material.ACACIA_LOG, Material.DARK_OAK_LOG));

//    private Block findNearestTree() {
//        for (int x = -10; x <= 10; x++) {
//            for (int y = -10; y <= 10; y++) {
//                for (int z = -10; z <= 10; z++) {
//                    Block block = npc.getEntity().getLocation().add(new Vector(x, y, z)).getBlock();
//                    if (LOG_TYPES.contains(block.getType())) {
//                        Location location = block.getLocation().add(0, 3, 0); // 在方块上方的位置
//                        World world = block.getWorld();
//                        Entity entity = world.spawnEntity(location.add(5, 0, 0), EntityType.ZOMBIE);
//                        if (entity instanceof LivingEntity) { // 透视模式
//                            ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 0, false, false));
//                        }
//
//                        if (entity instanceof LivingEntity) { // 失重模式 也就是禁止它的行为
//                            ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, Integer.MAX_VALUE, 255, false, false));
//                        }
//
//                        if (entity instanceof LivingEntity) { // 隐身模式
//                            ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0, false, false));
//                        }
//
//
//
//                        Location zombieLocation = entity.getLocation();
//                        Location teleportLocation = zombieLocation.clone().add(5, 0, 0); // 在僵尸的东边 5 个方块的位置
//                        //player.teleport(teleportLocation);
//                        // 在这个位置创建一个僵尸
//
////                        npc.getNavigator().setTarget(entity,true); // 在这里立即移动到玩家位置
//                        player.sendMessage("NPC行动");
//                        return zombieLocation.getBlock();
//                    }
//                }
//            }
//        }
//        return null;
//    }

    private Block findNearestTree() {
        for (int x = -10; x <= 10; x++) {
            for (int y = -10; y <= 10; y++) {
                for (int z = -10; z <= 10; z++) {
                    Block block = npc.getEntity().getLocation().add(new Vector(x, y, z)).getBlock();
                    if (LOG_TYPES.contains(block.getType())) {
                        Block belowBlock = block.getRelative(BlockFace.DOWN); // 获取这个方块下面的方块
                        if (belowBlock.getType() == Material.GRASS_BLOCK || belowBlock.getType() == Material.DIRT) { // 如果这个方块下面的方块是草方块或者是土方块
                            return block;
                        }
                    }
                }
            }
        }
        return null;
    }



}
