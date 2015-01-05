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
import org.eclipse.egit.github.core.Issue;

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
            if (item.getType() != Material.AIR) {
                if (inventory.getName().equals("§bIssue Control Panel")) {
                    event.setCancelled(true);
                    player.playSound(player.getLocation(), Sound.NOTE_PLING, 10, 1.5F);
                    player.closeInventory();
                    switch (slot) {
                        case 0:
                            new IssueListInventory(player);
                        default:
                            player.sendMessage("§aMore features are coming soon! Please send computerwizjared a message on SpigotMC if you would like a specific feature!");
                    }
                } else if (inventory.getName().equals("§bIssue List")) {
                    event.setCancelled(true);
                    try {
                        player.closeInventory();
                        player.playSound(player.getLocation(), Sound.NOTE_PLING, 10, 1.5F);
                        if (item.getDurability() == 14) {
                            Issue issue = plugin.getIssueService().getIssue(plugin.getRepository(), item.getItemMeta().getLore().get(0).replace("§7#", ""));
                            player.sendMessage("§bIssue Body:\n§f" + issue.getBody() + "\n§bView this issue at: §a" + issue.getHtmlUrl());
                        } else {
                            player.sendMessage("§aNo issues are currently open!");
                        }
                    } catch (Exception e) {
                        plugin.getLogger().severe("Could not connect to repository!");
                    }
                }
            }
        }
    }
}
