package me.refluxo.bansystem.commands;

import me.refluxo.bansystem.utils.ban.BanAPI;
import me.refluxo.core.BungeeCordCore;
import me.refluxo.libary.bungee.utils.player.Player;
import me.refluxo.libary.bungee.utils.player.PlayerAPI;
import me.refluxo.libary.bungee.utils.project.ProjectManager;
import me.refluxo.libary.bungee.utils.sync.OfflinePlayerData;
import me.refluxo.libary.bungee.utils.sync.builder.OfflinePlayer;
import me.refluxo.libary.bungee.utils.sync.builder.OfflinePlayerBuilder;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class BanCommand extends Command {

    public BanCommand(String name) {
        super(name);
    }

    public void execute(CommandSender commandSender, String[] strings) {
        if(commandSender.hasPermission("bans.ban")) {
            BanAPI banAPI = new BanAPI();
            ProxiedPlayer proxiedPlayer = BungeeCord.getInstance().getPlayer(commandSender.getName());
            PlayerAPI player = new PlayerAPI(new Player(proxiedPlayer));
            ProjectManager projectManager = BungeeCordCore.getProjectManager();
            if(strings.length <= 1 || strings.length >= 4) {
                proxiedPlayer.sendMessage(new TextComponent(projectManager.getMessage("ban.help", player.getLanguage())));
            } else if(strings.length==2) {
                proxiedPlayer.sendMessage(new TextComponent(projectManager.getMessage("ban.searching", player.getLanguage())));
                ProxiedPlayer target = BungeeCord.getInstance().getPlayer(strings[1]);
                if(target.isConnected()) {
                    proxiedPlayer.sendMessage(new TextComponent(projectManager.getMessage("ban.found", player.getLanguage())));
                    proxiedPlayer.sendMessage(new TextComponent(projectManager.getMessage("ban.ban", player.getLanguage())));
                    banAPI.ban(projectManager.getMessage("ban.noreplayid", new PlayerAPI(new Player(target.getUniqueId().toString())).getLanguage()), new Player(target.getUniqueId().toString()), new Player(proxiedPlayer.getUniqueId().toString()), false, strings[2]);
                } else {
                    if(OfflinePlayerData.OfflinePlayerDataMySQL.getUniqueIdByName(strings[1])==null) {
                        proxiedPlayer.sendMessage(new TextComponent(projectManager.getMessage("ban.playernotfound", player.getLanguage())));
                        return;
                    }
                    OfflinePlayer offlinePlayer = new OfflinePlayerBuilder(OfflinePlayerData.OfflinePlayerDataMySQL.getUniqueIdByName(strings[1]), OfflinePlayerData.OfflinePlayerDataMySQL.getIpByName(strings[1]), strings[1]);
                    proxiedPlayer.sendMessage(new TextComponent(projectManager.getMessage("ban.found", player.getLanguage())));
                    proxiedPlayer.sendMessage(new TextComponent(projectManager.getMessage("ban.ban", player.getLanguage())));
                    banAPI.ban(projectManager.getMessage("ban.noreplayid", new PlayerAPI(new Player(offlinePlayer.getUUID())).getLanguage()), new Player(offlinePlayer.getUUID()), new Player(proxiedPlayer.getUniqueId().toString()), false, strings[2]);
                }
            } else {
                proxiedPlayer.sendMessage(new TextComponent(projectManager.getMessage("ban.searching", player.getLanguage())));
                ProxiedPlayer target = BungeeCord.getInstance().getPlayer(strings[1]);
                if(target.isConnected()) {
                    proxiedPlayer.sendMessage(new TextComponent(projectManager.getMessage("ban.found", player.getLanguage())));
                    proxiedPlayer.sendMessage(new TextComponent(projectManager.getMessage("ban.ban", player.getLanguage())));
                    banAPI.ban(strings[3], new Player(target.getUniqueId().toString()), new Player(proxiedPlayer.getUniqueId().toString()), false, strings[2]);
                } else {
                    if(OfflinePlayerData.OfflinePlayerDataMySQL.getUniqueIdByName(strings[1])==null) {
                        proxiedPlayer.sendMessage(new TextComponent(projectManager.getMessage("ban.playernotfound", player.getLanguage())));
                        return;
                    }
                    OfflinePlayer offlinePlayer = new OfflinePlayerBuilder(OfflinePlayerData.OfflinePlayerDataMySQL.getUniqueIdByName(strings[1]), OfflinePlayerData.OfflinePlayerDataMySQL.getIpByName(strings[1]), strings[1]);
                    proxiedPlayer.sendMessage(new TextComponent(projectManager.getMessage("ban.found", player.getLanguage())));
                    proxiedPlayer.sendMessage(new TextComponent(projectManager.getMessage("ban.ban", player.getLanguage())));
                    banAPI.ban(strings[3], new Player(offlinePlayer.getUUID()), new Player(proxiedPlayer.getUniqueId().toString()), false, strings[2]);
                }
            }
        } else {
            commandSender.sendMessage(new TextComponent(BungeeCordCore.getProjectManager().getMessage("general.nopermission", new PlayerAPI(new Player(BungeeCord.getInstance().getPlayer(commandSender.getName()).getUniqueId().toString())).getLanguage())));
        }
    }

    /*

    ban player reason replayid

     */

}
