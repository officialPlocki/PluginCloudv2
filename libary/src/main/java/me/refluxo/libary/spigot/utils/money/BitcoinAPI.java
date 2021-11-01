package ro.menast.libary.spigot.utils.money;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import ro.menast.libary.spigot.utils.filebuilder.FileBuilder;

import java.text.DecimalFormat;

public class BitcoinAPI {

  private static FileBuilder fileBuilder;
  private static YamlConfiguration yml;

  public static void setup() {
    fileBuilder = new FileBuilder("coins.data");
    yml = fileBuilder.getYaml();
  }

  public static double getBitcoins(Player p) {
    if (yml.isSet(p.getUniqueId().toString()))
      return yml.getDouble(p.getUniqueId().toString());
    yml.set(p.getUniqueId().toString(), 0.0D);
    fileBuilder.save();
    return 0.0D;
  }

  public static String getVisualBitcoins(Player p) {
    DecimalFormat df = new DecimalFormat("#,###.##");
    if (yml.isSet(p.getUniqueId().toString())) {
      double a = yml.getDouble(p.getUniqueId().toString());
      return df.format(a);
    } 
    yml.set(p.getUniqueId().toString(), 0.0D);
    fileBuilder.save();
    return df.format(0.0D);
  }
  
  public static void setBitcoins(Player p, double amount) {
    yml.set(p.getUniqueId().toString(), amount);
    fileBuilder.save();
  }
  
  public static void addBitcoins(Player p, double amount) {
    setBitcoins(p, getBitcoins(p) + amount);
  }

  public static void removeBitcoins(Player p, double amount) {
    setBitcoins(p, getBitcoins(p) - amount);
  }
  
  public static void resetBitcoins(Player p) {
    yml.set(p.getUniqueId().toString(), 0);
    fileBuilder.save();
  }
  
  public static boolean hasBitcoins(Player p, double amount) {
      return getBitcoins(p) == amount || getBitcoins(p) >= amount;
  }
  
  public static double getBitcoins(OfflinePlayer p) {
    if (yml.isSet(p.getUniqueId().toString()))
      return yml.getDouble(p.getUniqueId().toString()); 
    yml.set(p.getUniqueId().toString(), 0.0D);
    fileBuilder.save();
    return 0.0D;
  }
  
  public static String getVisualBitcoins(OfflinePlayer p) {
    DecimalFormat df = new DecimalFormat("#,###.##");
    if (yml.isSet(p.getUniqueId().toString())) {
      double a = yml.getDouble(p.getUniqueId().toString());
      return df.format(a);
    } 
    yml.set(p.getUniqueId().toString(), 0.0D);
    fileBuilder.save();
    return df.format(0.0D);
  }
  
  public static void setBitcoins(OfflinePlayer p, double amount) {
    yml.set(p.getUniqueId().toString(), amount);
    fileBuilder.save();
  }
  
  public static void resetBitcoins(OfflinePlayer p) {
    yml.set(p.getUniqueId().toString(), 0);
    fileBuilder.save();
  }
  
  public static void addBitcoins(OfflinePlayer p, double amount) {
    setBitcoins(p, getBitcoins(p) + amount);
  }
  
  public static void removeBitcoins(OfflinePlayer p, double amount) {
    setBitcoins(p, getBitcoins(p) - amount);
  }
  
  public static boolean hasBitcoins(OfflinePlayer p, double amount) {
      return getBitcoins(p) == amount || getBitcoins(p) >= amount;
  }
}
