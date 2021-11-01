package me.refluxo.auth.paper.listener;

import me.refluxo.auth.paper.util.PlayerManagerPaper;
import me.refluxo.libary.spigot.utils.player.Player;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = new Player(event.getPlayer().getUniqueId().toString());
        PlayerManagerPaper managerBungee = new PlayerManagerPaper(player);
        if(managerBungee.isOnline()) {
            Bukkit.getConsoleSender().sendMessage("[AUTHENTICATION] Connection for " + Bukkit.getPlayer(player.getUniqueId()).getName() + " was established and secured.");
        } else {
            event.getPlayer().kickPlayer("Authentication failed. Your connection can't be secured.\n\nAuth powered by Refluxo");
            Bukkit.getConsoleSender().sendMessage("[AUTHENTICATION] The connection from " + player.getUniqueId() + " can't be secured.");
        }
    }

}
