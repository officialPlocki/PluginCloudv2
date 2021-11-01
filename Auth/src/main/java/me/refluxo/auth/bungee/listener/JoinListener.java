package me.refluxo.auth.bungee.listener;

import me.refluxo.auth.bungee.util.PlayerManagerBungee;
import me.refluxo.libary.bungee.utils.player.Player;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PostLoginEvent event) {
        Player player = new Player(event.getPlayer());
        PlayerManagerBungee managerBungee = new PlayerManagerBungee(player);
        managerBungee.checkContainsPlayer();
        managerBungee.setOnline(true);
    }

}
