package ro.menast.libary.spigot.utils.permissions;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissibleBase;
import org.bukkit.permissions.ServerOperator;

public class AstropermsPlayerAPI extends PermissibleBase {
  private final Player p;
  
  public AstropermsPlayerAPI(Player player) {
    super((ServerOperator)player);
    this.p = player;
  }
  
  public boolean hasPermission(String inName) {
    return (new AstropermsMySQL()).hasPermission(this.p, inName);
  }
  
  public boolean isOp() {
    return false;
  }
  
  public Player getPlayer() {
    return this.p;
  }
  
  public void setGroup(String group) {
    (new AstropermsMySQL()).setPlayerGroup(this.p, new PermissionableGroupBuilder(group));
  }
  
  public Group getGroupOfPlayer() {
    return new PermissionableGroupBuilder((new AstropermsMySQL()).getPlayerGroup(this.p));
  }
  
  public void addPermission(String permission) {
    (new AstropermsMySQL()).addPlayerPermission(this.p, permission);
  }
  
  public void removePermission(String permission) {
    (new AstropermsMySQL()).removePlayerPermission(this.p, permission);
  }
}
