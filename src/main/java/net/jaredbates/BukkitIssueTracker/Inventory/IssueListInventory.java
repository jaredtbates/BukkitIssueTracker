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
    private Inventory issueList = Bukkit.createInventory(null, 54, "§bIssue List");

    public IssueListInventory(Player player) {
        try {
            plugin.setRepository(plugin.getRepositoryService().getRepository(plugin.getUsername(), plugin.getRepositoryName()));
            int i = 0;
            for (Issue issue : plugin.getIssueService().getIssues(plugin.getUsername(), plugin.getRepositoryName(), null)) {
                ItemStack issueItem = new ItemStack(Material.WOOL, 1, (short) 14);
                ItemMeta issueItemMeta = issueItem.getItemMeta();
                issueItemMeta.setDisplayName("§c" + issue.getTitle());
                issueItemMeta.setLore(Arrays.asList("§7#" + issue.getNumber()));
                issueItem.setItemMeta(issueItemMeta);
                issueList.setItem(i, issueItem);
                i++;
            }
            if (i == 0) {
                ItemStack noIssues = new ItemStack(Material.WOOL, 1, (short) 5);
                ItemMeta noIssuesMeta = noIssues.getItemMeta();
                noIssuesMeta.setDisplayName("§aNo Issues!");
                noIssues.setItemMeta(noIssuesMeta);
                for (int n = 0; n < 54; n++) {
                    issueList.setItem(n, noIssues);
                }
            }
            player.openInventory(issueList);
        } catch (Exception e) {
            plugin.getLogger().severe("Could not connect to repository!");
        }
    }
}
