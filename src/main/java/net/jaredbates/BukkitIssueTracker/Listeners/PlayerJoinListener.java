package net.jaredbates.BukkitIssueTracker.Listeners;

import net.jaredbates.BukkitIssueTracker.BukkitIssueTracker;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.IOException;

public class PlayerJoinListener implements Listener {
    private BukkitIssueTracker plugin;

    public PlayerJoinListener(BukkitIssueTracker plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (plugin.isJoinMessage()) {
            if (player.hasPermission("BukkitIssueTracker.JoinMessage")) {
                try {
                    plugin.setRepository(plugin.getRepositoryService().getRepository(plugin.getUsername(), plugin.getRepositoryName()));
                    int issues = plugin.getRepository().getOpenIssues();
                    player.sendMessage("§7There are currently §c" + issues + " §7issues open.");
                } catch (IOException exception) {
                    plugin.getLogger().severe("Could not connect to repository!");
                }
            }
        }
    }
}
