package me.refluxo.libary.bungee.utils.player;

import me.refluxo.libary.bungee.utils.sync.OfflinePlayerData;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Player {

    String uuid;

    public Player(String s) {
        this.uuid = s;
    }

    public Player(ProxiedPlayer player) {
        this.uuid = player.getUniqueId().toString();
    }

    public Player makeStringToUUID() {
        if(ProxyServer.getInstance().getPlayer(uuid).isConnected()) {
            uuid = ProxyServer.getInstance().getPlayer(uuid).getUniqueId().toString();
        } else {
            uuid = new OfflinePlayerData().getOfflinePlayer(uuid).getUUID();
        }
        return this;
    }

    public String getUniqueId() {
        return uuid;
    }

}
