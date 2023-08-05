package com.chun._.Fakebb;

import com.chun._._;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.TabCompleteEvent;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class Fakebb  implements Listener {
    private static List<String> fakePlayerNames = new ArrayList<>();

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_";
    private static final int NAME_LENGTH = 10;
    private static final Random RANDOM = new Random();


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        generateFakePlayerNames();
    }

    public static void generateFakePlayerNames() {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();

        // 检查目标是否已经存在
        Objective objective = scoreboard.getObjective("test");
        if (objective == null) {
            // 如果目标不存在，创建新的目标
            objective = scoreboard.registerNewObjective("test", "dummy", "VIP");
        } else {
            // 如果目标已经存在，删除旧的目标并创建新的目标
            objective.unregister();
            objective = scoreboard.registerNewObjective("test", "dummy", "VIP");
        }

        // 创建一个ArrayList来存储你自己设定的名字
        List<String> names = new ArrayList<>();
        names.add("Street Squirrel");
        names.add("ĎâŘk°ØmÈğĄ");
        names.add("Zelda");
        names.add("Rohan");
        names.add("Paindark");
        names.add("__Sneaky Sneak");
        names.add("Rohan");
        names.add("thef|ow");
        names.add("MiStiK-_-SneaZz");
        names.add("High_Prognose");
        names.add("Kaysey");
        names.add("by wakee");

        // ... 添加更多的名字

        for (int i = 0; i < names.size(); i++) {
            String fakePlayerName = names.get(i);
            Score score = objective.getScore(fakePlayerName);
            score.setScore(i * 10);
        }

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    }


    private static String generateRandomName() {
        StringBuilder builder = new StringBuilder(NAME_LENGTH);

        for (int i = 0; i < NAME_LENGTH; i++) {
            char randomChar = CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length()));
            builder.append(randomChar);
        }

        return builder.toString();
    }
    public static String generateRandomNumber() {
        Random random = new Random();
        int number = random.nextInt(10000); // Generate a random number up to 9999
        return String.valueOf(number);
    }

    private static String getRandomCasing(String input) {
        Random random = new Random();
        StringBuilder output = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (random.nextBoolean()) {
                output.append(Character.toUpperCase(c));
            } else {
                output.append(Character.toLowerCase(c));
            }
        }
        return output.toString();
    }


    public static void Init(){
        Fakebb FakebbLis =new Fakebb();

        Bukkit.getPluginManager().registerEvents(FakebbLis, _.instance);
        generateFakePlayerNames();
    }






}
