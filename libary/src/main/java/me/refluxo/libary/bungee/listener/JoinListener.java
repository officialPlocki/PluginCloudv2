package me.refluxo.libary.bungee.listener;

import me.refluxo.libary.bungee.utils.money.CoinsAPI;
import me.refluxo.libary.bungee.utils.player.Player;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PostLoginEvent event) {
        CoinsAPI.validateJoin(new Player(event.getPlayer()));
    }

}
