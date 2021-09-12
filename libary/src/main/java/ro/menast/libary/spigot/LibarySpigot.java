package ro.menast.libary.spigot;

import me.tigerhix.lib.bossbar.BossbarLib;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import ro.menast.libary.bungee.utils.mysql.MySQLService;
import ro.menast.libary.spigot.apis.EventAPI;
import ro.menast.libary.spigot.utils.filebuilder.FileBuilder;
import ro.menast.libary.spigot.utils.permissions.AstropermsMySQL;
import ro.menast.libary.spigot.utils.project.ProjectManager;
import ro.menast.libary.spigot.utils.sync.OnlinePlayerData;

public final class LibarySpigot extends JavaPlugin {
  private static ProjectManager projectManager;
  
  private static JavaPlugin instance;
  
  private static MySQLService mySQLService;
  
  public void onEnable() {
    projectManager = new ProjectManager("LibarySpigot");
    Bukkit.getConsoleSender().sendMessage("___  ___              _      _  _                          \n|  \\/  |             | |    (_)| |                         \n| .  . |  ___  _ __  | |     _ | |__    __ _  _ __  _   _  \n| |\\/| | / _ \\| '_ \\ | |    | || '_ \\  / _` || '__|| | | | \n| |  | ||  __/| | | || |____| || |_) || (_| || |   | |_| | \n\\_|  |_/ \\___||_| |_|\\_____/|_||_.__/  \\__,_||_|    \\__, | \n                                                     __/ | \n                                                    |___/  \n                                                           ");
    Bukkit.getConsoleSender().sendMessage("  __                _____         _                _       \n / _|              /  ___|       (_)              | |      \n| |_   ___   _ __  \\ `--.  _ __   _   __ _   ___  | |_     \n|  _| / _ \\ | '__|  `--. \\| '_ \\ | | / _` | / _ \\ | __|    \n| |  | (_) || |    /\\__/ /| |_) || || (_| || (_) || |_     \n|_|   \\___/ |_|    \\____/ | .__/ |_| \\__, | \\___/  \\__|    \n                          | |         __/ |                \n                          |_|        |___/                 \n                                                           ");
    instance = this;
    FileBuilder fb = new FileBuilder("plugins/libary/config.yml");
    YamlConfiguration conf = fb.getYaml();
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
    AstropermsMySQL.setup();
    BossbarLib.setHandler();
    OnlinePlayerData.OnlinePlayerDataMySQL.init();
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
