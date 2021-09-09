package ro.menast.libary.spigot.utils.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PermissionList {
  public static HashMap<String, List<String>> perms = new HashMap<>();
  
  public static void addPermission(String project, String perm) {
    List<String> list = getPermissions(project);
    list.add(perm);
    perms.put(project, list);
  }
  
  public static List<String> getPermissions(String project) {
    return perms.getOrDefault(project, new ArrayList<>());
  }
  
  public static List<String> getProjects() {
    return new ArrayList<>(perms.keySet());
  }
}
