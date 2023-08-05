package CustItem;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomItemListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack item = event.getItem();

        if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
            if (item != null && item.hasItemMeta()) {
                ItemMeta meta = item.getItemMeta();
                assert meta != null;
                if (meta.getDisplayName().equals(ChatColor.GREEN + "无敌牛逼苹果")) {
                    player.launchProjectile(Fireball.class); // 生成一个大火球
                }
            }
        }
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Snowball) {
            Projectile projectile = event.getEntity();
            if (projectile.getShooter() instanceof Player) {
                Entity hitEntity = event.getHitEntity();
                if (hitEntity != null && hitEntity instanceof LivingEntity) {
                    ((LivingEntity) hitEntity).damage(30.0);
                }
            }
        }
    }


    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        Entity entity = event.getEntity();
        World world = entity.getWorld();
        Location location = entity.getLocation();

        if (entity instanceof Fireball) {
            int radius = 3; // 设置搜索半径

            for (int x = -radius; x <= radius; x++) {
                for (int y = -radius; y <= radius; y++) {
                    for (int z = -radius; z <= radius; z++) {
                        Block block = world.getBlockAt(location.getBlockX() + x, location.getBlockY() + y, location.getBlockZ() + z);

                        // 检查方块是否是树干
                        if (block.getType().name().contains("LOG")) {
                            block.setType(Material.AIR); // 移除树干

                            // 生成钻石
                            ItemStack diamonds = new ItemStack(Material.DIAMOND, 30);
                            world.dropItemNaturally(block.getLocation(), diamonds);
                        }
                    }
                }
            }
        }
    }
}
