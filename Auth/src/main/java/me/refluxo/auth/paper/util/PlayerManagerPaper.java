package me.refluxo.auth.paper.util;

import me.refluxo.libary.spigot.utils.mysql.MySQLService;
import me.refluxo.libary.spigot.utils.player.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerManagerPaper {

    private static final MySQLService mysqlService = new MySQLService();

    private final Player player;

    public PlayerManagerPaper(Player player) {
        this.player = player;
    }

    public static void setup() {
        try {
            mysqlService.executeUpdate(mysqlService.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS auth(uuid TEXT, isOnline BOOLEAN)"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setOnline(boolean online) {
        try {
            PreparedStatement ps = mysqlService.getConnection().prepareStatement("UPDATE auth SET isOnline = ? WHERE uuid = ?");
            ps.setBoolean(1, online);
            ps.setString(2, player.getUniqueId());
            mysqlService.executeUpdate(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void checkContainsPlayer() {
        try {
            PreparedStatement ps = mysqlService.getConnection().prepareStatement("SELECT isOnline FROM auth WHERE uuid = ?");
            ps.setString(1, player.getUniqueId());
            ResultSet rs = mysqlService.getResult(ps);
            if(!rs.next()) {
                PreparedStatement inject = mysqlService.getConnection().prepareStatement("INSERT INTO auth(uuid, isOnline) VALUES (?,?)");
                inject.setString(1, player.getUniqueId());
                inject.setBoolean(2, true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isOnline() {
        try {
            PreparedStatement ps = mysqlService.getConnection().prepareStatement("SELECT isOnline FROM auth WHERE uuid = ?");
            ps.setString(1, player.getUniqueId());
            ResultSet rs = mysqlService.getResult(ps);
            if(rs.next()) {
                return rs.getBoolean("isOnline");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
