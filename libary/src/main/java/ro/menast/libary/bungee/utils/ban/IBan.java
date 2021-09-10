package ro.menast.libary.bungee.utils.ban;

import ro.menast.libary.bungee.utils.player.Player;

import java.util.Date;

public interface IBan {

    String getReplayID();

    boolean isUnbannable();

    Player bannedFrom();

    Player bannedPlayer();

    Date bannedAt();

    Date unbanAt();

    boolean isPerma();

    String getBanID();

}
