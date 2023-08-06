package com.chun._.BuildingSystem;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class BuidlingBoy {

    public void generateBuilding_1(Player player) {
        World world = player.getWorld();
        Location location = player.getLocation();

        // 计算建筑的位置
        int startX = location.getBlockX();
        int startY = location.getBlockY();
        int startZ = location.getBlockZ();

        // 生成一个10x10x4的房间
        for (int x = startX; x < startX + 10; x++) {
            for (int y = startY; y < startY + 4; y++) {
                for (int z = startZ; z < startZ + 10; z++) {
                    Block block = world.getBlockAt(x, y, z);
                    // 如果是边界，设置为石头，否则设置为空气
                    if (x == startX || x == startX + 9 || y == startY || y == startY + 3 || z == startZ || z == startZ + 9) {
                        block.setType(Material.STONE);
                    } else {
                        block.setType(Material.AIR);
                    }
                }
            }
        }

        // 在中间放置熔炉和工作台
        world.getBlockAt(startX + 5, startY + 1, startZ + 5).setType(Material.FURNACE);
        world.getBlockAt(startX + 5, startY + 1, startZ + 6).setType(Material.CRAFTING_TABLE);

        // 设置一个门
        world.getBlockAt(startX, startY + 1, startZ + 5).setType(Material.AIR);
        world.getBlockAt(startX, startY + 2, startZ + 5).setType(Material.AIR);
    }


    public static void generateBuilding_2(Player player) {
        World world = player.getWorld();
        Location location = player.getLocation().add(10, 0, 10); // 在玩家附近生成建筑

        // 计算建筑的位置
        int startX = location.getBlockX();
        int startY = location.getBlockY();
        int startZ = location.getBlockZ();

        // 生成随机的房间大小
        int roomSize = 10 + (int) (Math.random() * 5);
        int roomHeight = 1 + (int) (Math.random() * 2);

        // 生成房间
        for (int x = startX; x < startX + roomSize; x++) {
            for (int y = startY; y < startY + roomHeight; y++) {
                for (int z = startZ; z < startZ + roomSize; z++) {
                    Block block = world.getBlockAt(x, y, z);
                    // 如果是边界，设置为石头，否则设置为空气
                    if (x == startX || x == startX + roomSize - 1 || y == startY || y == startY + roomHeight - 1 || z == startZ || z == startZ + roomSize - 1) {
                        block.setType(Material.STONE);
                    } else {
                        block.setType(Material.AIR);
                    }
                }
            }
        }

        // 在随机的位置放置熔炉和工作台
        world.getBlockAt(startX + (int) (Math.random() * roomSize), startY + 1, startZ + (int) (Math.random() * roomSize)).setType(Material.FURNACE);
        world.getBlockAt(startX + (int) (Math.random() * roomSize), startY + 1, startZ + (int) (Math.random() * roomSize)).setType(Material.CRAFTING_TABLE);

        // 在随机的位置放置箱子
        for (int i = 0; i < (int) (Math.random() * 5); i++) {
            world.getBlockAt(startX + (int) (Math.random() * roomSize), startY + 1, startZ + (int) (Math.random() * roomSize)).setType(Material.CHEST);
        }

        // 设置一个门
        world.getBlockAt(startX, startY + 1, startZ + roomSize / 2).setType(Material.AIR);
        world.getBlockAt(startX, startY + 2, startZ + roomSize / 2).setType(Material.AIR);
    }

    public static void generateBuilding_3(Player player) {
        World world = player.getWorld();

        // 在玩家附近200格的随机位置生成建筑
        double offsetX = (Math.random() - 0.5) * 400;
        double offsetZ = (Math.random() - 0.5) * 400;
        Location location = player.getLocation().add(offsetX, 0, offsetZ);

        // 计算建筑的位置
        int startX = location.getBlockX();
        int startY = location.getBlockY();
        int startZ = location.getBlockZ();

        // 生成随机的房间大小
        int roomSize = 10 + (int) (Math.random() * 5);
        int roomHeight = 3 + (int) (Math.random() * 3); // 增加房间的高度

        // 生成房间
        for (int x = startX; x < startX + roomSize; x++) {
            for (int y = startY; y < startY + roomHeight; y++) {
                for (int z = startZ; z < startZ + roomSize; z++) {
                    Block block = world.getBlockAt(x, y, z);
                    // 如果是边界或者是底部，设置为石头，否则设置为空气
                    if (x == startX || x == startX + roomSize - 1 || y == startY || y == startY + roomHeight - 1 || z == startZ || z == startZ + roomSize - 1) {
                        block.setType(Material.STONE);
                    } else {
                        block.setType(Material.AIR);
                    }
                }
            }
        }

        // 在随机的位置放置熔炉和工作台
        world.getBlockAt(startX + (int) (Math.random() * (roomSize - 2)) + 1, startY + 1, startZ + (int) (Math.random() * (roomSize - 2)) + 1).setType(Material.FURNACE);
        world.getBlockAt(startX + (int) (Math.random() * (roomSize - 2)) + 1, startY + 1, startZ + (int) (Math.random() * (roomSize - 2)) + 1).setType(Material.CRAFTING_TABLE);

        // 在随机的位置放置箱子
        for (int i = 0; i < (int) (Math.random() * 5); i++) {
            world.getBlockAt(startX + (int) (Math.random() * (roomSize - 2)) + 1, startY + 1, startZ + (int) (Math.random() * (roomSize - 2)) + 1).setType(Material.CHEST);
        }

        // 设置一个门
        world.getBlockAt(startX, startY + 1, startZ + roomSize / 2).setType(Material.AIR);
        world.getBlockAt(startX, startY + 2, startZ + roomSize / 2).setType(Material.AIR);
    }

//    public static void generateBuilding_4(Player player, int count) {
//        for (int i = 0; i < count; i++) {
//            // 获取玩家的位置
//            Location playerLocation = player.getLocation();
//
//            // 生成随机位置
//            double offsetX = (Math.random() - 0.5) * 300;
//            double offsetZ = (Math.random() - 0.5) * 300;
//            Location randomLocation = playerLocation.add(offsetX, 0, offsetZ);
//
//            // 找到地面位置
//            Location groundLocation = randomLocation.getWorld().getHighestBlockAt(randomLocation).getLocation();
//
//            // 在地面上生成火把
//            groundLocation.getBlock().setType(Material.TORCH);
//
//            // 在地面上的旁边生成火堆
//            Location campfireLocation = groundLocation.add(1, 0, 0);
//            campfireLocation.getBlock().setType(Material.CAMPFIRE);
//        }
//    }

    public static void generateBuilding_4(Player player, int count) {
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            // 获取玩家的位置
            Location playerLocation = player.getLocation();

            // 生成随机位置
            double offsetX = (Math.random() - 0.5) * 300;
            double offsetZ = (Math.random() - 0.5) * 300;
            Location randomLocation = playerLocation.add(offsetX, 0, offsetZ);

            // 找到地面位置
            Location groundLocation = randomLocation.getWorld().getHighestBlockAt(randomLocation).getLocation();

            // 生成1-6个火把
            int torchCount = random.nextInt(6) + 1;
            for (int j = 0; j < torchCount; j++) {
                // 在地面上生成火把
                Location torchLocation = groundLocation.clone().add(j, 1, 0);
                torchLocation.getBlock().setType(Material.TORCH);
            }
        }
    }


    public static void generateBuilding_5(Player player, int count) {
        for (int i = 0; i < count; i++) {
            // 获取玩家的位置
            Location playerLocation = player.getLocation();

            // 生成随机位置
            double offsetX = (Math.random() - 0.5) * 500;
            double offsetZ = (Math.random() - 0.5) * 500;
            Location randomLocation = playerLocation.add(offsetX, 0, offsetZ);

            // 找到地面位置
            Location groundLocation = randomLocation.getWorld().getHighestBlockAt(randomLocation).getLocation();

            // 在地面上生成工作台
            groundLocation.getBlock().setType(Material.CRAFTING_TABLE);

            // 在工作台旁边生成熔炉
            Location furnaceLocation = groundLocation.add(1, 0, 0);
            furnaceLocation.getBlock().setType(Material.FURNACE);
        }
    }


    public static void generateBuilding_6(Player player, int count) {
        for (int i = 0; i < count; i++) {
            // 获取玩家的位置
            Location playerLocation = player.getLocation();

            // 生成随机位置
            double offsetX = (Math.random() - 0.5) * 500;
            double offsetZ = (Math.random() - 0.5) * 500;
            Location randomLocation = playerLocation.add(offsetX, 0, offsetZ);

            // 找到地面位置
            Location groundLocation = randomLocation.getWorld().getHighestBlockAt(randomLocation).getLocation();

            // 在地面上生成小房子
            generateHouse(groundLocation);
        }
    }

    public static void generateHouse(Location location) {
        Random random = new Random();

        // 生成房子的墙壁
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < random.nextInt(3) + 3; y++) {  // 随机高度
                for (int z = 0; z < 5; z++) {
                    // 随机生成洞
                    if (random.nextInt(10) != 0) {  // 10%的概率生成洞
                        location.clone().add(x, y, z).getBlock().setType(Material.COBBLESTONE);
                    }
                }
            }
        }

        // 生成房子的门
        location.clone().add(2, 0, 0).getBlock().setType(Material.OAK_DOOR);

        // 生成房子的窗户
        location.clone().add(0, 2, 2).getBlock().setType(Material.GLASS);
        location.clone().add(4, 2, 2).getBlock().setType(Material.GLASS);

        // 在房子内部生成火把
        location.clone().add(2, 2, 2).getBlock().setType(Material.TORCH);

        // 在房子内部生成箱子
        Block chestBlock = location.clone().add(2, 1, 2).getBlock();
        chestBlock.setType(Material.CHEST);

        // 在箱子内部生成随机物品
        Chest chest = (Chest) chestBlock.getState();
        chest.getInventory().addItem(new ItemStack(Material.DIAMOND, 1));
        chest.getInventory().addItem(new ItemStack(Material.BREAD, 5));
        chest.getInventory().addItem(new ItemStack(Material.IRON_SWORD, 1));
    }

}
