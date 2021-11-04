package me.refluxo.core.paper.commands;

import me.refluxo.core.paper.CorePaper;
import me.refluxo.libary.spigot.utils.player.Player;
import me.refluxo.libary.spigot.utils.player.PlayerAPI;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class GamemodeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        PlayerAPI playerAPI = new PlayerAPI(new Player(Objects.requireNonNull(sender.getServer().getPlayer(sender.getName())).getUniqueId().toString()));
        if(sender.hasPermission("core.gamemode")) {
            if(args.length != 1) {
                sender.sendMessage(CorePaper.getProjectManager().getMessage("core.command.gamemode.help", playerAPI.getLanguage()));
            } else {
                if(args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")) {
                    sender.sendMessage(CorePaper.getProjectManager().getMessage("core.command.gamemode.survival", playerAPI.getLanguage()));
                    Objects.requireNonNull(Bukkit.getPlayer(sender.getName())).setGameMode(GameMode.SURVIVAL);
                } else if(args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")) {
                    sender.sendMessage(CorePaper.getProjectManager().getMessage("core.command.gamemode.creative", playerAPI.getLanguage()));
                    Objects.requireNonNull(Bukkit.getPlayer(sender.getName())).setGameMode(GameMode.CREATIVE);
                } else if(args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")) {
                    sender.sendMessage(CorePaper.getProjectManager().getMessage("core.command.gamemode.adventure", playerAPI.getLanguage()));
                    Objects.requireNonNull(Bukkit.getPlayer(sender.getName())).setGameMode(GameMode.ADVENTURE);
                } else if(args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")) {
                    sender.sendMessage(CorePaper.getProjectManager().getMessage("core.command.gamemode.spectator", playerAPI.getLanguage()));
                    Objects.requireNonNull(Bukkit.getPlayer(sender.getName())).setGameMode(GameMode.SPECTATOR);
                } else {
                    sender.sendMessage(CorePaper.getProjectManager().getMessage("core.command.gamemode.help", playerAPI.getLanguage()));
                }
            }
        } else {
            sender.sendMessage(CorePaper.getProjectManager().getMessage("nopermission", playerAPI.getLanguage()));
        }
        return false;
    }
}
