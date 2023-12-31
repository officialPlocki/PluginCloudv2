package me.refluxo.bansystem.utils.messages;

import me.refluxo.bansystem.utils.ban.BanAPI;
import me.refluxo.bansystem.utils.ban.IBan;
import me.refluxo.core.BungeeCordCore;
import me.refluxo.libary.bungee.utils.language.LanguageAPI;
import me.refluxo.libary.bungee.utils.project.ProjectManager;
import net.md_5.bungee.BungeeCord;

public class BanInfoMessageBuilder {

    public static String getBanInfoString(String banID, LanguageAPI.langs lang) {
        String s = "";
        BanAPI banAPI = new BanAPI();
        IBan iBan = banAPI.getBanInfo(banID);
        ProjectManager projectManager = BungeeCordCore.getProjectManager();

        String first = projectManager.getMessage("ban.bannedat", lang).replaceAll("%date%", iBan.bannedAt().toGMTString()+" GMT");
        String second = projectManager.getMessage("ban.bannedto", lang).replaceAll("%date%", iBan.unbanAt().toGMTString()+" GMT");
        String three;
        if(BungeeCord.getInstance().getPlayer(iBan.bannedFrom().getUniqueId()).isConnected()) {
            three = projectManager.getMessage("ban.bannedfrom", lang).replaceAll("%from%", BungeeCord.getInstance().getPlayer(iBan.bannedFrom().getUniqueId()).getName());
        } else {
            //OfflinePlayer from = new OfflinePlayerBuilder(OfflinePlayerData.OfflinePlayerDataMySQL.getUniqueIdByName(OfflinePlayerData.OfflinePlayerDataMySQL.getNameByUniqueID(iBan.bannedFrom().getUniqueId())));
        }
        return s;
    }

}
