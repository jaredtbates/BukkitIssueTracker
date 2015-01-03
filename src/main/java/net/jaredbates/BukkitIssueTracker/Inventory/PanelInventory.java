package net.jaredbates.BukkitIssueTracker.Inventory;

import net.jaredbates.BukkitIssueTracker.BukkitIssueTracker;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.eclipse.egit.github.core.Issue;

import java.io.IOException;

public class PanelInventory {
    private BukkitIssueTracker plugin = BukkitIssueTracker.getInstance();
    private Inventory panel = Bukkit.createInventory(null, 9, "Issues Control Panel");

    public PanelInventory(Player player) {
        try {
            plugin.setRepository(plugin.getRepositoryService().getRepository(plugin.getUsername(), plugin.getRepositoryName()));
            for (Issue issue : plugin.getIssueService().getIssues()) {

            }
            player.openInventory(panel);
        } catch (IOException e) {
            plugin.getLogger().severe("Could not connect to repository!");
        }
    }
}
