package placeholder.tigerhix.lib.bossbar;

import me.refluxo.libary.spigot.LibarySpigot;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import placeholder.tigerhix.lib.bossbar.handler.BossbarHandler;
import placeholder.tigerhix.lib.bossbar.handler.WitherBossbarHandler;

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
