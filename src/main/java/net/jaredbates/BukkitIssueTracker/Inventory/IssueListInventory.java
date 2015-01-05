package net.jaredbates.BukkitIssueTracker.Inventory;

import net.jaredbates.BukkitIssueTracker.BukkitIssueTracker;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.eclipse.egit.github.core.Issue;

import java.util.Arrays;

public class IssueListInventory {
    private BukkitIssueTracker plugin = BukkitIssueTracker.getInstance();
    private Inventory panel = Bukkit.createInventory(null, 9, "§bIssue List");

    public IssueListInventory(Player player) {
        try {
            plugin.setRepository(plugin.getRepositoryService().getRepository(plugin.getUsername(), plugin.getRepositoryName()));
            int i = 0;
            for (Issue issue : plugin.getIssueService().getIssues(plugin.getUsername(), plugin.getRepositoryName(), null)) {
                ItemStack issueItem = new ItemStack(Material.WOOL, 1, (short) 14);
                ItemMeta issueItemMeta = issueItem.getItemMeta();
                issueItemMeta.setDisplayName("§a" + issue.getTitle());
                issueItemMeta.setLore(Arrays.asList("§7#" + issue.getNumber()));
                issueItem.setItemMeta(issueItemMeta);
                panel.setItem(i, issueItem);
                i++;
            }
            player.openInventory(panel);
        } catch (Exception e) {
            plugin.getLogger().severe("Could not connect to repository!");
        }
    }
}
