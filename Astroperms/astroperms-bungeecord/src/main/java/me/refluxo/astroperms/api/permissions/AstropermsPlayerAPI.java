package me.refluxo.astroperms.api.permissions;

import ro.menast.libary.bungee.utils.player.Player;

public class AstropermsPlayerAPI {
  private final Player p;
  
  public AstropermsPlayerAPI(Player player) {
    this.p = player;
  }

  public AstropermsPlayerAPI(String uuid) {
    this.p = new Player(uuid);
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
