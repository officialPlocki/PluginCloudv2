package me.tigerhix.lib.bossbar.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import me.tigerhix.lib.bossbar.BossbarLib;
import me.tigerhix.lib.bossbar.common.NMS;
import me.tigerhix.lib.bossbar.type.Bossbar;
import me.tigerhix.lib.bossbar.type.BossbarWither;
import me.tigerhix.lib.bossbar.type.CraftWitherBossbar;
import me.tigerhix.lib.bossbar.type.WitherBossbar;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.Plugin;

public final class WitherBossbarHandler implements Listener, BossbarHandler {
  private final Plugin plugin;
  
  static {
    NMS.registerCustomEntity("WitherBoss", BossbarWither.class, 64);
  }
  
  private final Map<UUID, WitherBossbar> spawnedWithers = new HashMap<>();
  
  public WitherBossbarHandler() {
    this.plugin = BossbarLib.getPluginInstance();
    Bukkit.getServer().getPluginManager().registerEvents(this, this.plugin);
    Bukkit.getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
          public void run() {
            for (Player player : Bukkit.getOnlinePlayers())
              WitherBossbarHandler.this.teleport(player); 
          }
        },  0L, 20L);
  }
  
  @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
  public void onPlayerQuit(PlayerQuitEvent event) {
    clearBossbar(event.getPlayer());
  }
  
  @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
  public void onPlayerKick(PlayerKickEvent event) {
    clearBossbar(event.getPlayer());
  }
  
  @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
  public void onPlayerTeleport(PlayerTeleportEvent event) {
    teleport(event.getPlayer());
  }
  
  @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
  public void onPlayerRespawn(PlayerRespawnEvent event) {
    teleport(event.getPlayer());
  }
  
  private void teleport(final Player player) {
    if (!hasBossbar(player))
      return; 
    Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
          public void run() {
            WitherBossbarHandler.this.updateBossbar(player);
          }
        },  2L);
  }
  
  private WitherBossbar newBossbar() {
    return newBossbar(ChatColor.BOLD + "", 1.0F);
  }
  
  private WitherBossbar newBossbar(String message, float percentage) {
    return (new CraftWitherBossbar(message, null)).setMessage(message).setPercentage(percentage);
  }
  
  public void clearBossbar(Player player) {
    WitherBossbar bossbar = this.spawnedWithers.get(player.getUniqueId());
    this.spawnedWithers.remove(player.getUniqueId());
    if (bossbar == null || !bossbar.isSpawned() || bossbar.getDestroyPacket() == null)
      return; 
    NMS.sendPacket(player, new Packet[] { bossbar.getDestroyPacket() });
  }
  
  public WitherBossbar getBossbar(Player player) {
    WitherBossbar bossbar = this.spawnedWithers.get(player.getUniqueId());
    if (bossbar == null) {
      bossbar = newBossbar();
      this.spawnedWithers.put(player.getUniqueId(), bossbar);
    } 
    return bossbar;
  }
  
  public boolean hasBossbar(Player player) {
    return this.spawnedWithers.containsKey(player.getUniqueId());
  }
  
  public void updateBossbar(Player player) {
    WitherBossbar bossbar = this.spawnedWithers.get(player.getUniqueId());
    if (bossbar == null)
      return; 
    if (!bossbar.isSpawned()) {
      bossbar.setSpawned(true);
      bossbar.setSpawnLocation(player.getLocation().add(player.getEyeLocation().getDirection().multiply(20)));
      NMS.sendPacket(player, new Packet[] { bossbar.getSpawnPacket() });
    } 
    NMS.sendPacket(player, new Packet[] { bossbar.getMetaPacket(bossbar.getWatcher()) });
    NMS.sendPacket(player, new Packet[] { bossbar.getTeleportPacket(player.getLocation().add(player.getEyeLocation().getDirection().multiply(20))) });
  }
}
