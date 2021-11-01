package me.refluxo.bansystem.utils.ban;

import ro.menast.libary.spigot.utils.player.Player;

import java.util.Date;

public class BanBuilder implements IBan {

    private final String replayID;
    private final boolean canUnbanned;
    private final Player from;
    private final Player to;
    private final Date ban;
    private final Date unban;
    private final boolean perma;
    private final String id;

    public BanBuilder(String replayID, boolean canUnbanned, Player from, Player to, Date bannedAt, Date unbanAt, boolean isPermanent, String banID) {
        this.replayID = replayID;
        this.canUnbanned = canUnbanned;
        this.from = from;
        this.to = to;
        this.ban = bannedAt;
        this.unban = unbanAt;
        this.perma = isPermanent;
        this.id = banID;
    }

    @Override
    public String getReplayID() {
        return replayID;
    }

    @Override
    public boolean isUnbannable() {
        return canUnbanned;
    }

    @Override
    public Player bannedFrom() {
        return from;
    }

    @Override
    public Player bannedPlayer() {
        return to;
    }

    @Override
    public Date bannedAt() {
        return ban;
    }

    @Override
    public Date unbanAt() {
        return unban;
    }

    @Override
    public boolean isPerma() {
        return perma;
    }

    @Override
    public String getBanID() {
        return id;
    }
}
