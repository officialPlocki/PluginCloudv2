package me.refluxo.core;

import me.refluxo.libary.bungee.utils.mysql.MySQLService;
import me.refluxo.libary.bungee.utils.project.ProjectManager;
import net.md_5.bungee.api.plugin.Plugin;

public class MenBungeeCordCore extends Plugin {

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
