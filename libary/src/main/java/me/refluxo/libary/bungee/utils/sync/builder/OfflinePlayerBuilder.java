package me.refluxo.libary.bungee.utils.sync.builder;

public class OfflinePlayerBuilder implements OfflinePlayer {

    private final String uuid;
    private final String ip;
    private final String name;

    public OfflinePlayerBuilder(String uuid, String ip, String name) {
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
    public String getName() {return name;}

}
