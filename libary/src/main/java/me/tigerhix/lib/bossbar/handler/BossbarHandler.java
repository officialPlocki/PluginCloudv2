package me.tigerhix.lib.bossbar.handler;

import me.tigerhix.lib.bossbar.type.Bossbar;
import org.bukkit.entity.Player;

public interface BossbarHandler {
  Bossbar getBossbar(Player paramPlayer);
  
  boolean hasBossbar(Player paramPlayer);
  
  void updateBossbar(Player paramPlayer);
  
  void clearBossbar(Player paramPlayer);
}
