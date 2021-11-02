package me.refluxo.libary.bungee.utils.money;

import me.refluxo.libary.bungee.LibaryBungee;
import me.refluxo.libary.bungee.utils.mysql.MySQLService;
import me.refluxo.libary.bungee.utils.player.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CoinsAPI {

  private static final MySQLService service = LibaryBungee.getMySQL();

  public static void setup() {
    service.executeUpdate("CREATE TABLE IF NOT EXISTS coins(playerCoins INT, UUID TEXT)");
  }

  public static void addCoins(Player p, int amount) {
    try {
      PreparedStatement ps = service.getConnection().prepareStatement("UPDATE coins SET playerCoins = ? WHERE UUID = ?");
      ps.setInt(1, getCoins(p) + amount);
      ps.setString(2, p.getUniqueId());
      service.executeUpdate(ps);
    } catch (SQLException sQLException) {}
  }
  
  public static void resetCoins(Player p) {
    try {
      PreparedStatement ps = service.getConnection().prepareStatement("UPDATE coins SET playerCoins = ? WHERE UUID = ?");
      ps.setInt(1, 0);
      ps.setString(2, p.getUniqueId());
      service.executeUpdate(ps);
    } catch (SQLException sQLException) {}
  }
  
  public static void setCoins(Player p, int amount) {
    try {
      PreparedStatement ps = service.getConnection().prepareStatement("UPDATE coins SET playerCoins = ? WHERE UUID = ?");
      ps.setInt(1, amount);
      ps.setString(2, p.getUniqueId());
      service.executeUpdate(ps);
    } catch (SQLException sQLException) {}
  }
  
  public static void removeCoins(Player p, int amount) {
    try {
      PreparedStatement ps = service.getConnection().prepareStatement("UPDATE coins SET playerCoins = ? WHERE UUID = ?");
      ps.setInt(1, getCoins(p) - amount);
      ps.setString(2, p.getUniqueId());
      service.executeUpdate(ps);
    } catch (SQLException sQLException) {}
  }
  
  public static int getCoins(Player p) {
    try {
      PreparedStatement ps = service.getConnection().prepareStatement("SELECT playerCoins FROM coins WHERE UUID = ?");
      ps.setString(1, p.getUniqueId());
      ResultSet rs = service.getResult(ps);
      if (rs.next())
        return rs.getInt("playerCoins"); 
      setupPlayer(p);
      return getCoins(p);
    } catch (SQLException sQLException) {
      return -1;
    } 
  }
  
  public static void validateJoin(Player p) {
    if (getCoins(p) == -1)
      setupPlayer(p); 
  }
  
  public static void setupPlayer(Player p) {
    try {
      PreparedStatement ps = service.getConnection().prepareStatement("INSERT INTO coins(playerCoins,UUID) VALUES (?,?)");
      ps.setInt(1, 0);
      ps.setString(2, p.getUniqueId());
      service.executeUpdate(ps);
    } catch (Exception exception) {}
  }
}
