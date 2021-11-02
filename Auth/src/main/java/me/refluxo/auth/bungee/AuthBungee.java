package me.refluxo.auth.bungee;

import me.refluxo.auth.bungee.listener.JoinListener;
import me.refluxo.auth.bungee.listener.QuitListener;
import me.refluxo.auth.bungee.util.PlayerManagerBungee;
import me.refluxo.libary.bungee.LibaryBungee;
import me.refluxo.libary.bungee.utils.mysql.MySQLService;
import me.refluxo.libary.bungee.utils.project.ProjectManager;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class AuthBungee extends Plugin {

    private static ProjectManager projectManager;

    @Override
    public void onEnable() {
        projectManager = new ProjectManager("AuthBungee");
        PlayerManagerBungee.setup();
        new PluginManager(ProxyServer.getInstance()).registerListener(this, new JoinListener());
        new PluginManager(ProxyServer.getInstance()).registerListener(this, new QuitListener());
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
