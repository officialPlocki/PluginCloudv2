package ro.menast.core.Commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GMCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (Bukkit.getPlayer(sender.getName()).isOnline()) {
            Player p = Bukkit.getPlayer(sender.getName());
            if (p.hasPermission("Menastro.Gamemode")) {
                if (args.length == 1) {
                    if (args[0] == "0") {
                        if (!p.getGameMode().equals(GameMode.SURVIVAL)) {
                            p.setGameMode(GameMode.SURVIVAL);
                            p.sendMessage("§7Your Gamemode has been changed to §bSURVIVAL");
                            return true;
                        } else {
                            p.sendMessage("§cYour gamemode already is SURVIVAL");
                            return false;
                        }
                    } else if (args[0] == "1") {
                        if (!p.getGameMode().equals(GameMode.CREATIVE)) {
                            p.setGameMode(GameMode.CREATIVE);
                            p.sendMessage("§7Your Gamemode has been changed to §bCREATIVE");
                            return true;
                        } else {
                            p.sendMessage("§cYour gamemode already is CREATIVE");
                            return false;
                        }
                    } else if (args[0] == "2") {
                        if (!p.getGameMode().equals(GameMode.ADVENTURE)) {
                            p.setGameMode(GameMode.ADVENTURE);
                            p.sendMessage("§7Your Gamemode has been changed to §bADVENTURE");
                            return true;
                        } else {
                            p.sendMessage("§cYour gamemode already is ADVENTURE");
                            return false;
                        }
                    } else if (args[0] == "3") {
                        if (!p.getGameMode().equals(GameMode.SPECTATOR)) {
                            p.setGameMode(GameMode.SPECTATOR);
                            p.sendMessage("§7Your Gamemode has been changed to §bSPECTATOR");
                            return true;
                        } else {
                            p.sendMessage("§cYour gamemode already is SPECTATOR");
                            return false;
                        }
                    } else {
                        p.sendMessage("§c/gm <0/1/2/3>");
                        return false;
                    }
                } else if (args.length == 2) {
                    String realname = "SADijhiasdaopkoa";
                    for(Player all : Bukkit.getOnlinePlayers()) {
                        if(all.getName().equalsIgnoreCase(args[1])) {
                            realname = all.getName();
                        }
                    }
                    if (Bukkit.getPlayer(realname).isOnline()) {
                        Player target = Bukkit.getPlayer(args[1]);
                        if (args[0] == "0") {
                            if (!target.getGameMode().equals(GameMode.SURVIVAL)) {
                                target.setGameMode(GameMode.SURVIVAL);
                                target.sendMessage("§7Changed §b" + target.getName() + "'s §7Gamemode to §bSURVIVAL");
                                return true;
                            } else {
                                target.sendMessage("§c" + target.getName() + "'s gamemode already is SURVIVAL");
                                return false;
                            }
                        } else if (args[0] == "1") {
                            if (!p.getGameMode().equals(GameMode.CREATIVE)) {
                                target.setGameMode(GameMode.CREATIVE);
                                target.sendMessage("§7Changed §b" + target.getName() + "'s §7Gamemode to §bCREATIVE");
                                return true;
                            } else {
                                target.sendMessage("§c" + target.getName() + "'s gamemode already is CREATIVE");
                                return false;
                            }
                        } else if (args[0] == "2") {
                            if (!p.getGameMode().equals(GameMode.ADVENTURE)) {
                                target.setGameMode(GameMode.ADVENTURE);
                                target.sendMessage("§7Changed §b" + target.getName() + "'s §7Gamemode to §bADVENTURE");
                                return true;
                            } else {
                                target.sendMessage("§c" + target.getName() + "'s gamemode already is ADVENTURE");
                                return false;
                            }
                        } else if (args[0] == "3") {
                            if (!p.getGameMode().equals(GameMode.SPECTATOR)) {
                                target.setGameMode(GameMode.SPECTATOR);
                                target.sendMessage("§7Changed §b" + target.getName() + "'s §7Gamemode to §bSPECTATOR");
                                return true;
                            } else {
                                target.sendMessage("§c" + target.getName() + "'s gamemode already is SPECTATOR");
                                return false;
                            }
                        } else {
                            p.sendMessage("§c/gm <0/1/2/3>");
                            return false;
                        }
                    }
                } else {
                    p.sendMessage("§c/gm <0/1/2/3>");
                    return false;
                }
            }
        }
        return false;
    }
}
