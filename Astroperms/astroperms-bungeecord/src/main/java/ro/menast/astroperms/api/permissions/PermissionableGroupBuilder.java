package ro.menast.astroperms.api.permissions;

public class PermissionableGroupBuilder implements Group {
  private final String name;
  
  public PermissionableGroupBuilder(String groupName) {
    this.name = groupName;
  }
  
  public String getGroupName() {
    return this.name;
  }
}
