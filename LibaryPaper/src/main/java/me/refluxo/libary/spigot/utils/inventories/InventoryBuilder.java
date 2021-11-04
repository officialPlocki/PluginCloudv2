package me.refluxo.libary.spigot.utils.inventories;

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
    this.title = displayName;
  }
  
  public InventoryBuilder(String displayName) {
    this.size = 0;
    this.title = displayName;
  }

  public InventoryBuilder setInventoryType(InventoryType type) {
    this.type = type;
    return this;
  }

  public Inventory buildInventory(Map<Integer, ItemStack> items) {
    Inventory inv;
    if (this.type == null) {
      inv = Bukkit.createInventory(null, this.size, this.title);
      for (int i : items.keySet()) {
        inv.setItem(i, items.get(i));
      } 
    } else {
      inv = Bukkit.createInventory(null, this.type, this.title);
      for (int i : items.keySet()) {
        inv.setItem(i, items.get(i));
      } 
    } 
    return inv;
  }
}
