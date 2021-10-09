package ro.menast.libary.bungee.utils.money;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.config.Configuration;
import ro.menast.libary.bungee.utils.filebuilder.FileBuilder;

import java.text.DecimalFormat;

public class BitcoinAPI {
  private static FileBuilder fileBuilder;
  
  private static Configuration yml;
  
  public static void setup() {
    fileBuilder = new FileBuilder("coins.data");
    yml = fileBuilder.getYaml();
  }
  
  public static double getBitcoins(ProxiedPlayer p) {
    if (yml.get(p.getUniqueId().toString()) == null)
      return yml.getDouble(p.getUniqueId().toString()); 
    yml.set(p.getUniqueId().toString(), Double.valueOf(0.0D));
    fileBuilder.save();
    return 0.0D;
  }
  
  public static String getVisualBitcoins(ProxiedPlayer p) {
    DecimalFormat df = new DecimalFormat("#,###.##");
    if (yml.get(p.getUniqueId().toString()) == null) {
      double a = yml.getDouble(p.getUniqueId().toString());
      return df.format(a);
    } 
    yml.set(p.getUniqueId().toString(), Double.valueOf(0.0D));
    fileBuilder.save();
    return df.format(0.0D);
  }
  
  public static void setBitcoins(ProxiedPlayer p, double amount) {
    yml.set(p.getUniqueId().toString(), Double.valueOf(amount));
    fileBuilder.save();
  }
  
  public static void addBitcoins(ProxiedPlayer p, double amount) {
    setBitcoins(p, getBitcoins(p) + amount);
  }
  
  public static void removeBitcoins(ProxiedPlayer p, double amount) {
    setBitcoins(p, getBitcoins(p) - amount);
  }
  
  public static void resetBitcoins(ProxiedPlayer p) {
    yml.set(p.getUniqueId().toString(), Integer.valueOf(0));
    fileBuilder.save();
  }
  
  public static boolean hasBitcoins(ProxiedPlayer p, double amount) {
      return getBitcoins(p) == amount || getBitcoins(p) >= amount;
  }
}
