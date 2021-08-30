package ro.menast.libary.utils.projects;

import java.util.List;

public class ProjectManager {

    private final String pro;

    public ProjectManager(String project) {
        pro = project;
    }

    public void registerProject() {
        ProjectList.addProject(pro);
    }

    public void unregisterProject() {
        ProjectList.removeProject(pro);
    }

    public void addPermission(String permission) {
        PermissionList.addPermission(pro, permission);
    }

    public List<String> getPermissions() {
        return PermissionList.getPermissions(pro);
    }

}
