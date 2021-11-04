package me.refluxo.core.paper.commands;

import me.refluxo.core.paper.CorePaper;
import me.refluxo.libary.spigot.utils.player.Player;
import me.refluxo.libary.spigot.utils.player.PlayerAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Objects;

public class VanishCommand implements CommandExecutor {

    public static HashMap<CommandSender, Boolean> vanish = new HashMap<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        PlayerAPI playerAPI = new PlayerAPI(new Player(Objects.requireNonNull(sender.getServer().getPlayer(sender.getName())).getUniqueId().toString()));
        if(sender.hasPermission("core.vanish")) {
            if(!vanish.getOrDefault(sender, false)) {
                vanish.put(sender, true);
                sender.sendMessage(CorePaper.getProjectManager().getMessage("core.command.vanish.vanished", playerAPI.getLanguage()));
            } else {
                vanish.put(sender, false);
                sender.sendMessage(CorePaper.getProjectManager().getMessage("core.command.vanish.unvanished", playerAPI.getLanguage()));
            }
        } else {
            sender.sendMessage(CorePaper.getProjectManager().getMessage("nopermission", playerAPI.getLanguage()));
        }
        return false;
    }
}
