package me.refluxo.auth.paper;

import me.refluxo.auth.paper.listener.JoinListener;
import me.refluxo.libary.spigot.LibarySpigot;
import me.refluxo.libary.spigot.utils.project.ProjectManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class AuthPaper extends JavaPlugin {

    private static ProjectManager projectManager;

    @Override
    public void onEnable() {
        projectManager = new ProjectManager("AuthPaper");
        Bukkit.getConsoleSender().sendMessage("Auth loaded.");
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
    }

    public static Plugin getInstance() {
        return LibarySpigot.getInstance();
    }

}
