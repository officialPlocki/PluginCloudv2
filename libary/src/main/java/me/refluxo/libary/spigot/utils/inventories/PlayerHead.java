package me.refluxo.libary.spigot.utils.inventories;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

public class PlayerHead {
  public static ItemStack getItemStackWithTexture(ItemStack head, String long_key) {
    SkullMeta meta = (SkullMeta)head.getItemMeta();
    GameProfile profile = new GameProfile(UUID.randomUUID(), "");
    profile.getProperties().put("textures", new Property("textures", long_key));
    try {
      assert meta != null;
      Field profileField = meta.getClass().getDeclaredField("profile");
      profileField.setAccessible(true);
      profileField.set(meta, profile);
    } catch (IllegalArgumentException|IllegalAccessException|NoSuchFieldException|SecurityException e) {
      e.printStackTrace();
    } 
    head.setItemMeta(meta);
    return head;
  }
}
