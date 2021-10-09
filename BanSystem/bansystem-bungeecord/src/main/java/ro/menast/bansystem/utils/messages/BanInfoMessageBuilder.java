package ro.menast.bansystem.utils.messages;

import net.md_5.bungee.BungeeCord;
import ro.menast.bansystem.utils.ban.BanAPI;
import ro.menast.bansystem.utils.ban.IBan;
import ro.menast.core.MenBungeeCordCore;
import ro.menast.libary.bungee.utils.language.LanguageAPI;
import ro.menast.libary.bungee.utils.project.ProjectManager;
import ro.menast.libary.bungee.utils.sync.OfflinePlayerData;
import ro.menast.libary.bungee.utils.sync.builder.OfflinePlayer;
import ro.menast.libary.bungee.utils.sync.builder.OfflinePlayerBuilder;

public class BanInfoMessageBuilder {

    public static String getBanInfoString(String banID, LanguageAPI.langs lang) {
        String s = "";
        BanAPI banAPI = new BanAPI();
        IBan iBan = banAPI.getBanInfo(banID);
        ProjectManager projectManager = MenBungeeCordCore.getProjectManager();

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
