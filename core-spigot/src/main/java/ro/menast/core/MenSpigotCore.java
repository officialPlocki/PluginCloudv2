package ro.menast.core;

import org.bukkit.plugin.java.JavaPlugin;
import ro.menast.libary.utils.projects.ProjectManager;
import ro.menast.libary.utils.projects.language.Language;

public final class MenSpigotCore extends JavaPlugin {

    private static ProjectManager projectManager;
    private static Language lang;


    @Override
    public void onEnable() {
        projectManager = new ProjectManager("SpigotCore");
        lang = new Language("SpigotCore");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Language getLanguage() {
        return lang;
    }

    public static ProjectManager getProjectManager() {
        return projectManager;
    }

}
