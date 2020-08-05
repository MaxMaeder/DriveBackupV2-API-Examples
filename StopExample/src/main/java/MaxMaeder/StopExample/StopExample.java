package MaxMaeder.StopExample;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import ratismal.drivebackup.DriveBackupApi;

public class StopExample extends JavaPlugin {

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

        // Stop the server when a backup successfully completes
        DriveBackupApi.onBackupDone(new Runnable() {
            public void run() {
                
                // Tell the user the server is stopping
                getLogger().info("Backup complete, stopping server");

                Bukkit.getServer().shutdown();
            }
        });

        // Tell the user the plugin was initialized 
        getLogger().info("Plugin initialized, the server will now stop after a backup");
    }
}