package me.tigerhix.lib.bossbar.common;

import java.util.Map;
import net.minecraft.server.v1_8_R3.EntityTypes;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public final class NMS {
  public static void sendPacket(Player player, Packet... packets) {
    if (!(player instanceof CraftPlayer)) {
      player = player.getPlayer();
      if (!(player instanceof CraftPlayer))
        throw new IllegalArgumentException(); 
    } 
    for (Packet packet : packets)
      (((CraftPlayer)player).getHandle()).playerConnection.sendPacket(packet); 
  }
  
  public static void registerCustomEntity(String entityName, Class<?> entityClass, int entityId) {
    ((Map<String, Class<?>>)Reflections.<Map<String, Class<?>>>getField(EntityTypes.class, "c", (Class)Map.class).get(null)).put(entityName, entityClass);
    ((Map<Class<?>, String>)Reflections.<Map<Class<?>, String>>getField(EntityTypes.class, "d", (Class)Map.class).get(null)).put(entityClass, entityName);
    ((Map<Integer, Class<?>>)Reflections.<Map<Integer, Class<?>>>getField(EntityTypes.class, "e", (Class)Map.class).get(null)).put(Integer.valueOf(entityId), entityClass);
    ((Map<Class<?>, Integer>)Reflections.<Map<Class<?>, Integer>>getField(EntityTypes.class, "f", (Class)Map.class).get(null)).put(entityClass, Integer.valueOf(entityId));
    ((Map<String, Integer>)Reflections.<Map<String, Integer>>getField(EntityTypes.class, "g", (Class)Map.class).get(null)).put(entityName, Integer.valueOf(entityId));
  }
}
