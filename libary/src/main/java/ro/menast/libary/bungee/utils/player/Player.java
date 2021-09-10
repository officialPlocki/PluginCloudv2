package ro.menast.libary.bungee.utils.player;

import net.md_5.bungee.BungeeCord;
import ro.menast.libary.bungee.utils.sync.OfflinePlayerData;

public class Player {

    String uuid;

    public Player(String s) {
        this.uuid = s;
    }

    public Player makeStringToUUID() {
        if(BungeeCord.getInstance().getPlayer(uuid).isConnected()) {
            uuid = BungeeCord.getInstance().getPlayer(uuid).getUniqueId().toString();
        } else {
            uuid = new OfflinePlayerData().getOfflinePlayer(uuid).getUUID();
        }
        return this;
    }

    public String getUniqueId() {
        return uuid;
    }

}
