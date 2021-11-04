package me.refluxo.libary.bungee.utils.filebuilder;

import me.refluxo.libary.bungee.utils.filebuilder.files.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileBuilder {

  private final File file;
  private final YamlConfiguration yml;

  public FileBuilder(String path) {
    String s = path;
    if (!s.contains("plugins/"))
      s = "plugins/API/" + path;
    this.file = new File(s);
    this.yml = YamlConfiguration.loadConfiguration(this.file);
  }

  public YamlConfiguration getYaml() {
    return this.yml;
  }

  public File getFile() {
    return this.file;
  }

  public void save() {
    try {
      this.yml.save(this.file);
    } catch (IOException ignored) {}
  }
}
