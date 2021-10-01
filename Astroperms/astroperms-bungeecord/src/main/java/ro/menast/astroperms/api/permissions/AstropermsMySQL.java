package ro.menast.astroperms.api.permissions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import ro.menast.libary.bungee.LibaryBungee;
import ro.menast.libary.bungee.utils.mysql.MySQLService;
import ro.menast.libary.bungee.utils.player.Player;

public class AstropermsMySQL {
  private static final MySQLService mysql = LibaryBungee.getMySQL();
  
  private static final Connection con = mysql.getConnection();
  
  public static void setup() {
    try {
      PreparedStatement ps = mysql.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS permUser(uuid TEXT, groupss TEXT);");
      PreparedStatement ps2 = mysql.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS permGroup(name TEXT, permission TEXT);");
      PreparedStatement ps3 = mysql.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS userPermissions(uuid TEXT, permission TEXT);");
      PreparedStatement ps4 = mysql.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS groupss(name TEXT);");
      mysql.executeUpdate(ps);
      mysql.executeUpdate(ps2);
      mysql.executeUpdate(ps3);
    } catch (SQLException e) {
      e.printStackTrace();
    } 
  }
  
  public static void setupProxiedPlayer(Player player) {
    try {
      PreparedStatement ps = con.prepareStatement("INSERT INTO permUser(uuid,groupss) VALUES ('" + player.getUniqueId().toString() + "','default');");
      mysql.executeUpdate(ps);
    } catch (SQLException e) {
      e.printStackTrace();
    } 
  }
  
  public List<UUID> getGroupMembers(Group group) {
    List<UUID> ids = new ArrayList<>();
    try {
      PreparedStatement ps = con.prepareStatement("SELECT * FROM permUser WHERE groupss = '" + group.getGroupName() + "'");
      ResultSet rs = mysql.getResult(ps);
      while (rs.next())
        ids.add(UUID.fromString(rs.getString("uuid"))); 
    } catch (SQLException e) {
      e.printStackTrace();
    } 
    return ids;
  }
  
  public void setProxiedPlayerGroup(Player player, Group group) {
    try {
      PreparedStatement ps = con.prepareStatement("UPDATE permUser SET groupss = '" + group.getGroupName() + "' WHERE uuid = '" + player.getUniqueId().toString() + "'");
      mysql.executeUpdate(ps);
    } catch (SQLException e) {
      e.printStackTrace();
    } 
  }
  
  public void deleteGroup(Group group) {
    try {
      PreparedStatement ps = con.prepareStatement("DELETE FROM groupss WHERE name = '" + group.getGroupName() + "')");
      mysql.executeUpdate(ps);
    } catch (SQLException e) {
      e.printStackTrace();
    } 
  }
  
  public void createGroup(Group group) {
    try {
      PreparedStatement ps = con.prepareStatement("INSERT INTO groupss(name) VALUES ('" + group.getGroupName() + "')");
      mysql.executeUpdate(ps);
    } catch (SQLException e) {
      e.printStackTrace();
    } 
  }
  
  public String getProxiedPlayerGroup(Player player) {
    try {
      PreparedStatement ps = con.prepareStatement("SELECT groupss FROM permUser WHERE uuid = '" + player.getUniqueId().toString() + "'");
      ResultSet rs = mysql.getResult(ps);
      if (rs.next())
        return rs.getString("group"); 
      setupProxiedPlayer(player);
      return getProxiedPlayerGroup(player);
    } catch (SQLException e) {
      e.printStackTrace();
      return "default";
    } 
  }
  
  public boolean hasPermission(Player player, String permission) {
    try {
      PreparedStatement ps = con.prepareStatement("SELECT permission FROM userPermissions WHERE uuid = '" + player.getUniqueId().toString() + "'");
      ResultSet rs = mysql.getResult(ps);
      if (rs.next()) {
        String answer = rs.getString("permission");
        if (answer.startsWith("false:"))
          return hasGroupPermission(new PermissionableGroupBuilder(getProxiedPlayerGroup(player)), permission); 
        return true;
      } 
      return hasGroupPermission(new PermissionableGroupBuilder(getProxiedPlayerGroup(player)), permission);
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    } 
  }
  
  public boolean hasGroupPermission(Group group, String permission) {
    try {
      PreparedStatement ps = con.prepareStatement("SELECT permission FROM permGroup WHERE name = '" + group.getGroupName() + "'");
      ResultSet rs = mysql.getResult(ps);
      if (rs.next()) {
        String answer = rs.getString("permission");
        if (answer.startsWith("false:"))
          return false; 
        return true;
      } 
      return false;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    } 
  }
  
  public void addProxiedPlayerPermission(Player player, String permission) {
    try {
      PreparedStatement ps = con.prepareStatement("INSERT INTO userPermissions(uuid, permission) VALUES ('" + player.getUniqueId().toString() + "', '" + permission + "')");
      mysql.executeUpdate(ps);
    } catch (SQLException e) {
      e.printStackTrace();
    } 
  }
  
  public void addGroupPermission(Group group, String permission) {
    try {
      PreparedStatement ps = con.prepareStatement("INSERT INTO permGroup(name, permission) VALUES ('" + group.getGroupName() + "', '" + permission + "')");
      mysql.executeUpdate(ps);
    } catch (SQLException e) {
      e.printStackTrace();
    } 
  }
  
  public void removeProxiedPlayerPermission(Player player, String permission) {
    try {
      PreparedStatement ps = con.prepareStatement("DELETE FROM userPermissions WHERE permission = '" + permission + "' AND uuid = '" + player.getUniqueId().toString() + "')");
      mysql.executeUpdate(ps);
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public void removeGroupPermission(Group group, String permission) {
    try {
      PreparedStatement ps = con.prepareStatement("DELETE FROM permGroup WHERE permission = '" + permission + "' AND name = '" + group.getGroupName() + "')");
      mysql.executeUpdate(ps);
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
