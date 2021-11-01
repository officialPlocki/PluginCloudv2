package me.refluxo.core;

import me.refluxo.libary.spigot.LibarySpigot;
import me.refluxo.libary.spigot.utils.mysql.MySQLService;
import me.refluxo.libary.spigot.utils.project.ProjectManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class MenSpigotCore extends JavaPlugin {

    private static ProjectManager projectManager;

    private static MySQLService mySQLService;

    @Override
    public void onEnable() {
        mySQLService = LibarySpigot.getMySQL();
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
