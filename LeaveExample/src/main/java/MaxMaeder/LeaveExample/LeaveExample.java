package MaxMaeder.LeaveExample;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import ratismal.drivebackup.DriveBackupApi;

public class LeaveExample extends JavaPlugin implements Listener {

    /**
     * What to do when the plugin is enabled
     */
    public void onEnable() {

        // Verify the DriveBackupV2 Plugin is installed
        if (Bukkit.getServer().getPluginManager().getPlugin("DriveBackupV2") == null) {

            // Tell the user to install DriveBackupV2
            getLogger().warning("DriveBackupV2 not found, please install it to use this API example");

            // Disable this plugin
            Bukkit.getPluginManager().disablePlugin(this);
            
            return;
        }

        // Register PlayerQuitEvent event listener
        getServer().getPluginManager().registerEvents(this, this);

        // Tell the user the plugin was initialized 
        getLogger().info("Plugin initialized, a backup will now be run every time a player leaves the server");
    }

    /**
     * Run a backup whenever a player leaves the server
     */
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {

        // Tell the user a backup is starting
        getLogger().info("A player left the server, starting a backup");

        DriveBackupApi.startBackup();
    }
}