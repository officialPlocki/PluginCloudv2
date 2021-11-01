package me.refluxo.gameapi.interfaces;

import me.refluxo.gameapi.enums.EGameType;
import org.bukkit.entity.Player;

public interface IGame {

    int getMaxPlayers();

    int getMinPlayers();

    ITeam[] getAllTeams();

    ITeam[] getAliveTeams();

    ITeam[] getDeadTeams();

    Player[] getPlayers();

    String getGameID();

    EGameType getGameType();

}
