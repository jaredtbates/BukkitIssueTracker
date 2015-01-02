package net.jaredbates.BukkitIssueTracker.Inventory;

import net.jaredbates.BukkitIssueTracker.BukkitIssueTracker;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class PanelInventory {
    private BukkitIssueTracker plugin = BukkitIssueTracker.getInstance();
    private Inventory panel = Bukkit.createInventory(null, 9, "Control Panel");

    public PanelInventory(Player player) {

        player.openInventory(panel);
    }
}
