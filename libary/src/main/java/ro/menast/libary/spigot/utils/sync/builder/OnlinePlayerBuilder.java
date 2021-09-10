package ro.menast.libary.spigot.utils.sync.builder;

import java.util.List;

public class OnlinePlayerBuilder implements OnlinePlayer {

    private final String uuid;
    private final String ip;
    private final String name;

    public OnlinePlayerBuilder(String uuid, String ip, String name) {
        this.uuid = uuid;
        this.ip = ip;
        this.name = name;
    }

    @Override
    public String getUUID() {
        return uuid;
    }

    @Override
    public String getIP() {
        return ip;
    }

    @Override
    public String getName() {
        return name;
    }

}
