package CustItem;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class ItemGiver {

    public static void GetApple(PlayerJoinEvent event){

        Player player = event.getPlayer();
        UUID playerId = player.getUniqueId();
        // get item
        CustomItem apple = new CustomItem("&a无敌牛逼苹果", Material.APPLE, 0, 1);
        ItemStack appleItem = apple.createItem();
        ItemMeta appleMeta = appleItem.getItemMeta();
        appleMeta.addEnchant(Enchantment.LURE, 1, true);
        appleItem.setItemMeta(appleMeta);
        player.getInventory().addItem(appleItem);
    }

}
