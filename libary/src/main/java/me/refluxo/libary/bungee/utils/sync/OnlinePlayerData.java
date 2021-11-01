package me.refluxo.libary.bungee.utils.sync;

import me.refluxo.libary.bungee.LibaryBungee;
import me.refluxo.libary.bungee.utils.mysql.MySQLService;
import me.refluxo.libary.bungee.utils.player.Player;
import me.refluxo.libary.bungee.utils.player.PlayerAPI;
import me.refluxo.libary.bungee.utils.sync.builder.OnlinePlayer;
import me.refluxo.libary.bungee.utils.sync.builder.OnlinePlayerBuilder;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OnlinePlayerData {

  private static ProxiedPlayer executor = null;
  private static String errorMessage = null;

  public OnlinePlayerData(ProxiedPlayer executor, String errorMessage) {
    OnlinePlayerData.executor = executor;
    OnlinePlayerData.errorMessage = errorMessage;

  }

  public OnlinePlayerData() {}

  public void updatePlayer(ProxiedPlayer p) {
    OnlinePlayerDataMySQL.autoUpdate(p);
  }

  public OnlinePlayer getOnlinePlayer(ProxiedPlayer p) {
    return new OnlinePlayerBuilder(p.getUniqueId().toString(), p.getAddress().getHostName(), p.getName());
  }

  public List<OnlinePlayer> getAccounts(String ip) {
    return OnlinePlayerDataMySQL.getAccounts(ip);
  }
  
  public static class OnlinePlayerDataMySQL {
    private static final MySQLService mysql = LibaryBungee.getMySQL();

    private static final Connection con = mysql.getConnection();

    public static void init() {
      try {
        PreparedStatement ps = con.prepareStatement("CREATE TABLE IF NOT EXISTS player(uuid TEXT,name TEXT,ip TEXT);");
        mysql.executeUpdate(ps);
        PreparedStatement ps1 = con.prepareStatement("CREATE TABLE IF NOT EXISTS serverSwitch(uuid TEXT,serverName TEXT,timestamp LONGBLOB);");
        mysql.executeUpdate(ps1);
      } catch (SQLException e) {
        executor.sendMessage(ChatMessageType.CHAT, TextComponent.fromLegacyText(errorMessage));
      }
    }

    public static List<OnlinePlayer> getAccounts(String ip) {
      List<OnlinePlayer> list = new ArrayList<>();
      try {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM player WHERE ip = '"+ip+"'");
        ResultSet rs = mysql.getResult(ps);
        while (rs.next()) {
          list.add(new OnlinePlayerBuilder(rs.getString("uuid"), rs.getString("ip"), rs.getString("name")));
        }
      } catch (SQLException e) {
        executor.sendMessage(ChatMessageType.CHAT, TextComponent.fromLegacyText(errorMessage));
      }
      return list;
    }

    public static void autoUpdate(ProxiedPlayer p) {
      if(p.getUniqueId().toString().equalsIgnoreCase(getUniqueIdByIp(p))) {
        if(Objects.requireNonNull(p.getAddress()).getHostName().equalsIgnoreCase(getIpByUniqueID(p))) {
          if(!p.getName().equalsIgnoreCase(getNameByUniqueID(p))) {
            updateIpByUniqueID(p);
          }
        } else {
          updateIpByUniqueID(p);
        }
      } else if(getUniqueIdByIp(p) == null && getUniqueIdByName(p) == null) {
        setupPlayer(p);
      } else {
        p.disconnect(LibaryBungee.getProjectManager().getMessage("playerdata.uniqueid.notverifyed", new PlayerAPI(new Player(p.getUniqueId().toString())).getLanguage()));
      }
    }

    public static void updateNameByIP(ProxiedPlayer p) {
      try {
        PreparedStatement ps = con.prepareStatement("UPDATE player SET name = '"+p.getName()+"' WHERE ip = '"+ Objects.requireNonNull(p.getAddress()).getHostName()+"'");
        mysql.executeUpdate(ps);
      } catch (SQLException e) {
        executor.sendMessage(ChatMessageType.CHAT, TextComponent.fromLegacyText(errorMessage));
      }
    }

    public static void updateNameByUniqueID(ProxiedPlayer p) {
      try {
        PreparedStatement ps = con.prepareStatement("UPDATE player SET name = '"+p.getName()+"' WHERE uuid = '"+ p.getUniqueId() +"'");
        mysql.executeUpdate(ps);
      } catch (SQLException e) {
        executor.sendMessage(ChatMessageType.CHAT, TextComponent.fromLegacyText(errorMessage));
      }
    }

    public static void updateIpByName(ProxiedPlayer p) {
      try {
        PreparedStatement ps = con.prepareStatement("UPDATE player SET ip = '"+ Objects.requireNonNull(p.getAddress()).getHostName()+"' WHERE name = '"+p.getName()+"'");
        mysql.executeUpdate(ps);
      } catch (SQLException e) {
        executor.sendMessage(ChatMessageType.CHAT, TextComponent.fromLegacyText(errorMessage));
      }
    }

    public static void updateIpByUniqueID(ProxiedPlayer p) {
      try {
        PreparedStatement ps = con.prepareStatement("UPDATE player SET ip = '"+ Objects.requireNonNull(p.getAddress()).getHostName()+"' WHERE uuid = '"+ p.getUniqueId() +"'");
        mysql.executeUpdate(ps);
      } catch (SQLException e) {
        executor.sendMessage(ChatMessageType.CHAT, TextComponent.fromLegacyText(errorMessage));
      }
    }

    public static void setupPlayer(ProxiedPlayer p) {
      try {
        PreparedStatement ps = con.prepareStatement("INSERT INTO player(uuid,name,ip) VALUES (?,?,?)");
        ps.setString(1, p.getUniqueId().toString());
        ps.setString(2, p.getName());
        ps.setString(3, Objects.requireNonNull(p.getAddress()).getHostName());
        mysql.executeUpdate(ps);
      } catch (SQLException e) {
        executor.sendMessage(ChatMessageType.CHAT, TextComponent.fromLegacyText(errorMessage));
      }
    }

    public static String getNameByIP(ProxiedPlayer p) {
      try {
        PreparedStatement ps = con.prepareStatement("SELECT name FROM player WHERE ip = '"+ Objects.requireNonNull(p.getAddress()).getHostName()+"'");
        ResultSet rs = mysql.getResult(ps);
        if(rs.next()) {
          return rs.getString("name");
        } else {
          return null;
        }
      } catch (SQLException e) {
        executor.sendMessage(ChatMessageType.CHAT, TextComponent.fromLegacyText(errorMessage));
      }
      return null;
    }

    public static String getNameByUniqueID(ProxiedPlayer p) {
      try {
        PreparedStatement ps = con.prepareStatement("SELECT name FROM player WHERE uuid = '"+p.getUniqueId().toString()+"'");
        ResultSet rs = mysql.getResult(ps);
        if(rs.next()) {
          return rs.getString("name");
        } else {
          return null;
        }
      } catch (SQLException e) {
        executor.sendMessage(ChatMessageType.CHAT, TextComponent.fromLegacyText(errorMessage));
      }
      return null;
    }

    public static String getUniqueIdByIp(ProxiedPlayer p) {
      try {
        PreparedStatement ps = con.prepareStatement("SELECT uuid FROM player WHERE ip = '"+ Objects.requireNonNull(p.getAddress()).getHostName()+"'");
        ResultSet rs = mysql.getResult(ps);
        if(rs.next()) {
          return rs.getString("uuid");
        } else {
          return null;
        }
      } catch (SQLException e) {
        executor.sendMessage(ChatMessageType.CHAT, TextComponent.fromLegacyText(errorMessage));
      }
      return null;
    }

    public static String getUniqueIdByName(ProxiedPlayer p) {
      try {
        PreparedStatement ps = con.prepareStatement("SELECT uuid FROM player WHERE name = '"+p.getName()+"'");
        ResultSet rs = mysql.getResult(ps);
        if(rs.next()) {
          return rs.getString("uuid");
        } else {
          return null;
        }
      } catch (SQLException e) {
        executor.sendMessage(ChatMessageType.CHAT, TextComponent.fromLegacyText(errorMessage));
      }
      return null;
    }

    public static String getIpByUniqueID(ProxiedPlayer p) {
      try {
        PreparedStatement ps = con.prepareStatement("SELECT ip FROM player WHERE uuid = '"+p.getUniqueId().toString()+"'");
        ResultSet rs = mysql.getResult(ps);
        if(rs.next()) {
          return rs.getString("ip");
        } else {
          return null;
        }
      } catch (SQLException e) {
        executor.sendMessage(ChatMessageType.CHAT, TextComponent.fromLegacyText(errorMessage));
      }
      return null;
    }

    public static String getIpByName(ProxiedPlayer p) {
      try {
        PreparedStatement ps = con.prepareStatement("SELECT ip FROM player WHERE name = '"+p.getName()+"'");
        ResultSet rs = mysql.getResult(ps);
        if(rs.next()) {
          return rs.getString("ip");
        } else {
          return null;
        }
      } catch (SQLException e) {
        executor.sendMessage(ChatMessageType.CHAT, TextComponent.fromLegacyText(errorMessage));
      }
      return null;
    }

  }
}
