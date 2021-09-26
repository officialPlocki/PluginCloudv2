package ro.menast.gameapi.interfaces;

import org.bukkit.entity.Player;
import ro.menast.gameapi.enums.EColor;

public interface ITeam {

    EColor getTeamColor();

    Player[] getTeamPlayers();

    boolean canRespawn();

    int getAmountOfTeamMembers();

    int getAmountOfAliveTeamMembers();

    int getAmountOfDeadTeamMembers();

}
