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
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.mcmonkey.sentinel.SentinelTrait;

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
        params.baseSpeed(2.0f);  // 设置 NPC 的移动速度
        params.attackRange(5.0);  // 设置 NPC 的攻击范围
        params.distanceMargin(1);  // 设置 NPC 与玩家的距离


        SentinelTrait sentinel = npc.getTrait(SentinelTrait.class);
        sentinel.attackRate = 15;
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "npc select " + npc.getId());
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "sentinel range 199");


        // 设置NPC的目标
        sentinel.addTarget("monsters");
        sentinel.addTarget("npcs");
        sentinel.addTarget("players");
        //navigator.setTarget(player, true);  // 设置 NPC 的目标为玩家
        setNpcEquipment(npc); // 设置NPC的装备
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
                // 创建一个随机数生成器
                Random random = new Random();

                // 创建一个包含所有可能装备的列表，包括null
                Material[] helmets = {Material.DIAMOND_HELMET, Material.GOLDEN_HELMET, Material.IRON_HELMET, Material.LEATHER_HELMET, null};
                Material[] chestplates = {Material.DIAMOND_CHESTPLATE, Material.GOLDEN_CHESTPLATE, Material.IRON_CHESTPLATE, Material.LEATHER_CHESTPLATE, null};
                Material[] leggings = {Material.DIAMOND_LEGGINGS, Material.GOLDEN_LEGGINGS, Material.IRON_LEGGINGS, Material.LEATHER_LEGGINGS, null};
                Material[] boots = {Material.DIAMOND_BOOTS, Material.GOLDEN_BOOTS, Material.IRON_BOOTS, Material.LEATHER_BOOTS, null};
                Material[] weapons = {Material.DIAMOND_SWORD, Material.GOLDEN_SWORD, Material.IRON_SWORD, Material.WOODEN_SWORD, Material.STONE_SWORD, null};

                // 随机选择装备
                ItemStack helmet = createRandomEnchantedItem(helmets[random.nextInt(helmets.length)], random);
                ItemStack chestplate = createRandomEnchantedItem(chestplates[random.nextInt(chestplates.length)], random);
                ItemStack legging = createRandomEnchantedItem(leggings[random.nextInt(leggings.length)], random);
                ItemStack boot = createRandomEnchantedItem(boots[random.nextInt(boots.length)], random);
                ItemStack weapon = createRandomEnchantedItem(weapons[random.nextInt(weapons.length)], random);

                equipment.setHelmet(helmet);
                equipment.setChestplate(chestplate);
                equipment.setLeggings(legging);
                equipment.setBoots(boot);
                equipment.setItemInMainHand(weapon);
            }
        }
    }

    private ItemStack createRandomEnchantedItem(Material material, Random random) {
        if (material == null) {
            return null;
        }

        ItemStack item = new ItemStack(material);

        // 有30%的几率附魔
        if (random.nextDouble() < 0.3) {
            ItemMeta meta = item.getItemMeta();

            // 随机选择一个附魔
            Enchantment enchantment = Enchantment.values()[random.nextInt(Enchantment.values().length)];

            // 随机选择一个附魔等级（1-5级）
            int level = random.nextInt(5) + 1;

            // 添加附魔
            meta.addEnchant(enchantment, level, true);

            item.setItemMeta(meta);
        }

        return item;
    }


}

    // 添加其他方法...



