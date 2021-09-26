package ro.menast.core;

import org.bukkit.plugin.java.JavaPlugin;
import ro.menast.libary.spigot.utils.mysql.MySQLService;
import ro.menast.libary.spigot.utils.project.ProjectManager;

public final class                    MenSpigotCore extends JavaPlugin {

    private static ProjectManager projectManager;

    private static MySQLService mySQLService;

    @Override
    public void onEnable() {
        mySQLService = new MySQLService();
        projectManager = new ProjectManager("SpigotCore");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static MySQLService getMySQL() {
        return mySQLService;
    }

    public static ProjectManager getProjectManager() {
        return projectManager;
    }

}
