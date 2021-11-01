package me.refluxo.libary.spigot.apis;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

public class ItemArmorstandAPI {

    private final ArmorStand armor;
    private final Location loc;
    private ItemStack stack;

    public ItemArmorstandAPI(Location loc) {
        this.loc = loc;
        this.armor = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
    }

    public ItemArmorstandAPI setHead(ItemStack stack) {
        this.stack = stack;
        return this;
    }

    public ItemArmorstandAPI setSmall(boolean small) {
        armor.setSmall(small);
        return this;
    }

    public void spawn() {
        armor.setGravity(false);
        armor.setVisible(false);
        Item item = loc.getWorld().dropItem(loc, stack);
        item.setPickupDelay(2147483647);
        armor.setPassenger(item);
    }

}
