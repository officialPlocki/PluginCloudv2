package ro.menast.libary.spigot.utils.sync;

import org.bukkit.entity.Player;

public class PlayerDataMirror {

    public PlayerDataMirror() {}

    public OfflinePlayerData getOfflinePlayerData(Player executor, String errorMessage) {
        return new OfflinePlayerData(executor, errorMessage);
    }

    public OfflinePlayerData getOfflinePlayerData() {
        return new OfflinePlayerData();
    }

    public OnlinePlayerData getOnlinePlayerData() {
        return new OnlinePlayerData();
    }

    public OnlinePlayerData getOnlinePlayerData(Player executor, String errorMessage) {
        return new OnlinePlayerData(executor, errorMessage);
    }

}
