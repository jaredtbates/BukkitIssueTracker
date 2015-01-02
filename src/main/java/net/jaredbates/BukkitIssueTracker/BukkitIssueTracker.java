package net.jaredbates.BukkitIssueTracker;

import lombok.Getter;
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
    private boolean gitHubOAuth = false;
    @Getter
    private String gitHubUsername;
    @Getter
    private String gitHubPassword;
    @Getter
    private String oAuthToken;
    @Getter
    private Repository repository;
    @Getter
    private RepositoryService repositoryService;
    @Getter
    private boolean joinMessage;

    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        try {
            setupConfig();
        } catch (IOException e) {
            return;
        }
        setupListeners();
    }

    public void onDisable() {

    }

    private void setupConfig() throws IOException {
        try {
            gitHubOAuth = getConfig().getBoolean("OAuth.Enabled");
            if (isGitHubOAuth()) {
                oAuthToken = getConfig().getString("OAuth.Token");
                gitHubClient.setOAuth2Token(oAuthToken);
            } else {
                gitHubUsername = getConfig().getString("GitHub.Credentials.Username");
                gitHubPassword = getConfig().getString("GitHub.Credentials.Password");
                gitHubClient.setCredentials(gitHubUsername, gitHubPassword);
            }
            repositoryService = new RepositoryService(gitHubClient);
            repository = repositoryService.getRepository(gitHubUsername, getConfig().getString("Repository"));
            joinMessage = getConfig().getBoolean("JoinMessage");
        } catch (IOException exception) {
            getLogger().severe("Malformed configuration file! Disabling plugin.");
            setEnabled(false);
            throw new IOException();
        }
    }

    private void setupListeners() {
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
    }
}
