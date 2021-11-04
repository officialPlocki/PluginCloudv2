package me.refluxo.core.paper;

import me.refluxo.core.paper.commands.VanishCommand;
import me.refluxo.libary.spigot.LibarySpigot;
import me.refluxo.libary.spigot.utils.project.ProjectManager;
import me.refluxo.libary.threaded.AsyncThreadScheduler;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class CorePaper extends JavaPlugin {

    private static ProjectManager projectManager;

    @Override
    public void onEnable() {
        projectManager = new ProjectManager("CorePaper");
        projectManager.addPermission("core.vanish");
        projectManager.addPermission("core.gamemode");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static ProjectManager getProjectManager() {
        return projectManager;
    }

    private void autoVanish() {
        new AsyncThreadScheduler(() -> {
            List<CommandSender> list = new ArrayList<>(VanishCommand.vanish.keySet());
            list.forEach(commandSender -> {
                if(VanishCommand.vanish.getOrDefault(commandSender, false)) {
                    Bukkit.getOnlinePlayers().forEach(player -> {
                        if(!player.hasPermission("core.vanish.bypass")) {
                            player.hidePlayer(LibarySpigot.getInstance(), Objects.requireNonNull(Bukkit.getPlayer(commandSender.getName())));
                        }
                    });
                }
            });
        }).scheduleAsyncTask(1L, 1L);
    }

}
