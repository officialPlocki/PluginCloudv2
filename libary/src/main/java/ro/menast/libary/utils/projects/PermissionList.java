package ro.menast.libary.utils.projects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PermissionList {

    private static HashMap<String, List<String>> perms = new HashMap<>();

    public static List<String> getPermissions(String name) {
        return perms.get(name);
    }

    public static void addPermission(String name, String permission) {
        List<String> perm = perms.get(name);
        perm.add(permission);
        perms.put(name, perm);
    }

}
