package net.jaredbates.BukkitIssueTracker.Listeners;

import net.jaredbates.BukkitIssueTracker.BukkitIssueTracker;
import net.jaredbates.BukkitIssueTracker.Inventory.IssueListInventory;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener implements Listener {
    public BukkitIssueTracker plugin;

    public InventoryClickListener(BukkitIssueTracker plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();
        ItemStack item = event.getCurrentItem();
        int slot = event.getSlot();
        if (item != null) {
            if (inventory.getName().equals("§bIssue Control Panel")) {
                event.setCancelled(true);
                player.playSound(player.getLocation(), Sound.NOTE_PLING, 10, 1.3F);
                switch (slot) {
                    case 0:
                        player.closeInventory();
                        new IssueListInventory(player);
                }
            } else if (inventory.getName().equals("§bIssue List")) {
                event.setCancelled(true);
                if (item.getType() != Material.AIR) {
                    try {
                        player.playSound(player.getLocation(), Sound.NOTE_PLING, 10, 1.3F);
                        player.sendMessage("Clicked on " + plugin.getIssueService().getIssue(plugin.getRepository(), item.getItemMeta().getLore().get(0).replace("§7#", "")).getTitle() + "!");
                    } catch (Exception e) {
                        plugin.getLogger().severe("Could not connect to repository!");
                    }
                }
            }
        }
    }
}
