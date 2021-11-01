package placeholder.tigerhix.lib.bossbar.type;

import net.minecraft.server.v1_8_R3.DataWatcher;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.Location;

public interface Bossbar {
  String getMessage();
  
  Bossbar setMessage(String paramString);
  
  float getPercentage();
  
  Bossbar setPercentage(float paramFloat);
  
  Packet getSpawnPacket();
  
  Packet getDestroyPacket();
  
  Packet getMetaPacket(DataWatcher paramDataWatcher);
  
  Packet getTeleportPacket(Location paramLocation);
  
  DataWatcher getWatcher();
}
