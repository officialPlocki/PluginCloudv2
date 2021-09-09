package ro.menast.libary.spigot.apis;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import ro.menast.libary.spigot.utils.Version;

public class ActionbarAPI {
  public void sendActionbar(String text) {
    for (Player p : Bukkit.getOnlinePlayers()) {
      if (Version.getVersion().equalsIgnoreCase("1.8.8")) {
        PacketPlayOutChat packet = new PacketPlayOutChat((IChatBaseComponent)new ChatComponentText(text.replaceAll("&", "§")), (byte)2);
        (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packet);
        continue;
      } 
      p.spigot().sendMessage(ChatMessageType.ACTION_BAR, (BaseComponent)new TextComponent(text));
    } 
  }
  
  public void sendActionbar(Player p, String text) {
    if (Version.getVersion().equalsIgnoreCase("1.8.8")) {
      PacketPlayOutChat packet = new PacketPlayOutChat((IChatBaseComponent)new ChatComponentText(text.replaceAll("&", "§")), (byte)2);
      (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packet);
    } else {
      p.spigot().sendMessage(ChatMessageType.ACTION_BAR, (BaseComponent)new TextComponent(text));
    } 
  }
}
