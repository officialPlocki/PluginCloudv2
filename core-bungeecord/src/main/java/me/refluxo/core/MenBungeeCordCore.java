package me.refluxo.core;

import net.md_5.bungee.api.plugin.Plugin;
import ro.menast.libary.bungee.utils.mysql.MySQLService;
import ro.menast.libary.bungee.utils.project.ProjectManager;

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
