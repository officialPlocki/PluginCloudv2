package me.refluxo.libary.bungee.utils.sync;

import net.md_5.bungee.api.connection.ProxiedPlayer;

public class PlayerDataMirror {

    public PlayerDataMirror() {}

    public OfflinePlayerData getOfflinePlayerData() {
        return new OfflinePlayerData();
    }

    public OfflinePlayerData getOfflinePlayerData(ProxiedPlayer executor, String errorMessage) {
        return new OfflinePlayerData(executor, errorMessage);
    }

    public OnlinePlayerData getOnlinePlayerData() {
        return new OnlinePlayerData();
    }

    public OnlinePlayerData getOnlinePlayerData(ProxiedPlayer executor, String errorMessage) {
        return new OnlinePlayerData(executor, errorMessage);
    }

    // PROXY 1          PROXY 2
    //          PAPER
    //                           PAPER

}
