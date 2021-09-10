package ro.menast.core.commands;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import ro.menast.core.MenBungeeCordCore;
import ro.menast.libary.bungee.utils.language.LanguageAPI;
import ro.menast.libary.bungee.utils.permissions.AstropermsGroupAPI;
import ro.menast.libary.bungee.utils.permissions.AstropermsPlayerAPI;
import ro.menast.libary.bungee.utils.permissions.PermissionableGroupBuilder;
import ro.menast.libary.bungee.utils.player.Player;
import ro.menast.libary.bungee.utils.player.PlayerAPI;
import ro.menast.libary.bungee.utils.project.ProjectManager;
import ro.menast.libary.bungee.utils.sync.builder.OfflinePlayer;
import ro.menast.libary.bungee.utils.sync.OfflinePlayerData;

import java.util.List;

public class AstropermsCommand extends Command {

    public AstropermsCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        ProjectManager projectManager = MenBungeeCordCore.getProjectManager();
        PlayerAPI playerAPI = new PlayerAPI(new Player(((ProxiedPlayer)commandSender).getUniqueId().toString()));
        AstropermsPlayerAPI astroPlayer = new AstropermsPlayerAPI(new Player(((ProxiedPlayer) commandSender).getUniqueId().toString()));
        AstropermsGroupAPI astroGroup = new AstropermsGroupAPI(new PermissionableGroupBuilder(""));
        //projekt permission hinzufügen bzw. auflisten (nur in main!): projectManager.addPermission("");
        //message getter: projectManager.getMessage("Hi %target%", playerAPI.getLanguage());
        //message placeholder replace: projectManager.getMessage("Hi %target%", playerAPI.getLanguage()).replaceAll("", "");
        //message setzen: projectManager.setMessage("", LanguageAPI.langs.DE, "");
        projectManager.setMessage("perms.user.notfound", LanguageAPI.langs.EN, "§cPlayer %target% not found!");
        projectManager.setMessage("perms.user.notfound", LanguageAPI.langs.EN, "§cSpieler %target% nicht gefunden!");

        if(playerAPI.hasPermission("menastro.admin.perms")) {
            if(strings.length == 3) {
                if(strings[0].equalsIgnoreCase("user")) {
                    ProxiedPlayer target = null;
                    for(ProxiedPlayer all : BungeeCord.getInstance().getPlayers()) {
                        if(all.getName().equalsIgnoreCase(strings[1])) {
                            target = all;
                        }
                    }
                    if(target != null) {

                    } else {
                        OfflinePlayerData offlinePlayerData = new OfflinePlayerData((ProxiedPlayer) commandSender, projectManager.getMessage("", playerAPI.getLanguage()));
                        OfflinePlayer offlinePlayer = offlinePlayerData.getOfflinePlayer(strings[1]);
                        AstropermsPlayerAPI targetPerm = new AstropermsPlayerAPI(new Player(offlinePlayer.getUUID()));

                    }
                }
            }
        }

    }

    /*

    aperms listgroups
    aperms help
    aperms user ... info
    aperms group ... delete
    aperms group ... create
    aperms group ... info
    aperms group ... listmembers
    aperms uerr ... permission info
    aperms group ... permission info
    aperms user ... group info
    aperms user ... permission add ...
    aperms group ... permission add ...
    aperms user ... permission remove ...
    aperms group ... permission remove ...
    aperms user ... group set ...

     */

}
