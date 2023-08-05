package com.chun._.Npc;

import com.chun._.Npc.AIBehavior.Attack;
import com.chun._.Npc.AIBehavior.ChopTreeBehavio;
import com.chun._.Npc.AIBehavior.FollowPlayerBehavior;
import net.citizensnpcs.api.CitizensAPI;
import com.chun._._;
import net.citizensnpcs.api.ai.Navigator;
import net.citizensnpcs.api.ai.NavigatorParameters;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.TraitInfo;
import net.citizensnpcs.trait.LookClose;
import net.citizensnpcs.trait.SkinTrait;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;


import java.util.Collection;
import java.util.Random;

public class ManCommandNEW implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
           // Collection<TraitInfo> registeredTraits = CitizensAPI.getTraitFactory().getRegisteredTraits();

//            for (TraitInfo trait : registeredTraits) {
//                Bukkit.getLogger().info("Registered trait: " + trait.getTraitName());
//            }

            Player player = (Player) sender;
            NPCManagerbase npcManager = new NPCManagerbase();
            NPC npc = npcManager.createNPC(player);
            npctest be = new npctest();
            EmptyTrait be2 =new EmptyTrait();
            be.setTargetPlayer(player);
            npc.addTrait(be);
            npc.addTrait(be2);
            //npc.addTrait(EmptyTrait.class);
            //npc.addTrait(npctest.class);


            //npcManager.setFollowPlayer(npc, player);

            //NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, player.getName());

//            if (npc != null) {
//                // 确保 NPC 已经被创建并分配给 this.npc
//                SkinTrait skin = new SkinTrait();
//                if (npc.hasTrait(SkinTrait.class)) {
//                    npc.getTrait(SkinTrait.class).setSkinName(player.getName(), true);
//                } else {
//                    npc.addTrait(skin);
//                    skin.setSkinName(player.getName(), true);
//                }
//                npc.setName("Player3432");
//            } else {
//                // 提示或处理错误
//            }
//
            // Spawn the NPC near the player
            //Location spawnLocation = player.getLocation().add(2, 0, 2);
            //Block spawnBlock = spawnLocation.getBlock();
            //Block blockAbove = spawnLocation.add(0, 1, 0).getBlock();

            // 如果生成位置的方块或者上方的方块不是空气，那么找到一个新的生成位置
//            if (spawnBlock.getType() != Material.AIR || blockAbove.getType() != Material.AIR) {
//                spawnLocation = player.getWorld().getHighestBlockAt(spawnLocation).getLocation().add(0, 1, 0);
//            }
//
//            npc.spawn(spawnLocation);
//            npc.getEntity().setGravity(true);
            //npc.spawn(player.getLocation().add(2, 0, 2));
            // 注视着玩家
//            npc.addTrait(LookClose.class);
//            LookClose lookClose = npc.getTrait(LookClose.class);
//            lookClose.lookClose(true);


            //Navigator navigator = npc.getNavigator();
//            npc.setProtected(false); // 设置NPC可以被攻击
            //NavigatorParameters params = navigator.getDefaultParameters();
            //params.baseSpeed(1.0f);  // 设置 NPC 的移动速度
            //params.attackRange(2.0);  // 设置 NPC 的攻击范围
            //params.distanceMargin(6.0);  // 设置 NPC 与玩家的距离
            //navigator.setTarget(player, true);  // 设置 NPC 的目标为玩家
//            setNpcEquipment(npc); // 设置NPC的装备
//
//
//            FollowPlayerBehavior behavior = new FollowPlayerBehavior(npc, player);
//            npc.getDefaultGoalController().addGoal(behavior, 1);
//            ChopTreeBehavio behavior2 = new ChopTreeBehavio(npc, player);
//            npc.getDefaultGoalController().addGoal(behavior2, 15);
//            Attack attackBehavior =new Attack(npc);
//            npc.getDefaultGoalController().addGoal(attackBehavior,2);


            // Start a repeating task that moves the NPC to the player's location every second
//            Bukkit.getScheduler().scheduleSyncRepeatingTask(_.instance, new Runnable() {
//                @Override
//                public void run() {
//                    if (player.isOnline()) {
//                        Location playerLocation = player.getLocation();
//                        double x = playerLocation.getX() + getRandomOffset();
//                        double y = playerLocation.getY();
//                        double z = playerLocation.getZ() + getRandomOffset();
//
//                        Location targetLocation = new Location(playerLocation.getWorld(), x, y, z);
//                        npc.teleport(targetLocation, PlayerTeleportEvent.TeleportCause.PLUGIN);
//                    } else {
//                        Bukkit.getScheduler().cancelTasks(_.instance);
//                        npc.destroy();
//                    }
//                }
//
//            }, 0L, getRandomDelay());

            //sender.sendMessage("A clone of you named 'him' has been created and will follow you!");
            return true;
        } else {
            //sender.sendMessage("This command can only be run by a player.");
            return false;
        }
    }




}
