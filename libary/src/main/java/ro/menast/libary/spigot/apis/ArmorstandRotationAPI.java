package ro.menast.libary.spigot.apis;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import ro.menast.libary.threaded.AsyncThreadScheduler;

import java.util.Objects;
import java.util.concurrent.ScheduledFuture;

public class ArmorstandRotationAPI {

    private final ArmorStand armorStand;
    private ScheduledFuture<?> future;
    private final Location loc;

    public ArmorstandRotationAPI(Location loc, ItemStack head) {
        this.loc = loc;
        this.armorStand = (ArmorStand) Objects.requireNonNull(loc.getWorld()).spawnEntity(loc, EntityType.ARMOR_STAND);
        armorStand.setGravity(false);
        armorStand.setVisible(false);
        armorStand.setHelmet(head);

    }

    public ArmorstandRotationAPI setSmall(boolean small) {
        armorStand.setSmall(small);
        return this;
    }

    public void start() {
        future = new AsyncThreadScheduler(() -> {
            loc.setYaw(loc.getYaw()+2.0F);
            armorStand.teleport(loc);
        }).scheduleAsyncTask(0L, 1L);
    }

    public void stop() {
        future.cancel(true);
    }

    public void remove() {
        stop();
        armorStand.remove();
    }

    public Location getLocation() {
        return loc;
    }

}
