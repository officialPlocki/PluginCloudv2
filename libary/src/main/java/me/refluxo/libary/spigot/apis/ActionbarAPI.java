package me.refluxo.libary.spigot.apis;

import me.refluxo.libary.spigot.utils.Version;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ActionbarAPI {
  public void sendActionbar(String text) {
    for (Player p : Bukkit.getOnlinePlayers()) {
      if (Version.getVersion().equalsIgnoreCase("1.8.8")) {
        PacketPlayOutChat packet = new PacketPlayOutChat(new ChatComponentText(text.replaceAll("&", "§")), (byte)2);
        (((CraftPlayer)p).getHandle()).playerConnection.sendPacket(packet);
        continue;
      } 
      p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(text));
    } 
  }
  
  public void sendActionbar(Player p, String text) {
    if (Version.getVersion().equalsIgnoreCase("1.8.8")) {
      PacketPlayOutChat packet = new PacketPlayOutChat(new ChatComponentText(text.replaceAll("&", "§")), (byte)2);
      (((CraftPlayer)p).getHandle()).playerConnection.sendPacket(packet);
    } else {
      p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(text));
    } 
  }
}
