package ro.menast.libary.spigot.apis;

import me.tigerhix.lib.bossbar.BossbarLib;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import ro.menast.libary.spigot.LibarySpigot;
import ro.menast.libary.spigot.utils.Version;
import ro.menast.libary.threaded.AsyncThreadScheduler;

public class BossbarAPI {
  public void sendBossbar(Player p, String text, int time) {
    if (Version.getVersion().equalsIgnoreCase("1.8.8")) {
      BossbarLib.getHandler().clearBossbar(p);
      BossbarLib.getHandler().getBossbar(p).setMessage(text);
      BossbarLib.getHandler().updateBossbar(p);
      (new AsyncThreadScheduler(() -> BossbarLib.getHandler().clearBossbar(p)))
        
        .runAsyncTaskLater(time);
    } else {
      BossBar bar = Bukkit.createBossBar(text, BarColor.BLUE, BarStyle.SEGMENTED_6, new BarFlag[0]);
      bar.setVisible(true);
      bar.addPlayer(p);
      (new AsyncThreadScheduler(() -> bar.removePlayer(p))).runAsyncTaskLater(time);
    } 
  }
  
  public void sendBossbar(Player p, String text, int time, BarColor color, BarStyle style, BarFlag... flags) {
    if (Version.getVersion().equalsIgnoreCase("1.8.8")) {
      BossbarLib.getHandler().clearBossbar(p);
      BossbarLib.getHandler().getBossbar(p).setMessage(text);
      BossbarLib.getHandler().updateBossbar(p);
      (new AsyncThreadScheduler(() -> BossbarLib.getHandler().clearBossbar(p)))
        
        .runAsyncTaskLater(time);
    } else {
      BossBar bar = Bukkit.createBossBar(text, color, style, flags);
      bar.setVisible(true);
      bar.addPlayer(p);
      Bukkit.getScheduler().runTaskLater((Plugin)LibarySpigot.getInstance(), () -> bar.removePlayer(p), time * 20L);
    } 
  }
}
