package net.jaredbates.BukkitIssueTracker;

import lombok.Getter;
import lombok.Setter;
import net.jaredbates.BukkitIssueTracker.Commands.IssuesCommand;
import net.jaredbates.BukkitIssueTracker.Listeners.InventoryClickListener;
import net.jaredbates.BukkitIssueTracker.Listeners.PlayerJoinListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.IssueService;
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
    private String password;
    @Getter
    private RepositoryService repositoryService;
    @Getter
    private String repositoryName;
    @Getter
    @Setter
    private Repository repository;
    @Getter
    private IssueService issueService;
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
        setupCommands();
    }

    public void onDisable() {

    }

    private void setupConfig() throws IOException {
        try {
            username = getConfig().getString("Username");
            password = getConfig().getString("Password");
            gitHubClient.setCredentials(username, password);
            gitHubClient.setUserAgent("BukkitIssueTracker");
            repositoryService = new RepositoryService(gitHubClient);
            repositoryName = getConfig().getString("Repository");
            repository = repositoryService.getRepository(username, repositoryName);
            issueService = new IssueService(gitHubClient);
            joinMessage = getConfig().getBoolean("JoinMessage");
        } catch (Exception e) {
            getLogger().severe("Malformed configuration file! Disabling plugin.");
            setEnabled(false);
            throw new IOException();
        }
    }

    private void setupListeners() {
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(this), this);
    }

    private void setupCommands() {
        getCommand("issues").setExecutor(new IssuesCommand(this));
    }
}
