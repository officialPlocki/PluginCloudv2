package me.tigerhix.lib.bossbar.type;

import me.tigerhix.lib.bossbar.common.Maths;
import org.bukkit.Location;

public abstract class WitherBossbar implements Bossbar {
  protected static final float MAX_HEALTH = 300.0F;
  
  protected boolean spawned;
  
  protected Location spawnLocation;
  
  protected String name;
  
  protected float health;
  
  public WitherBossbar(String message, Location spawnLocation) {
    this.spawnLocation = spawnLocation;
    this.name = message;
    this.health = 300.0F;
  }
  
  public String getMessage() {
    return this.name;
  }
  
  public WitherBossbar setMessage(String message) {
    this.name = message;
    return this;
  }
  
  public float getPercentage() {
    return this.health / 300.0F;
  }
  
  public WitherBossbar setPercentage(float percentage) {
    percentage = Maths.clamp(Float.valueOf(percentage), Float.valueOf(0.0F), Float.valueOf(1.0F)).floatValue();
    this.health = percentage * 300.0F;
    return this;
  }
  
  public boolean isSpawned() {
    return this.spawned;
  }
  
  public void setSpawned(boolean spawned) {
    this.spawned = spawned;
  }
  
  public Location getSpawnLocation() {
    return this.spawnLocation;
  }
  
  public void setSpawnLocation(Location spawnLocation) {
    this.spawnLocation = spawnLocation;
  }
}
