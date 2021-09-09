package ro.menast.libary.spigot.utils.inventories;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemBuilder {
  private final String displayname;
  
  private Material material;
  
  private final List<String> lore = new ArrayList<>();
  
  private ItemStack item;
  
  private int amount = 1;
  
  boolean enchanted = false;
  
  public ItemBuilder(String displayname, ItemStack item, String lore) {
    this.displayname = displayname;
    this.item = item;
    this.lore.add(lore);
  }
  
  public ItemBuilder(String displayname, Material material, String lore) {
    this.displayname = displayname;
    this.material = material;
    this.lore.add(lore);
  }
  
  public ItemBuilder setAmount(int count) {
    this.amount = count;
    return this;
  }
  
  public ItemBuilder setEnchanted(boolean enabled) {
    this.enchanted = enabled;
    return this;
  }
  
  public ItemStack buildItem() {
    ItemStack itemstack;
    if (this.item != null) {
      itemstack = this.item;
    } else {
      itemstack = new ItemStack(this.material);
    } 
    ItemMeta itemMeta = itemstack.getItemMeta();
    assert itemMeta != null;
    itemMeta.setDisplayName(this.displayname);
    itemMeta.setLore(this.lore);
    if (this.enchanted) {
      itemMeta.addEnchant(Enchantment.DURABILITY, 0, true);
      itemMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
    } 
    itemstack.setItemMeta(itemMeta);
    itemstack.setAmount(this.amount);
    return itemstack;
  }
  
  public ItemStack buildSkull(String skullowner) {
    ItemStack itemstack = new ItemStack(Material.PLAYER_HEAD, 1);
    SkullMeta skullMeta = (SkullMeta)itemstack.getItemMeta();
    assert skullMeta != null;
    skullMeta.setDisplayName(this.displayname);
    skullMeta.setOwner(skullowner);
    skullMeta.setLore(this.lore);
    itemstack.setItemMeta((ItemMeta)skullMeta);
    itemstack.setAmount(this.amount);
    return itemstack;
  }
}
