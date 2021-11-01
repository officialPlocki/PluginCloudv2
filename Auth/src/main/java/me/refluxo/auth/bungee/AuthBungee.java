package me.refluxo.auth.bungee;

import me.refluxo.auth.bungee.util.PlayerManagerBungee;
import me.refluxo.libary.bungee.LibaryBungee;
import me.refluxo.libary.bungee.utils.mysql.MySQLService;
import me.refluxo.libary.bungee.utils.project.ProjectManager;
import net.md_5.bungee.api.plugin.Plugin;

public class AuthBungee extends Plugin {

    private static ProjectManager projectManager;

    @Override
    public void onEnable() {
        projectManager = new ProjectManager("AuthBungee");
        PlayerManagerBungee.setup();
    }

    public static Plugin getInstance() {
        return LibaryBungee.getInstance();
    }
    public static MySQLService getMySQL() {
        return LibaryBungee.getMySQL();
    }
    public static ProjectManager getProjectManager() {
        return projectManager;
    }

}
