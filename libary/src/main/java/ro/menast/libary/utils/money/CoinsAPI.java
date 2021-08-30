package me.refluxo.api.spigot.utils.server.global;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import ro.menast.libary.utils.mysql.MySQLService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CoinsAPI {

    public static void addCoins(OfflinePlayer p, int amount) {
        try {
            MySQLService service = new MySQLService();
            PreparedStatement ps = service.getConnection().prepareStatement("UPDATE coins SET playerCoins = ? WHERE UUID = ?");
            ps.setInt(1, getCoins(p)+amount);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (SQLException ignored) {
        }
    }
    public static void resetCoins(OfflinePlayer p) {
        try {
            MySQLService service = new MySQLService();
            PreparedStatement ps = service.getConnection().prepareStatement("UPDATE coins SET playerCoins = ? WHERE UUID = ?");
            ps.setInt(1, 0);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (SQLException ignored) {
        }
    }
    public static void setCoins(OfflinePlayer p, int amount) {
        try {
            MySQLService service = new MySQLService();
            PreparedStatement ps = service.getConnection().prepareStatement("UPDATE coins SET playerCoins = ? WHERE UUID = ?");
            ps.setInt(1, amount);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (SQLException ignored) {
        }
    }
    public static void removeCoins(OfflinePlayer p, int amount) {
        try {
            MySQLService service = new MySQLService();
            PreparedStatement ps = service.getConnection().prepareStatement("UPDATE coins SET playerCoins = ? WHERE UUID = ?");
            ps.setInt(1, getCoins(p)-amount);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (SQLException ignored) {
        }
    }

    public static int getCoins(OfflinePlayer p) {
        try {
            MySQLService service = new MySQLService();
            PreparedStatement ps = service.getConnection().prepareStatement("SELECT playerCoins FROM coins WHERE UUID = ?");
            ps.setString(1, p.getUniqueId().toString());
            ResultSet rs = service.getResult(ps);
            if(rs.next()) {
                return rs.getInt("playerCoins");
            } else {
                setupPlayer(p);
                return getCoins(p);
            }
        } catch (SQLException ignored) {
        }
        return -1;
    }

    public static void validateJoin(OfflinePlayer p) {
        if(getCoins(p) == -1) {
            setupPlayer(p);
        }
    }

    public static void setupPlayer(OfflinePlayer p) {
        try {
            MySQLService service = new MySQLService();
            PreparedStatement ps = service.getConnection().prepareStatement("INSERT INTO coins(playerCoins,UUID) VALUES (?,?)");
            ps.setInt(1, 0);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (Exception ignored) {
        }
    }

    /////////////////////////////////


    public static void addCoins(Player p, int amount) {
        try {
            MySQLService service = new MySQLService();
            PreparedStatement ps = service.getConnection().prepareStatement("UPDATE coins SET playerCoins = ? WHERE UUID = ?");
            ps.setInt(1, getCoins(p)+amount);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (SQLException ignored) {
        }
    }
    public static void resetCoins(Player p) {
        try {
            MySQLService service = new MySQLService();
            PreparedStatement ps = service.getConnection().prepareStatement("UPDATE coins SET playerCoins = ? WHERE UUID = ?");
            ps.setInt(1, 0);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (SQLException ignored) {
        }
    }
    public static void setCoins(Player p, int amount) {
        try {
            MySQLService service = new MySQLService();
            PreparedStatement ps = service.getConnection().prepareStatement("UPDATE coins SET playerCoins = ? WHERE UUID = ?");
            ps.setInt(1, amount);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (SQLException ignored) {
        }
    }
    public static void removeCoins(Player p, int amount) {
        try {
            MySQLService service = new MySQLService();
            PreparedStatement ps = service.getConnection().prepareStatement("UPDATE coins SET playerCoins = ? WHERE UUID = ?");
            ps.setInt(1, getCoins(p)-amount);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (SQLException ignored) {
        }
    }

    public static int getCoins(Player p) {
        try {
            MySQLService service = new MySQLService();
            PreparedStatement ps = service.getConnection().prepareStatement("SELECT playerCoins FROM coins WHERE UUID = ?");
            ps.setString(1, p.getUniqueId().toString());
            ResultSet rs = service.getResult(ps);
            if(rs.next()) {
                return rs.getInt("playerCoins");
            } else {
                setupPlayer(p);
                return getCoins(p);
            }
        } catch (SQLException ignored) {
        }
        return -1;
    }

    public static void validateJoin(Player p) {
        if(getCoins(p) == -1) {
            setupPlayer(p);
        }
    }

    public static void setupPlayer(Player p) {
        try {
            MySQLService service = new MySQLService();
            PreparedStatement ps = service.getConnection().prepareStatement("INSERT INTO coins(playerCoins,UUID) VALUES (?,?)");
            ps.setInt(1, 0);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (Exception ignored) {
        }
    }

}
