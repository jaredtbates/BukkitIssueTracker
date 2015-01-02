package net.jaredbates.BukkitIssueTracker;

import lombok.Getter;
import lombok.Setter;
import net.jaredbates.BukkitIssueTracker.Listeners.PlayerJoinListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.RepositoryService;

import java.io.IOException;

public class BukkitIssueTracker extends JavaPlugin {
    @Getter
    private static BukkitIssueTracker instance;
    @Getter
    private GitHubClient gitHubClient = new GitHubClient();
    @Getter
    private String username;
    @Getter
    private RepositoryService repositoryService;
    @Getter
    private String repositoryName;
    @Getter
    @Setter
    private Repository repository;
    @Getter
    private boolean joinMessage;

    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        try {
            setupConfig();
        } catch (IOException exception) {
            return;
        }
        setupListeners();
    }

    public void onDisable() {

    }

    private void setupConfig() throws IOException {
        try {
            username = getConfig().getString("Username");
            repositoryService = new RepositoryService(gitHubClient);
            repositoryName = getConfig().getString("Repository");
            repository = repositoryService.getRepository(username, repositoryName);
            joinMessage = getConfig().getBoolean("JoinMessage");
        } catch (Exception exception) {
            getLogger().severe("Malformed configuration file! Disabling plugin.");
            setEnabled(false);
            throw new IOException();
        }
    }

    private void setupListeners() {
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
    }
}
