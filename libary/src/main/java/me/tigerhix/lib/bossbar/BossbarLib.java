package me.tigerhix.lib.bossbar;

import me.tigerhix.lib.bossbar.handler.BossbarHandler;
import me.tigerhix.lib.bossbar.handler.WitherBossbarHandler;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import ro.menast.libary.spigot.LibarySpigot;

public final class BossbarLib extends JavaPlugin {
  
  private static BossbarHandler handler;

  public static BossbarHandler getHandler() {
    return handler;
  }
  
  public static void setHandler() {
    BossbarLib.handler = new WitherBossbarHandler();
  }

  public static Plugin getPluginInstance() {
      return LibarySpigot.getInstance();
  }

}
