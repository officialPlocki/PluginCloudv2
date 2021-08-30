package ro.menast.libary.utils.inventories;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class InventoryBuilder {

    private final String title;
    private InventoryType type = null;
    private final int size;

    public InventoryBuilder(String displayName, int size) {
        this.size = size;
        title = displayName;
        type = null;
    }

    public InventoryBuilder(String displayName) {
        this.size = 0;
        title = displayName;
        this.type = type;
    }

    public Inventory buildInventory(Map<Integer, ItemStack> items) {
        Inventory inv;
        if(type==null) {
            inv = Bukkit.createInventory(null, size, title);
            for(int i : items.keySet()) {
                inv.setItem(i, items.get(i));
            }
        } else {
            inv = Bukkit.createInventory(null, type, title);
            for(int i : items.keySet()) {
                inv.setItem(i, items.get(i));
            }
        }
        return inv;
    }

}
