package com.chun._.Npc;
import net.skinsrestorer.api.PlayerWrapper;
import net.skinsrestorer.api.SkinsRestorerAPI;
import net.skinsrestorer.api.exception.SkinRequestException;
import net.skinsrestorer.api.property.IProperty;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class NPCManager {

    private static SkinsRestorerAPI skinsRestorerAPI;
    public static void spawnVillagerWithSkin(Player player, Location location) throws SkinRequestException {

        skinsRestorerAPI = SkinsRestorerAPI.getApi();
        // 创建 SkinsRestorerAPI 的一个实例

        // 在指定位置生成一个村民
        Villager villager = player.getWorld().spawn(location, Villager.class);

        // 获取玩家的皮肤
        String skinName = skinsRestorerAPI.getSkinName(player.getName());

        // 将村民的皮肤更改为玩家的皮肤
        skinsRestorerAPI.setSkin(villager.getName(), skinName);

        // 应用皮肤到村民
        //skinsRestorerAPI.applySkin(villager.getPlayer());
    }


}
