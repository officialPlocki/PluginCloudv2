package me.refluxo.libary.bungee;

import me.refluxo.libary.bungee.listener.JoinListener;
import me.refluxo.libary.bungee.utils.filebuilder.FileBuilder;
import me.refluxo.libary.bungee.utils.filebuilder.files.YamlConfiguration;
import me.refluxo.libary.bungee.utils.money.CoinsAPI;
import me.refluxo.libary.bungee.utils.mysql.MySQLService;
import me.refluxo.libary.bungee.utils.project.ProjectManager;
import me.refluxo.libary.bungee.utils.sync.OnlinePlayerData;
import me.refluxo.libary.threaded.AsyncThreadScheduler;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class LibaryBungee extends Plugin {
  private static Plugin instance;
  
  private static ProjectManager projectManager;
  
  private static MySQLService mySQLService;
  
  public void onEnable() {
    projectManager = new ProjectManager("LibaryBungee");
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
    CoinsAPI.setup();
    OnlinePlayerData.OnlinePlayerDataMySQL.init();
    new PluginManager(ProxyServer.getInstance()).registerListener(this, new JoinListener());
  }
  
  public void onDisable() {
    AsyncThreadScheduler.stopTasks();
  }
  
  public static Plugin getInstance() {
    return instance;
  }
  
  public static ProjectManager getProjectManager() {
    return projectManager;
  }
  
  public static MySQLService getMySQL() {
    return mySQLService;
  }
}
