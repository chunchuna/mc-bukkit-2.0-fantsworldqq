package CustItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomItem {
    private String name;
    private Material material;
    private int damage;
    private int durability;

    public CustomItem(String name, Material material, int damage, int durability) {
        this.name = ChatColor.translateAlternateColorCodes('&', name);
        this.material = material;
        this.damage = damage;
        this.durability = durability;
    }

    public ItemStack createItem() {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        // 设置物品名称
        meta.setDisplayName(name);

        if (material.equals(Material.DIAMOND_SWORD)) {
            // 设置剑的伤害
            item.setDurability((short) damage);
        } else if (material.equals(Material.APPLE)) {
            // 设置苹果的属性
            // 这里可以根据需要添加其他的物品类型的自定义属性设置
        } else if (material.equals(Material.BOOK)) {
            // 设置书的属性
            // 这里可以根据需要添加其他的物品类型的自定义属性设置
        }

        // 设置物品耐久度
        item.setDurability((short) durability);

        item.setItemMeta(meta);
        return item;
    }

    public void giveToPlayer(Player player) {
        ItemStack item = createItem();
        player.getInventory().addItem(item);
    }



}


