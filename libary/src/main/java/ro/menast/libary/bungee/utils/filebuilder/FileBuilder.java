package ro.menast.libary.bungee.utils.filebuilder;

import java.io.File;
import java.io.IOException;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class FileBuilder {
  private final File file;
  
  private final ConfigurationProvider raw;
  
  private Configuration yml;
  
  public FileBuilder(String path) {
    String s = path;
    if (!s.contains("plugins/"))
      s = "plugins/API/" + path; 
    this.file = new File(s);
    this.raw = YamlConfiguration.getProvider(YamlConfiguration.class);
    try {
      this.yml = this.raw.load(this.file);
    } catch (IOException iOException) {}
  }
  
  public Configuration getYaml() {
    return this.yml;
  }
  
  public File getFile() {
    return this.file;
  }
  
  public ConfigurationProvider getConfigurationProvider() {
    return this.raw;
  }
  
  public void save() {
    try {
      this.raw.save(this.yml, this.file);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
}
