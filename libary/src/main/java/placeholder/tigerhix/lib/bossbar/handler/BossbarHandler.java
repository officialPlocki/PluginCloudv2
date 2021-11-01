package placeholder.tigerhix.lib.bossbar.handler;

import org.bukkit.entity.Player;
import placeholder.tigerhix.lib.bossbar.type.Bossbar;

public interface BossbarHandler {
  Bossbar getBossbar(Player paramPlayer);
  
  boolean hasBossbar(Player paramPlayer);
  
  void updateBossbar(Player paramPlayer);
  
  void clearBossbar(Player paramPlayer);
}
