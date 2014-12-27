package net.jaredbates.BukkitIssueTracker;

import lombok.Getter;
import net.jaredbates.BukkitIssueTracker.Listeners.PlayerJoinListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.eclipse.egit.github.core.client.GitHubClient;

public class BukkitIssueTracker extends JavaPlugin {
    @Getter private GitHubClient gitHubClient = new GitHubClient();
    @Getter private boolean oAuth = false;

    public void onEnable() {
        saveDefaultConfig();
        oAuth = getConfig().getBoolean("OAuth.Enabled");
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
    }

    public void onDisable() {

    }
}
