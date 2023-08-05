package com.chun._.Fakebb;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FakeChatSimulator {
    private static List<FakePlayer> players;
    private static Random random;

    public FakeChatSimulator() {
        players = new ArrayList<>();
        random = new Random();

        // 创建一些模拟玩家
        List<String> player1Chat = new ArrayList<>();
        player1Chat.add("h----ello!");
        player1Chat.add("i want to trade some diamonds.");
        player1Chat.add("Can so to the spawn.....");
        player1Chat.add("anybody together?.");
        player1Chat.add("does food?");
        player1Chat.add("i'm giving away free enchanted books");
        player1Chat.add("u can give this to me? i really need it.");

        players.add(new FakePlayer("DEAD彡KILLERメ", 10, true, player1Chat));

        List<String> player2Chat = new ArrayList<>();
        player2Chat.add("no");
        player2Chat.add("who warvival island?");
        player2Chat.add("anyonelding materials?");
        player2Chat.add("nono");
        player2Chat.add("looking for a friendly and active realm to join");

        players.add(new FakePlayer("☆༒महाकाल☆", 5, false, player2Chat));

        List<String> player3Chat = new ArrayList<>();
        player3Chat.add("spare resources to share?");
        player3Chat.add("lookinyone interested?");
        player3Chat.add("doeo find diamonds?");
        player3Chat.add("need htone contraption.");
        player3Chat.add("who wants to team up for a quest?");
        players.add(new FakePlayer("Skynyx", 7, true, player3Chat));

        List<String> player4Chat = new ArrayList<>();
        player4Chat.add("i'm organizing a pvp tournament. join if you're interested");
        player4Chat.add("looking er. let's explore togethe.");
        player4Chat.add("trading i dm me if interested");
        player4Chat.add("need assighold exploration");
        player4Chat.add("selling rare potions at my shop. check it out");
        players.add(new FakePlayer("Blackshark", 2, false, player4Chat));

        List<String> player5Chat = new ArrayList<>();
        player5Chat.add("looking for a horse to buy or trade.");
        player5Chat.add("who wanvival island?");
        player5Chat.add("need heb spawner.");
        player5Chat.add("looking for a skilled builder for my castle project.");
        player5Chat.add("lol");
        players.add(new FakePlayer("⊰ŠԩąƉŏώ⊱", 3, true, player5Chat));

        List<String> player6Chat = new ArrayList<>();
        player6Chat.add("any tips on finding a jungle biome?");
        player6Chat.add("givingnted books. first come, first served.");
        player6Chat.add("anyon materials?");
        player6Chat.add("recruiting players for my skyblock island.");
        player6Chat.add("need help breeding villagers for a trading hall.");
        players.add(new FakePlayer("ShadowDragon", 8, false, player6Chat));

        List<String> player7Chat = new ArrayList<>();
        player7Chat.add("looking for a server with a player-run economy.");
        player7Chat.add("o with a prize for the winner.");
        player7Chat.add(" netherite ingots. dm me.");
        player7Chat.add("lol");
        player7Chat.add("who wants to join my survival games session?");
        players.add(new FakePlayer("spexy", 1, true, player7Chat));

        List<String> player8Chat = new ArrayList<>();
        player8Chat.add("need assistance with an ocean monument raid.");
        player8Chat.add(" realm to join.");
        player8Chat.add("anyone up for some mining ?");
        player8Chat.add("where to find ag?");
        player8Chat.add("looking for a teammate.");
        players.add(new FakePlayer("Widow Curio", 9, false, player8Chat));

        List<String> player9Chat = new ArrayList<>();
        player9Chat.add("who wants to join my faction?");
        player9Chat.add("- -.");
        player9Chat.add("no");
        player9Chat.add("no!!!!!!!");
        player9Chat.add("i can not");
        players.add(new FakePlayer("♛+°:.DarKGirL.:°+♛", 4, true, player9Chat));

        List<String> player10Chat = new ArrayList<>();
        player10Chat.add("lol");
        player10Chat.add("selling , message me.");
        player10Chat.add("r~~~~~~~~~~~~~~~~~~~~~~~~~~~.");
        player10Chat.add("bro dp ");
        player10Chat.add("o need gold u kon?????");
        players.add(new FakePlayer("I want URF Back.", 6, false, player10Chat));

        List<String> player11Chat = new ArrayList<>();
        player11Chat.add("trading diamonds for netherite ingots.");
        player11Chat.add("who wants ?");
        player11Chat.add(" help with my castle.");
        player11Chat.add("jungle biome?");
        player11Chat.add("selling .");
        players.add(new FakePlayer("zReeZ_", 7, true, player11Chat));


// ... 按照这个模式，你可以继续创建更多的模拟玩家


        // ... 添加更多的模拟玩家
    }

    public static void simulateChat() {
        for (FakePlayer player : players) {
            new Thread(() -> {
                while (true) {
                    Collections.shuffle(player.chatContents);

                    for (String message : player.chatContents) {
                        String prefix = player.isVip ? ChatColor.GOLD + "[VIP] " : "";

                        // 根据等级设置颜色
                        ChatColor levelColor;
                        if (player.level <= 3) {
                            levelColor = ChatColor.GREEN;
                        } else if (player.level <= 5) {
                            levelColor = ChatColor.YELLOW;
                        } else {
                            levelColor = ChatColor.DARK_PURPLE;
                        }

                        Bukkit.broadcastMessage(prefix + levelColor + "[" + player.level + "] " + ChatColor.GREEN + player.name + ChatColor.WHITE + ": " + message);

                        try {
                            // 随机间隔时间，比如3-25秒
                            Thread.sleep((random.nextInt(200) + 100) * 1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }







    private class FakePlayer {
        String name;
        int level;
        boolean isVip;
        List<String> chatContents;

        FakePlayer(String name, int level, boolean isVip, List<String> chatContents) {
            this.name = name;
            this.level = level;
            this.isVip = isVip;
            this.chatContents = chatContents;
        }
    }
}
