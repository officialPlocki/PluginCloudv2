package ro.menast.libary.bungee.utils.permissions;

import net.md_5.bungee.api.connection.ProxiedPlayer;

public class AstropermsPlayerAPI {
  private final ProxiedPlayer p;
  
  public AstropermsPlayerAPI(ProxiedPlayer player) {
    this.p = player;
  }
  
  public boolean hasPermission(String inName) {
    return (new AstropermsMySQL()).hasPermission(this.p, inName);
  }
  
  public boolean isOp() {
    return false;
  }
  
  public ProxiedPlayer getPlayer() {
    return this.p;
  }
  
  public void setGroup(String group) {
    (new AstropermsMySQL()).setProxiedPlayerGroup(this.p, new PermissionableGroupBuilder(group));
  }
  
  public Group getGroupOfPlayer() {
    return new PermissionableGroupBuilder((new AstropermsMySQL()).getProxiedPlayerGroup(this.p));
  }
  
  public void addPermission(String permission) {
    (new AstropermsMySQL()).addProxiedPlayerPermission(this.p, permission);
  }
  
  public void removePermission(String permission) {
    (new AstropermsMySQL()).removeProxiedPlayerPermission(this.p, permission);
  }
}
