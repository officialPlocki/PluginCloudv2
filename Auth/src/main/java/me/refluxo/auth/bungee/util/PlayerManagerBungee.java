package me.refluxo.auth.bungee.util;

import me.refluxo.auth.bungee.AuthBungee;
import me.refluxo.libary.bungee.utils.mysql.MySQLService;
import me.refluxo.libary.bungee.utils.player.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerManagerBungee {

    private static final MySQLService mysqlService = AuthBungee.getMySQL();
    private static final Connection connection = mysqlService.getConnection();

    private final Player player;

    public PlayerManagerBungee(Player player) {
        this.player = player;
    }

    public static void setup() {
        mysqlService.executeUpdate("CREATE TABLE IF NOT EXISTS auth(uuid TEXt, isOnline BOOLEAN)");
    }

    public void setOnline(boolean online) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE auth SET isOnline = ? WHERE uuid = ?");
            ps.setBoolean(1, online);
            ps.setString(2, player.getUniqueId());
            mysqlService.executeUpdate(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void checkContainsPlayer() {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT isOnline FROM auth WHERE uuid = ?");
            ps.setString(1, player.getUniqueId());
            ResultSet rs = mysqlService.getResult(ps);
            if(!rs.next()) {
                PreparedStatement inject = connection.prepareStatement("INSERT INTO auth(uuid, isOnline) VALUES (?,?)");
                inject.setString(1, player.getUniqueId());
                inject.setBoolean(2, true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
