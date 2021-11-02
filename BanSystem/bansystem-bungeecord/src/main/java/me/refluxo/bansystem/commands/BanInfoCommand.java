package me.refluxo.bansystem.commands;

import me.refluxo.bansystem.utils.ban.BanAPI;
import me.refluxo.core.BungeeCordCore;
import me.refluxo.libary.bungee.utils.player.Player;
import me.refluxo.libary.bungee.utils.player.PlayerAPI;
import me.refluxo.libary.bungee.utils.project.ProjectManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class BanInfoCommand extends Command {

    public BanInfoCommand(String name) {
        super(name);
    }

    public void execute(CommandSender commandSender, String[] strings) {

        BanAPI banAPI = new BanAPI();
        ProjectManager projectManager = BungeeCordCore.getProjectManager();
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
