package me.tigerhix.lib.bossbar.type;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;

public class CraftWitherBossbar extends WitherBossbar {
  private BossbarWither wither;
  
  public CraftWitherBossbar(String name, Location location) {
    super(name, location);
  }
  
  public Packet getSpawnPacket() {
    this.wither = new BossbarWither(((CraftWorld)this.spawnLocation.getWorld()).getHandle());
    this.wither.setLocation(this.spawnLocation.getX(), this.spawnLocation.getY(), this.spawnLocation.getZ(), this.spawnLocation.getYaw(), this.spawnLocation.getPitch());
    this.wither.setInvisible(false);
    this.wither.setCustomName(this.name);
    this.wither.setHealth(this.health);
    return new PacketPlayOutSpawnEntityLiving(this.wither);
  }
  
  public Packet getDestroyPacket() {
    if (this.wither == null)
      return null; 
    return new PacketPlayOutEntityDestroy(this.wither.getId());
  }
  
  public Packet getMetaPacket(DataWatcher watcher) {
    return new PacketPlayOutEntityMetadata(this.wither.getId(), watcher, true);
  }
  
  public Packet getTeleportPacket(Location location) {
    return new PacketPlayOutEntityTeleport(this.wither.getId(), location.getBlockX() * 32, location.getBlockY() * 32, location.getBlockZ() * 32, (byte)((int)location.getYaw() * 256 / 360), (byte)((int)location.getPitch() * 256 / 360), false);
  }
  
  public DataWatcher getWatcher() {
    DataWatcher watcher = new DataWatcher(this.wither);
    watcher.a(0, Byte.valueOf((byte)32));
    watcher.a(2, this.name);
    watcher.a(3, Byte.valueOf((byte)1));
    watcher.a(6, Float.valueOf(this.health));
    watcher.a(7, Integer.valueOf(0));
    watcher.a(8, Byte.valueOf((byte)0));
    watcher.a(10, this.name);
    watcher.a(11, Byte.valueOf((byte)1));
    return watcher;
  }
}
