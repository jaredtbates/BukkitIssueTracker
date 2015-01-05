package net.jaredbates.BukkitIssueTracker.Inventory;

import net.jaredbates.BukkitIssueTracker.BukkitIssueTracker;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ControlPanelInventory {
    private BukkitIssueTracker plugin = BukkitIssueTracker.getInstance();
    private Inventory panel = Bukkit.createInventory(null, 9, "§bIssue Control Panel");

    public ControlPanelInventory(Player player) {
        ItemStack viewOpenIssuesItem = new ItemStack(Material.ANVIL);
        ItemMeta viewOpenIssuesItemMeta = viewOpenIssuesItem.getItemMeta();
        viewOpenIssuesItemMeta.setDisplayName("§aView Open Issues");
        viewOpenIssuesItem.setItemMeta(viewOpenIssuesItemMeta);
        panel.addItem(viewOpenIssuesItem);
        player.openInventory(panel);
    }
}
