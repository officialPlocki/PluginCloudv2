package ro.menast.astroperms.api.permissions;

import java.util.List;
import java.util.UUID;

public class AstropermsGroupAPI {
  private Group group;
  
  public AstropermsGroupAPI(Group group) {
    this.group = group;
  }
  
  public void deleteGroup() {
    (new AstropermsMySQL()).deleteGroup(this.group);
  }
  
  public void createGroup() {
    (new AstropermsMySQL()).createGroup(this.group);
  }
  
  public boolean hasPermission(String permission) {
    return (new AstropermsMySQL()).hasGroupPermission(this.group, permission);
  }
  
  public void addPermission(String permission) {
    (new AstropermsMySQL()).addGroupPermission(this.group, permission);
  }
  
  public void removePermission(String permission) {
    (new AstropermsMySQL()).removeGroupPermission(this.group, permission);
  }
  
  public List<UUID> getGroupMembers() {
    return (new AstropermsMySQL()).getGroupMembers(this.group);
  }
}
