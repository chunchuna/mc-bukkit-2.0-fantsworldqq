package com.chun._.Npc;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.ai.Navigator;
import net.citizensnpcs.api.ai.NavigatorParameters;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.Trait;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class NPCManagerbase {
    private final Map<NPC, npctest> npctest = new HashMap<>();

    public NPC createNPC(Player player) {
        List<String> names = Arrays.asList(
                "Notch", "Intel_7", "RafiMC", "ArchitectHyper", "GiiMiner", "Meralline",
                "BauPixelDev", "AqMin", "stabwounds", "DinoTopse", "CasinoBandit",
                "TripleTowers", "Enkler", "Verger", "Rowin", "12skitzo", "_EnjoyMyPvP_"
        );

        Random random = new Random();
        String randomName = names.get(random.nextInt(names.size()));
        NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, randomName);

        //NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, player.getName());

        //Spawn the NPC near the player

       // Location spawnLocation = player.getLocation().add(2, 0, 2);
        double offsetX = (Math.random() - 0.5) * 100;
        double offsetZ = (Math.random() - 0.5) * 100;

// 在玩家附近生成一个随机的位置
        Location spawnLocation = player.getLocation().add(offsetX, 0, offsetZ);
        Block spawnBlock = spawnLocation.getBlock();
        Block blockAbove = spawnLocation.add(0, 1, 0).getBlock();
        //如果生成位置的方块或者上方的方块不是空气，那么找到一个新的生成位置
        if (spawnBlock.getType() != Material.AIR || blockAbove.getType() != Material.AIR) {
            spawnLocation = player.getWorld().getHighestBlockAt(spawnLocation).getLocation().add(0, 1, 0);
        }

        npc.spawn(spawnLocation);
        if (!npc.getEntity().getLocation().getBlock().getRelative(BlockFace.DOWN).getType().isSolid()) {
            // 如果在空中，将其移动到地面
            Location groundLocation = npc.getEntity().getWorld().getHighestBlockAt(npc.getEntity().getLocation()).getLocation();
            npc.teleport(groundLocation, PlayerTeleportEvent.TeleportCause.PLUGIN);
        }
        npc.getEntity().setGravity(true);
        //npc.spawn(player.getLocation().add(2, 0, 2));
        //注视着玩家
        //npc.addTrait(LookClose.class);
        //LookClose lookClose = npc.getTrait(LookClose.class);
        //lookClose.lookClose(true);

        Navigator navigator = npc.getNavigator();
        npc.setProtected(false); // 设置NPC可以被攻击
        NavigatorParameters params = navigator.getDefaultParameters();
        params.baseSpeed(1.0f);  // 设置 NPC 的移动速度
        params.attackRange(2.0);  // 设置 NPC 的攻击范围
        params.distanceMargin(3);  // 设置 NPC 与玩家的距离
        //navigator.setTarget(player, true);  // 设置 NPC 的目标为玩家
        //setNpcEquipment(npc); // 设置NPC的装备
        return npc;
    }

    public void setFollowPlayer(NPC npc, Player player) {
        npctest behavior = npctest.get(npc);
        if (behavior != null) {
            behavior.followPlayer(player);
        }
    }

    public void goToLocation(NPC npc, Location location) {
        npctest behavior = npctest.get(npc);
        if (behavior != null) {
            behavior.goToLocation(location);
        }
    }

    public void setNpcEquipment(NPC npc) {
        if (npc.getEntity() instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) npc.getEntity();

            // 获取实体的装备
            EntityEquipment equipment = livingEntity.getEquipment();

            if (equipment != null) {
                // 设置主手物品
                equipment.setItemInMainHand(new ItemStack(Material.TORCH));
                // 设置头部装备
                equipment.setHelmet(new ItemStack(Material.DIAMOND_HELMET));
                // 设置胸部装备
                //equipment.setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
                // 设置腿部装备
                //equipment.setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
                // 设置脚部装备
                //equipment.setBoots(new ItemStack(Material.DIAMOND_BOOTS));
            }
        }
    }

    // 添加其他方法...


}
