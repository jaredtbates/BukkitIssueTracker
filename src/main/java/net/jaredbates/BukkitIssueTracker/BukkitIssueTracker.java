package net.jaredbates.BukkitIssueTracker;

import lombok.Getter;
import net.jaredbates.BukkitIssueTracker.Listeners.PlayerJoinListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.eclipse.egit.github.core.client.GitHubClient;

public class BukkitIssueTracker extends JavaPlugin {
    @Getter private GitHubClient gitHubClient = new GitHubClient();
    @Getter
    private boolean gitHubOAuth = false;
    @Getter
    private String gitHubUsername;
    @Getter
    private String gitHubPassword;
    @Getter
    private String oAuthToken;

    public void onEnable() {
        saveDefaultConfig();
        gitHubOAuth = getConfig().getBoolean("OAuth.Enabled");
        if (isGitHubOAuth()) {
            oAuthToken = getConfig().getString("OAuth.Token");
        } else {
            gitHubUsername = getConfig().getString("GitHub.Credentials.Username");
            gitHubPassword = getConfig().getString("GitHub.Credentials.Password");
        }
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
    }

    public void onDisable() {

    }
}
