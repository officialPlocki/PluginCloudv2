package ro.menast.astroperms.commands;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import ro.menast.astroperms.api.permissions.AstropermsGroupAPI;
import ro.menast.astroperms.api.permissions.AstropermsPlayerAPI;
import ro.menast.core.MenBungeeCordCore;
import ro.menast.libary.bungee.utils.language.LanguageAPI;
import ro.menast.libary.bungee.utils.player.Player;
import ro.menast.libary.bungee.utils.player.PlayerAPI;
import ro.menast.libary.bungee.utils.project.ProjectManager;
import ro.menast.libary.bungee.utils.sync.OfflinePlayerData;
import ro.menast.libary.bungee.utils.sync.builder.OfflinePlayer;

public class AstropermsCommand extends Command {

    public AstropermsCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        ProjectManager projectManager = MenBungeeCordCore.getProjectManager();
        PlayerAPI playerAPI = new PlayerAPI(new Player(((ProxiedPlayer) commandSender).getUniqueId().toString()));
        AstropermsPlayerAPI astroPlayer = new AstropermsPlayerAPI(new Player(((ProxiedPlayer) commandSender).getUniqueId().toString()));
        AstropermsGroupAPI astroGroup = new AstropermsGroupAPI(astroPlayer.getGroupOfPlayer());
        //projekt permission hinzufügen bzw. auflisten (nur in main!): projectManager.addPermission("");
        //message getter: projectManager.getMessage("Hi %target%", playerAPI.getLanguage());
        //message placeholder replace: projectManager.getMessage("Hi %target%", playerAPI.getLanguage()).replaceAll("", "");
        //message setzen: projectManager.setMessage("", LanguageAPI.langs.DE, "");
        projectManager.setMessage("perms.user.notfound", LanguageAPI.langs.EN, "§cPlayer %target% not found!");
        projectManager.setMessage("perms.user.notfound", LanguageAPI.langs.DE, "§cSpieler %target% nicht gefunden!");

        playerAPI.getLanguage();
        if (playerAPI.hasPermission("menastro.admin.perms")) {
            if (strings.length == 3) {
                if (strings[0].equalsIgnoreCase("user")) {
                    ProxiedPlayer target = null;
                    for (ProxiedPlayer all : BungeeCord.getInstance().getPlayers()) {
                        if (all.getName().equalsIgnoreCase(strings[1])) {
                            target = all;
                        }
                    }
                    if (target != null) {
                        if (strings[2].equalsIgnoreCase("info")) {
                            PlayerAPI tapi = new PlayerAPI(new Player(target.getUniqueId().toString()));
                            AstropermsPlayerAPI papi = new AstropermsPlayerAPI(new Player(target.getUniqueId().toString()));

                            AstropermsGroupAPI gapi = new AstropermsGroupAPI(papi.getGroupOfPlayer());
                            projectManager.setMessage("perms.user.info", LanguageAPI.langs.EN, "\n---- §bPlayerinfo of %target%:\n§Rank: ----" + astroPlayer.getGroupOfPlayer() + "\n \n§lPermissionlist:\n" + target.getPermissions() + "\n \n   ---------------------   ");
                            projectManager.setMessage("perms.user.info", LanguageAPI.langs.DE, "\n---- §bSpeierinfo für %target%:\n§Rang: ----" + astroPlayer.getGroupOfPlayer() + "\n \n§lPermissionliste:\n" + target.getPermissions() + "\n \n   ---------------------   ");
                            String msg = projectManager.getMessage("perms.user.info", playerAPI.getLanguage()).replaceAll("%target%", target.getName());
                        }
                    } else {
                        OfflinePlayerData offlinePlayerData = new OfflinePlayerData((ProxiedPlayer) commandSender, projectManager.getMessage("perms.user.notfound", playerAPI.getLanguage()).replaceAll("%target%", strings[1]));
                        OfflinePlayer offlinePlayer = offlinePlayerData.getOfflinePlayer(strings[1]);
                        AstropermsPlayerAPI targetPerm = new AstropermsPlayerAPI(new Player(offlinePlayer.getUUID()));
                        PlayerAPI tapi = new PlayerAPI(new Player(offlinePlayer.getUUID()));
                    }
                }
                if (strings[0].equalsIgnoreCase("user")) {
                    ProxiedPlayer target = null;
                    for (ProxiedPlayer all : BungeeCord.getInstance().getPlayers()) {
                        if (all.getName().equalsIgnoreCase(strings[1])) {
                            target = all;
                        }
                    }
                    if (target != null) {
                        if (strings[2].equalsIgnoreCase("info")) {
                            PlayerAPI tapi = new PlayerAPI(new Player(target.getUniqueId().toString()));
                            AstropermsPlayerAPI papi = new AstropermsPlayerAPI(new Player(target.getUniqueId().toString()));

                            AstropermsGroupAPI gapi = new AstropermsGroupAPI(papi.getGroupOfPlayer());
                            projectManager.setMessage("perms.user.info", LanguageAPI.langs.EN, "\n---- §bPlayerinfo of %target%:\n§Rank: ----" + astroPlayer.getGroupOfPlayer() + "\n \n§lPermissionlist:\n" + target.getPermissions() + "\n \n   ---------------------   ");
                            projectManager.setMessage("perms.user.info", LanguageAPI.langs.DE, "\n---- §bSpeierinfo für %target%:\n§Rang: ----" + astroPlayer.getGroupOfPlayer() + "\n \n§lPermissionliste:\n" + target.getPermissions() + "\n \n   ---------------------   ");
                            String msg = projectManager.getMessage("perms.user.info", playerAPI.getLanguage()).replaceAll("%target%", target.getName());
                        }
                    }
                } else if (strings[0].equalsIgnoreCase("group")) {
                    ProxiedPlayer target = null;
                    for (ProxiedPlayer all : BungeeCord.getInstance().getPlayers()) {
                        if (all.getName().equalsIgnoreCase(strings[1])) {
                            target = all;
                        }
                    }
                    if (target != null) {
                        if (strings[2].equalsIgnoreCase("info")) {
                            PlayerAPI tapi = new PlayerAPI(new Player(target.getUniqueId().toString()));
                            AstropermsPlayerAPI papi = new AstropermsPlayerAPI(new Player(target.getUniqueId().toString()));

                            AstropermsGroupAPI gapi = new AstropermsGroupAPI(papi.getGroupOfPlayer());
                            projectManager.setMessage("perms.group.info", LanguageAPI.langs.EN, "\n---- §bGroupinfo of %target%:\n§Rank: ----" + astroPlayer.getGroupOfPlayer() + "\n \n§lPermissionlist:\n" + target.getPermissions() + "\n \n   ---------------------   ");
                            projectManager.setMessage("perms.group.info", LanguageAPI.langs.DE, "\n---- §bGruppeninfo für %target%:\n§lPermissionliste:\n" + target.getPermissions() + "\n \n   ---------------------   ");
                            String msg = projectManager.getMessage("perms.user.info", playerAPI.getLanguage()).replaceAll("%target%", target.getName());
                        }
                    } else {
                        OfflinePlayerData offlinePlayerData = new OfflinePlayerData((ProxiedPlayer) commandSender, projectManager.getMessage("perms.group.notfound", playerAPI.getLanguage()).replaceAll("%target%", strings[1]));
                        OfflinePlayer offlinePlayer = offlinePlayerData.getOfflinePlayer(strings[1]);
                        AstropermsPlayerAPI targetPerm = new AstropermsPlayerAPI(new Player(offlinePlayer.getUUID()));
                        PlayerAPI tapi = new PlayerAPI(new Player(offlinePlayer.getUUID()));
                    }
                }
            }
        }

    /*
Subcommands:
    aperms listgroups
    aperms help


    aperms user ... info  3 *
    aperms user ... permission add ... 4
    aperms user ... permission remove ... 4
    aperms user ... group set ... 4


    aperms group ... delete 3
    aperms group ... create 3
    aperms group ... info 3 *
    aperms group ... listmembers 3
    aperms group ... permission info 3
    aperms group ... permission add ... 4
    aperms group ... permission remove ... 4

--------------------------------------------

Variablen:



    §7 -> normaler Text
    §b -> Überschriften

    §8 ->
    §3

     */

    }
}


/* aperms user (user) arg */