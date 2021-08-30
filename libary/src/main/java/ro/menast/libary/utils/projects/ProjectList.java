package ro.menast.libary.utils.projects;

import java.util.ArrayList;
import java.util.List;

public class ProjectList {

    private static final List<String> projects = new ArrayList<>();

    public static void addProject(String name) {
        if(!projects.contains(name)) {
            projects.add(name);
        }
    }

    public static void removeProject(String name) {
        projects.remove(name);
    }

    public List<String> getProjects() {
        return projects;
    }

}
