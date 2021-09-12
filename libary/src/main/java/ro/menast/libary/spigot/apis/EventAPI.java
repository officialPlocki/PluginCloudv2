package ro.menast.libary.spigot.apis;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import ro.menast.libary.spigot.LibarySpigot;

import java.util.function.Consumer;

public class EventAPI {

    public EventAPI() {}

    public <T extends Event> Listener registerEvent(EventPriority priority, Class<T> clazz, Consumer<T> consumer) {
        Listener listener = new Listener() {};
        Bukkit.getServer().getPluginManager().registerEvent(clazz, listener, priority, ((l, e) -> {
            if(clazz.isInstance(consumer)) consumer.accept((T) e);
        }), LibarySpigot.getInstance(), false);
        return listener;
    }

}
