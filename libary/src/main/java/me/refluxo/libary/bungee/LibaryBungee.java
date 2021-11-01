package me.refluxo.libary.bungee;

import me.refluxo.libary.bungee.listener.JoinListener;
import me.refluxo.libary.bungee.utils.filebuilder.FileBuilder;
import me.refluxo.libary.bungee.utils.money.CoinsAPI;
import me.refluxo.libary.bungee.utils.mysql.MySQLService;
import me.refluxo.libary.bungee.utils.project.ProjectManager;
import me.refluxo.libary.bungee.utils.sync.OnlinePlayerData;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import org.bukkit.Bukkit;

public class LibaryBungee extends Plugin {
  private static Plugin instance;
  
  private static ProjectManager projectManager;
  
  private static MySQLService mySQLService;
  
  public void onEnable() {
    projectManager = new ProjectManager("LibaryBungee");
    instance = this;
    Bukkit.getConsoleSender().sendMessage("___  ___              _      _  _                          \n|  \\/  |             | |    (_)| |                         \n| .  . |  ___  _ __  | |     _ | |__    __ _  _ __  _   _  \n| |\\/| | / _ \\| '_ \\ | |    | || '_ \\  / _` || '__|| | | | \n| |  | ||  __/| | | || |____| || |_) || (_| || |   | |_| | \n\\_|  |_/ \\___||_| |_|\\_____/|_||_.__/  \\__,_||_|    \\__, | \n                                                     __/ | \n                                                    |___/  \n                                                           ");
    Bukkit.getConsoleSender().sendMessage("  __               ______                                  \n / _|              | ___ \\                                 \n| |_   ___   _ __  | |_/ / _   _  _ __    __ _   ___   ___ \n|  _| / _ \\ | '__| | ___ \\| | | || '_ \\  / _` | / _ \\ / _ \\\n| |  | (_) || |    | |_/ /| |_| || | | || (_| ||  __/|  __/\n|_|   \\___/ |_|    \\____/  \\__,_||_| |_| \\__, | \\___| \\___|\n                                          __/ |            \n                                         |___/             ");
    FileBuilder fb = new FileBuilder("plugins/libary/config.yml");
    Configuration conf = fb.getYaml();
    if (conf.get("mysql.host") == null || conf.get("mysql.host") == "") {
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
    BungeeCord.getInstance().getPluginManager().registerListener(this, new JoinListener());
  }
  
  public void onDisable() {}
  
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
