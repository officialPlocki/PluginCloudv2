package me.refluxo.libary.spigot.utils;

import org.bukkit.Bukkit;

public class Version {
  public static String getVersion() {
    if (Bukkit.getVersion().contains("16"))
      return "1.16"; 
    return "1.8.8";
  }
}
