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
                    plugin.setRepository(plugin.getRepositoryService().getRepository(plugin.getRepository()));
                    int issues = plugin.getRepository().getOpenIssues();
                    if (issues != 0) {
                        player.sendMessage("§7There are currently §c" + issues + " §7issues open.");
                        player.sendMessage("§7View these issues with §c/issues§7.");
                    }
                } catch (IOException e) {
                    plugin.getLogger().severe("Could not connect to repository!");
                    e.printStackTrace();
                }
            }
        }
    }
}
