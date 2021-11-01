package me.refluxo.libary.spigot.listener;

import me.refluxo.libary.spigot.utils.money.MoneyAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        MoneyAPI.getMoney(event.getPlayer());
    }

}
