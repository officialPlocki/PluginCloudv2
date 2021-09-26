package ro.menast.gameapi.utils.builder;

import org.bukkit.entity.Player;
import ro.menast.gameapi.enums.EGameType;
import ro.menast.gameapi.interfaces.IGame;
import ro.menast.gameapi.interfaces.ITeam;

import java.util.UUID;

public class GameBuilder implements IGame {

    private final int max;
    private final int min;
    private final EGameType type;


    public GameBuilder(int maxPlayers, EGameType gameType) {
        this.type = gameType;
        max = maxPlayers;
        if(max==2) {
            min=2;
        } else if(max == 3) {
            min = 3;
        } else  {
            min = max/2;
        }
    }

    @Override
    public int getMaxPlayers() {
        return max;
    }

    @Override
    public int getMinPlayers() {
        return min;
    }

    @Override
    public ITeam[] getAllTeams() {
        return null;
    }

    @Override
    public ITeam[] getAliveTeams() {
        return null;
    }

    @Override
    public ITeam[] getDeadTeams() {
        return null;
    }

    @Override
    public Player[] getPlayers() {
        return null;
    }

    @Override
    public String getGameID() {
        return UUID.randomUUID().toString();
    }

    @Override
    public EGameType getGameType() {
        return type;
    }
}
