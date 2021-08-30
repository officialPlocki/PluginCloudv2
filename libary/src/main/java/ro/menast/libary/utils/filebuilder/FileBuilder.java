package ro.menast.libary.utils.filebuilder;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileBuilder {

    private final File file;
    private final YamlConfiguration yml;

    public FileBuilder(String path) {
        String s = path;
        if(!s.contains("plugins/")){
            s = "plugins/API/"+path;
        }
        file = new File(s);
        yml = YamlConfiguration.loadConfiguration(file);
    }

    public YamlConfiguration getYaml() {
        return yml;
    }

    public File getFile() {
        return file;
    }

    public void save() {
        try {
            yml.save(file);
        } catch (IOException ignored) {
        }
    }

}
