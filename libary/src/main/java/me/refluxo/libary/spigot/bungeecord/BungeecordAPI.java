package ro.menast.libary.spigot.bungeecord;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import ro.menast.libary.spigot.LibarySpigot;

import java.util.Objects;

public class BungeecordAPI {
  public void sendPlayer(Player player, String server) {
    if (!Bukkit.getMessenger().isOutgoingChannelRegistered(LibarySpigot.getInstance(), "BungeeCord"))
      Bukkit.getMessenger().registerOutgoingPluginChannel(LibarySpigot.getInstance(), "BungeeCord");
    ByteArrayDataOutput out = ByteStreams.newDataOutput();
    out.writeUTF("ConnectOther");
    out.writeUTF(Objects.requireNonNull(player.getPlayer()).getName());
    out.writeUTF(server);
    Bukkit.getServer().sendPluginMessage(LibarySpigot.getInstance(), "BungeeCord", out.toByteArray());
  }
  
  public void kickPlayer(Player player, String message) {
    if (!Bukkit.getMessenger().isOutgoingChannelRegistered(LibarySpigot.getInstance(), "BungeeCord"))
      Bukkit.getMessenger().registerOutgoingPluginChannel(LibarySpigot.getInstance(), "BungeeCord");
    ByteArrayDataOutput out = ByteStreams.newDataOutput();
    out.writeUTF("KickPlayer");
    out.writeUTF(Objects.requireNonNull(player.getPlayer()).getName());
    out.writeUTF(message);
    Bukkit.getServer().sendPluginMessage(LibarySpigot.getInstance(), "BungeeCord", out.toByteArray());
  }
}
