package me.refluxo.auth.bungee.listener;

import me.refluxo.auth.bungee.util.PlayerManagerBungee;
import me.refluxo.libary.bungee.utils.player.Player;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class QuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerDisconnectEvent event) {
        new PlayerManagerBungee(new Player(event.getPlayer())).setOnline(false);
    }

}
