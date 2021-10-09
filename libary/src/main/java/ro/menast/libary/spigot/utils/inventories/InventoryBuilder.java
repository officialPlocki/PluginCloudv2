package ro.menast.libary.spigot.utils.inventories;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Iterator;
import java.util.Map;

public class InventoryBuilder {
  private final String title;
  
  private InventoryType type = null;
  
  private final int size;
  
  public InventoryBuilder(String displayName, int size) {
    this.size = size;
    this.title = displayName;
    this.type = null;
  }
  
  public InventoryBuilder(String displayName) {
    this.size = 0;
    this.title = displayName;
    this.type = this.type;
  }
  
  public Inventory buildInventory(Map<Integer, ItemStack> items) {
    Inventory inv;
    if (this.type == null) {
      inv = Bukkit.createInventory(null, this.size, this.title);
      for (Iterator<Integer> iterator = items.keySet().iterator(); iterator.hasNext(); ) {
        int i = iterator.next().intValue();
        inv.setItem(i, items.get(Integer.valueOf(i)));
      } 
    } else {
      inv = Bukkit.createInventory(null, this.type, this.title);
      for (Iterator<Integer> iterator = items.keySet().iterator(); iterator.hasNext(); ) {
        int i = iterator.next().intValue();
        inv.setItem(i, items.get(Integer.valueOf(i)));
      } 
    } 
    return inv;
  }
}
