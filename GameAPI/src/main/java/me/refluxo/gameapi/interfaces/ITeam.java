package me.refluxo.gameapi.interfaces;

import me.refluxo.gameapi.enums.EColor;
import org.bukkit.entity.Player;

public interface ITeam {

    EColor getTeamColor();

    Player[] getTeamPlayers();

    boolean canRespawn();

    int getAmountOfTeamMembers();

    int getAmountOfAliveTeamMembers();

    int getAmountOfDeadTeamMembers();

}
