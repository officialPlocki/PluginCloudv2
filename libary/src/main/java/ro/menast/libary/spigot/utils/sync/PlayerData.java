package ro.menast.libary.spigot.utils.sync;

import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import org.bukkit.entity.Player;
import ro.menast.libary.spigot.utils.mysql.MySQLService;

public class PlayerData {
  public static class PlayerDataMySQL {
    private static final MySQLService mysql = new MySQLService();

    private static final Connection con = mysql.getConnection();

    public static void init() {
      try {
        PreparedStatement ps = con.prepareStatement("CREATE TABLE IF NOT EXISTS player(uuid TEXT,name TEXT,ip TEXT);");
        mysql.executeUpdate(ps);
        PreparedStatement ps1 = con.prepareStatement("CREATE TABLE IF NOT EXISTS serverSwitch(uuid TEXT,serverName TEXT);");
        mysql.executeUpdate(ps1);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    public static void setupPlayer(Player p) {
      try {
        PreparedStatement ps = con.prepareStatement("INSERT INTO player(uuid,name,ip) VALUES (?,?,?)");
        ps.setString(1, p.getUniqueId().toString());
        ps.setString(2, p.getName());
        ps.setString(3, ((InetSocketAddress)Objects.<InetSocketAddress>requireNonNull(p.getAddress())).getHostName());
        mysql.executeUpdate(ps);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
