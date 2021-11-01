package me.refluxo.libary.spigot.apis;

import me.refluxo.libary.spigot.utils.Version;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class TitleAPI {

    public TitleAPI() {}

    public void sendTitle(Player player, String title, String subtitle, int fadeIn, int stayTime, int fadeOut) {
        if(Version.getVersion().equalsIgnoreCase("1.8.8")) {
            IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\",color:" + ChatColor.GOLD.name().toLowerCase() + "}");

            PacketPlayOutTitle up = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, IChatBaseComponent.ChatSerializer.a(ChatColor.translateAlternateColorCodes('&', "{\"text\": \"" + title + "\"}")), fadeIn, stayTime, fadeOut);
            PacketPlayOutTitle down = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, IChatBaseComponent.ChatSerializer.a(ChatColor.translateAlternateColorCodes('&', "{\"text\": \"" + subtitle + "\"}")), fadeIn, stayTime, fadeOut);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(up);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(down);
        } else {
            player.sendTitle(title, subtitle, fadeIn, stayTime, fadeOut);
        }
    }

}
