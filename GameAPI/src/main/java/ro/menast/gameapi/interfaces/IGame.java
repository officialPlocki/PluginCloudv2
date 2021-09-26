package ro.menast.gameapi.interfaces;

import org.bukkit.entity.Player;
import ro.menast.gameapi.enums.EGameType;

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
