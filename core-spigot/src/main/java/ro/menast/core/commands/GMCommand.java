package ro.menast.core.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GMCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        assert p != null;
        if (p.hasPermission("Menastro.Gamemode")) {
            if (args.length == 1) {
                if (args[0] == "0") {
                    if (!p.getGameMode().equals(GameMode.SURVIVAL)) {
                        p.setGameMode(GameMode.SURVIVAL);
                        p.sendMessage("§7Your Gamemode has been changed to §bSURVIVAL");
                    } else {
                        p.sendMessage("§cYour gamemode already is SURVIVAL");
                    }
                } else if (args[0] == "1") {
                    if (!p.getGameMode().equals(GameMode.CREATIVE)) {
                        p.setGameMode(GameMode.CREATIVE);
                        p.sendMessage("§7Your Gamemode has been changed to §bCREATIVE");
                    } else {
                        p.sendMessage("§cYour gamemode already is CREATIVE");
                    }
                } else if (args[0] == "2") {
                    if (!p.getGameMode().equals(GameMode.ADVENTURE)) {
                        p.setGameMode(GameMode.ADVENTURE);
                        p.sendMessage("§7Your Gamemode has been changed to §bADVENTURE");
                    } else {
                        p.sendMessage("§cYour gamemode already is ADVENTURE");
                    }
                } else if (args[0] == "3") {
                    if (!p.getGameMode().equals(GameMode.SPECTATOR)) {
                        p.setGameMode(GameMode.SPECTATOR);
                        p.sendMessage("§7Your Gamemode has been changed to §bSPECTATOR");
                    } else {
                        p.sendMessage("§cYour gamemode already is SPECTATOR");
                    }
                } else {
                    p.sendMessage("§c/gm <0/1/2/3>");
                    return false;
                }
                return true;
            } else {
                p.sendMessage("§c/gm <0/1/2/3>");
                return false;
            }
        }
        return false;
    }
}
