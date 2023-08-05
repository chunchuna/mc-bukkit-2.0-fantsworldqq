package com.chun._.BuildingSystem;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

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

}
