package net.jaredbates.BukkitIssueTracker;

import net.jaredbates.BukkitIssueTracker.Listeners.PlayerJoinListener;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitIssueTracker extends JavaPlugin {
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
    }

    public void onDisable() {

    }
}
