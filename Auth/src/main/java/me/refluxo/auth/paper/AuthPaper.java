package me.refluxo.auth.paper;

import me.refluxo.auth.paper.util.PlayerManagerPaper;
import me.refluxo.libary.spigot.LibarySpigot;
import me.refluxo.libary.spigot.utils.mysql.MySQLService;
import me.refluxo.libary.spigot.utils.project.ProjectManager;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class AuthPaper extends JavaPlugin {

    private static ProjectManager projectManager;

    @Override
    public void onEnable() {
        projectManager = new ProjectManager("AuthPaper");
        PlayerManagerPaper.setup();
    }

    public static Plugin getInstance() {
        return LibarySpigot.getInstance();
    }
    public static MySQLService getMySQL() {
        return LibarySpigot.getMySQL();
    }
    public static ProjectManager getProjectManager() {
        return projectManager;
    }

}
