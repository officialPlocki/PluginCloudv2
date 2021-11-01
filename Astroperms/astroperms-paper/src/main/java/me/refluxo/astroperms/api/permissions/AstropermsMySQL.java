package me.refluxo.astroperms.api.permissions;

import me.refluxo.libary.spigot.LibarySpigot;
import me.refluxo.libary.spigot.utils.mysql.MySQLService;
import me.refluxo.libary.spigot.utils.player.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AstropermsMySQL {
  private static final MySQLService mysql = LibarySpigot.getMySQL();
  
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
  
  public static void setupPlayer(Player player) {
    try {
      PreparedStatement ps = con.prepareStatement("INSERT INTO permUser(uuid,groupss) VALUES ('" + player.getUniqueId() + "','default');");
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
  
  public void setPlayerGroup(Player player, Group group) {
    try {
      PreparedStatement ps = con.prepareStatement("UPDATE permUser SET groupss = '" + group.getGroupName() + "' WHERE uuid = '" + player.getUniqueId() + "'");
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
  
  public String getPlayerGroup(Player player) {
    try {
      PreparedStatement ps = con.prepareStatement("SELECT groupss FROM permUser WHERE uuid = '" + player.getUniqueId() + "'");
      ResultSet rs = mysql.getResult(ps);
      if (rs.next())
        return rs.getString("group"); 
      setupPlayer(player);
      return getPlayerGroup(player);
    } catch (SQLException e) {
      e.printStackTrace();
      return "default";
    } 
  }
  
  public boolean hasPermission(Player player, String permission) {
    try {
      PreparedStatement ps = con.prepareStatement("SELECT permission FROM userPermissions WHERE uuid = '" + player.getUniqueId() + "'");
      ResultSet rs = mysql.getResult(ps);
      if (rs.next()) {
        String answer = rs.getString("permission");
        if (answer.startsWith("false:"))
          return hasGroupPermission(new PermissionableGroupBuilder(getPlayerGroup(player)), permission); 
        return true;
      } 
      return hasGroupPermission(new PermissionableGroupBuilder(getPlayerGroup(player)), permission);
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
          return !answer.startsWith("false:");
      } 
      return false;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    } 
  }
  
  public void addPlayerPermission(Player player, String permission) {
    try {
      PreparedStatement ps = con.prepareStatement("INSERT INTO userPermissions(uuid, permission) VALUES ('" + player.getUniqueId() + "', '" + permission + "')");
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
  
  public void removePlayerPermission(Player player, String permission) {
    try {
      PreparedStatement ps = con.prepareStatement("DELETE FROM userPermissions WHERE permission = '" + permission + "' AND uuid = '" + player.getUniqueId() + "')");
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
