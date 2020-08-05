package MaxMaeder.TimeExample;

import java.util.concurrent.Callable;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import ratismal.drivebackup.DriveBackupApi;

public class TimeExample extends JavaPlugin {

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

        // If the time is after 1200 (night), cancel any backup attempts
        DriveBackupApi.beforeBackupStart(new Callable<Boolean>() {
            public Boolean call() throws Exception {
                if (Bukkit.getWorlds().get(0).getTime() > 1200) {

                    // Tell user the backup was canceled due to the current time
                    getLogger().info("Backup canceled as it is night in the main world");

                    return false;
                }

                return true;
            }
        });

        // Tell the user the plugin was initialized 
        getLogger().info("Plugin initialized, any backups attempts will be canceled");
    }
}