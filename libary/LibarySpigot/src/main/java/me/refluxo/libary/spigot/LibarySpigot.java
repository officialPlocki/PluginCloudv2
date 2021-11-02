package me.refluxo.libary.spigot;

import me.refluxo.libary.spigot.listener.JoinListener;
import me.refluxo.libary.spigot.utils.filebuilder.FileBuilder;
import me.refluxo.libary.spigot.utils.money.MoneyAPI;
import me.refluxo.libary.spigot.utils.mysql.MySQLService;
import me.refluxo.libary.spigot.utils.project.ProjectManager;
import me.refluxo.libary.spigot.utils.sync.OnlinePlayerData;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class LibarySpigot extends JavaPlugin {

  private static ProjectManager projectManager;
  private static JavaPlugin instance;
  private static MySQLService mySQLService;
  
  public void onEnable() {
    projectManager = new ProjectManager("LibarySpigot");
    instance = this;
    FileBuilder fb = new FileBuilder("plugins/libary/config.yml");
    YamlConfiguration conf = fb.getYaml();
    if (!fb.getFile().exists()) {
      conf.set("mysql.host", "localhost");
      conf.set("mysql.port", 3306);
      conf.set("mysql.user", "root");
      conf.set("mysql.password", "abc123");
      conf.set("mysql.database", "core");
      fb.save();
    }
    MySQLService.connect(conf.getString("mysql.host"), conf.getString("mysql.user"), conf.getString("mysql.database"), conf.getString("mysql.password"), conf.getString("mysql.port"));
    MySQLService.setMaxConnections();
    mySQLService = new MySQLService();
    OnlinePlayerData.OnlinePlayerDataMySQL.init();
    MoneyAPI.setup();
    Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
  }

  public void onDisable() {
    MySQLService.disconnect();
  }
  
  public static MySQLService getMySQL() {
    return mySQLService;
  }
  
  public static JavaPlugin getInstance() {
    return instance;
  }
  
  public static ProjectManager getProjectManager() {
    return projectManager;
  }
}
