package me.refluxo.libary.spigot.utils.money;

import me.refluxo.libary.spigot.utils.filebuilder.FileBuilder;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

public class MoneyAPI {

  private static FileBuilder fileBuilder;
  private static YamlConfiguration yml;

  public static void setup() {
    fileBuilder = new FileBuilder("coins.data");
    yml = fileBuilder.getYaml();
  }

  public static double getMoney(Player p) {
    if (yml.isSet(p.getUniqueId().toString()))
      return yml.getDouble(p.getUniqueId().toString());
    yml.set(p.getUniqueId().toString(), 0.0D);
    fileBuilder.save();
    return 0.0D;
  }

  public static String getVisualMoney(Player p) {
    DecimalFormat df = new DecimalFormat("#,###.##");
    if (yml.isSet(p.getUniqueId().toString())) {
      double a = yml.getDouble(p.getUniqueId().toString());
      return df.format(a);
    } 
    yml.set(p.getUniqueId().toString(), 0.0D);
    fileBuilder.save();
    return df.format(0.0D);
  }
  
  public static void setMoney(Player p, double amount) {
    yml.set(p.getUniqueId().toString(), amount);
    fileBuilder.save();
  }
  
  public static void addMoney(Player p, double amount) {
    setMoney(p, getMoney(p) + amount);
  }

  public static void removeMoney(Player p, double amount) {
    setMoney(p, getMoney(p) - amount);
  }
  
  public static void resetMoney(Player p) {
    yml.set(p.getUniqueId().toString(), 0);
    fileBuilder.save();
  }
  
  public static boolean hasMoney(Player p, double amount) {
      return getMoney(p) == amount || getMoney(p) >= amount;
  }
  
  public static double getMoney(OfflinePlayer p) {
    if (yml.isSet(p.getUniqueId().toString()))
      return yml.getDouble(p.getUniqueId().toString()); 
    yml.set(p.getUniqueId().toString(), 0.0D);
    fileBuilder.save();
    return 0.0D;
  }
  
  public static String getVisualMoney(OfflinePlayer p) {
    DecimalFormat df = new DecimalFormat("#,###.##");
    if (yml.isSet(p.getUniqueId().toString())) {
      double a = yml.getDouble(p.getUniqueId().toString());
      return df.format(a);
    } 
    yml.set(p.getUniqueId().toString(), 0.0D);
    fileBuilder.save();
    return df.format(0.0D);
  }
  
  public static void setMoney(OfflinePlayer p, double amount) {
    yml.set(p.getUniqueId().toString(), amount);
    fileBuilder.save();
  }
  
  public static void resetMoney(OfflinePlayer p) {
    yml.set(p.getUniqueId().toString(), 0);
    fileBuilder.save();
  }
  
  public static void addMoney(OfflinePlayer p, double amount) {
    setMoney(p, getMoney(p) + amount);
  }
  
  public static void removeMoney(OfflinePlayer p, double amount) {
    setMoney(p, getMoney(p) - amount);
  }
  
  public static boolean hasMoney(OfflinePlayer p, double amount) {
      return getMoney(p) == amount || getMoney(p) >= amount;
  }
}
