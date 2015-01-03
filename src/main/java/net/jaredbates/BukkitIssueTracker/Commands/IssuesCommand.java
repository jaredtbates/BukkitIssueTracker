package net.jaredbates.BukkitIssueTracker.Commands;

import net.jaredbates.BukkitIssueTracker.BukkitIssueTracker;
import net.jaredbates.BukkitIssueTracker.Inventory.PanelInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class IssuesCommand implements CommandExecutor {
    private BukkitIssueTracker plugin;

    public IssuesCommand(BukkitIssueTracker plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            new PanelInventory(player);
            return true;
        }
        return false;
    }
}
