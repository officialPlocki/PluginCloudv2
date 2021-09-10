package ro.menast.core;

import net.md_5.bungee.api.plugin.Plugin;
import ro.menast.libary.bungee.utils.mysql.MySQLService;
import ro.menast.libary.bungee.utils.project.ProjectManager;

public final class MenBungeeCordCore extends Plugin {

    private static ProjectManager projectManager;
    private static MySQLService mySQLService;
    private static Plugin instance;

    public void onEnable() {
        instance = this;
        projectManager = new ProjectManager("MenBungeeCordCore");
        mySQLService = new MySQLService();
    }

    public void onDisable() {}

    public static Plugin getInstance() {return instance;}

    public static MySQLService getMySQL() {return mySQLService;}

    public static ProjectManager getProjectManager() {return projectManager;}

}
