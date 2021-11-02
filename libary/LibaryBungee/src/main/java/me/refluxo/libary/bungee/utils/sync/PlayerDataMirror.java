package me.refluxo.libary.bungee.utils.sync;

public class PlayerDataMirror {

    public PlayerDataMirror() {}

    public OfflinePlayerData getOfflinePlayerData() {
        return new OfflinePlayerData();
    }

    public OnlinePlayerData getOnlinePlayerData() {
        return new OnlinePlayerData();
    }

}
