package me.refluxo.libary.spigot.utils.player;

import me.refluxo.libary.spigot.utils.sync.OfflinePlayerData;
import org.bukkit.Bukkit;

import java.util.Objects;

public class Player {

    String uuid;

    public Player(String uuid) {
        this.uuid = uuid;
    }

    public Player makeStringToUUID() {
        if(Objects.requireNonNull(Bukkit.getPlayer(uuid)).isOnline()) {
            uuid = Objects.requireNonNull(Bukkit.getPlayer(uuid)).getUniqueId().toString();
        } else {
            uuid = new OfflinePlayerData().getOfflinePlayer(uuid).getUUID();
        }
        return this;
    }

    public String getUniqueId() {
        return uuid;
    }

}
