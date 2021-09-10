package ro.menast.libary.spigot.utils.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import ro.menast.libary.spigot.utils.Version;
import ro.menast.libary.spigot.utils.permissions.AstropermsPlayerAPI;

import java.lang.reflect.Field;

public class LoginListener implements Listener {

    @EventHandler
    public void onLogin(PlayerLoginEvent e) {
        if(Version.getVersion().equalsIgnoreCase("1.8.8")) {
            try {
                Field f = org.bukkit.craftbukkit.v1_8_R3.entity.CraftHumanEntity.class.getDeclaredField("perm");
                f.setAccessible(true);
                f.set(e.getPlayer(), new AstropermsPlayerAPI(e.getPlayer()));
                f.setAccessible(false);
            } catch (NoSuchFieldException | IllegalAccessException ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                Field f = org.bukkit.craftbukkit.v1_16_R3.entity.CraftHumanEntity.class.getDeclaredField("perm");
                f.setAccessible(true);
                f.set(e.getPlayer(), new AstropermsPlayerAPI(e.getPlayer()));
                f.setAccessible(false);
            } catch (NoSuchFieldException | IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
    }

}
