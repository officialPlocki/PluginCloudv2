package ro.menast.libary.spigot.bungeecord;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import java.util.Objects;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import ro.menast.libary.spigot.LibarySpigot;

public class BungeecordAPI {
  public void sendPlayer(Player player, String server) {
    if (!Bukkit.getMessenger().isOutgoingChannelRegistered((Plugin)LibarySpigot.getInstance(), "BungeeCord"))
      Bukkit.getMessenger().registerOutgoingPluginChannel((Plugin)LibarySpigot.getInstance(), "BungeeCord"); 
    ByteArrayDataOutput out = ByteStreams.newDataOutput();
    out.writeUTF("ConnectOther");
    out.writeUTF(((Player)Objects.<Player>requireNonNull(player.getPlayer())).getName());
    out.writeUTF(server);
    Bukkit.getServer().sendPluginMessage((Plugin)LibarySpigot.getInstance(), "BungeeCord", out.toByteArray());
  }
  
  public void kickPlayer(Player player, String message) {
    if (!Bukkit.getMessenger().isOutgoingChannelRegistered((Plugin)LibarySpigot.getInstance(), "BungeeCord"))
      Bukkit.getMessenger().registerOutgoingPluginChannel((Plugin)LibarySpigot.getInstance(), "BungeeCord"); 
    ByteArrayDataOutput out = ByteStreams.newDataOutput();
    out.writeUTF("KickPlayer");
    out.writeUTF(((Player)Objects.<Player>requireNonNull(player.getPlayer())).getName());
    out.writeUTF(message);
    Bukkit.getServer().sendPluginMessage((Plugin)LibarySpigot.getInstance(), "BungeeCord", out.toByteArray());
  }
}
