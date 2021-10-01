package ro.menast.libary.spigot.utils.sync;

import org.bukkit.entity.Player;
import ro.menast.libary.spigot.utils.mysql.MySQLService;
import ro.menast.libary.spigot.LibarySpigot;
import ro.menast.libary.spigot.utils.sync.builder.OfflinePlayer;
import ro.menast.libary.spigot.utils.sync.builder.OfflinePlayerBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OfflinePlayerData {

  private static Player executor = null;
  private static String errorMessage = null;

  public OfflinePlayerData(Player executor, String errorMessage) {
    OfflinePlayerData.executor = executor;
    OfflinePlayerData.errorMessage = errorMessage;
  }

  public OfflinePlayerData() {}

  public OfflinePlayer getOfflinePlayer(String name) {
    return new OfflinePlayerBuilder(OfflinePlayerDataMySQL.getUniqueIdByName(name), OfflinePlayerDataMySQL.getIpByName(name), OfflinePlayerDataMySQL.getNameByUniqueID(OfflinePlayerDataMySQL.getUniqueIdByName(name)));
  }

  public List<OfflinePlayer> getAccounts(String ip) {
    return OfflinePlayerDataMySQL.getAccounts(ip);
  }

  public static class OfflinePlayerDataMySQL {
    private static final MySQLService mysql = LibarySpigot.getMySQL();

    private static final Connection con = mysql.getConnection();

    public static List<OfflinePlayer> getAccounts(String ip) {
      List<OfflinePlayer> list = new ArrayList<>();
      try {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM player WHERE ip = '"+ip+"'");
        ResultSet rs = mysql.getResult(ps);
        while (rs.next()) {
          list.add(new OfflinePlayerBuilder(rs.getString("uuid"), rs.getString("ip"), rs.getString("name")));
        }
      } catch (SQLException e) {
        OfflinePlayerData.executor.sendMessage(errorMessage);
      }
      return list;
    }

    public static String getNameByUniqueID(String uuid) {
      try {
        PreparedStatement ps = con.prepareStatement("SELECT name FROM player WHERE uuid = '"+uuid+"'");
        ResultSet rs = mysql.getResult(ps);
        if(rs.next()) {
          return rs.getString("name");
        } else {
          return "";
        }
      } catch (SQLException e) {
        OfflinePlayerData.executor.sendMessage(errorMessage);
      }
      return null;
    }

    public static String getUniqueIdByName(String name) {
      try {
        PreparedStatement ps = con.prepareStatement("SELECT uuid FROM player WHERE name = '"+name+"'");
        ResultSet rs = mysql.getResult(ps);
        if(rs.next()) {
          return rs.getString("uuid");
        } else {
          return "";
        }
      } catch (SQLException e) {
        OfflinePlayerData.executor.sendMessage(errorMessage);
      }
      return null;
    }

    public static String getIpByName(String name) {
      try {
        PreparedStatement ps = con.prepareStatement("SELECT ip FROM player WHERE name = '"+name+"'");
        ResultSet rs = mysql.getResult(ps);
        if(rs.next()) {
          return rs.getString("ip");
        } else {
          return "";
        }
      } catch (SQLException e) {
        OfflinePlayerData.executor.sendMessage(errorMessage);
      }
      return null;
    }

  }
}
