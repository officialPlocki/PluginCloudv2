package me.refluxo.bansystem.commands;

import me.refluxo.bansystem.utils.ban.BanAPI;
import me.refluxo.core.MenBungeeCordCore;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;
import ro.menast.libary.bungee.utils.player.Player;
import ro.menast.libary.bungee.utils.player.PlayerAPI;
import ro.menast.libary.bungee.utils.project.ProjectManager;

public class BanInfoCommand extends Command {

    public BanInfoCommand(String name) {
        super(name);
    }

    public void execute(CommandSender commandSender, String[] strings) {

        BanAPI banAPI = new BanAPI();
        ProjectManager projectManager = MenBungeeCordCore.getProjectManager();
        PlayerAPI playerAPI = new PlayerAPI(new Player(commandSender.getName()).makeStringToUUID());
        if(strings.length==0) {
            if(banAPI.isBanned(new Player(commandSender.getName()).makeStringToUUID())) {

            } else {
                commandSender.sendMessage(new TextComponent(projectManager.getMessage("ban.younotbanned", playerAPI.getLanguage())));
                return;
            }
        }

    }

    /*

    baninfo banid

     */
}