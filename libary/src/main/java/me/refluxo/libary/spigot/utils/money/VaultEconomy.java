package me.refluxo.libary.spigot.utils.money;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.List;
import java.util.Objects;

public class VaultEconomy implements Economy {
  public EconomyResponse bankBalance(String arg0) {
    return null;
  }
  
  public EconomyResponse bankDeposit(String arg0, double arg1) {
    return null;
  }
  
  public EconomyResponse bankHas(String arg0, double arg1) {
    return null;
  }
  
  public EconomyResponse bankWithdraw(String arg0, double arg1) {
    return null;
  }
  
  public EconomyResponse createBank(String arg0, String arg1) {
    return null;
  }
  
  public EconomyResponse createBank(String arg0, OfflinePlayer arg1) {
    return null;
  }
  
  public boolean createPlayerAccount(String arg0) {
    return true;
  }
  
  public boolean createPlayerAccount(OfflinePlayer arg0) {
    return true;
  }
  
  public boolean createPlayerAccount(String arg0, String arg1) {
    return true;
  }
  
  public boolean createPlayerAccount(OfflinePlayer arg0, String arg1) {
    return true;
  }
  
  public String currencyNamePlural() {
    return "Dollar";
  }
  
  public String currencyNameSingular() {
    return "Dollar";
  }
  
  public EconomyResponse deleteBank(String arg0) {
    return null;
  }
  
  public EconomyResponse depositPlayer(String arg0, double arg1) {
    MoneyAPI.addMoney(Bukkit.getPlayerExact(arg0), arg1);
    return new EconomyResponse(arg1, getBalance(arg0), EconomyResponse.ResponseType.SUCCESS, "It worked!");
  }
  
  public EconomyResponse depositPlayer(OfflinePlayer arg0, double arg1) {
    MoneyAPI.addMoney(arg0, arg1);
    return new EconomyResponse(arg1, getBalance(arg0), EconomyResponse.ResponseType.SUCCESS, "It worked!");
  }
  
  public EconomyResponse depositPlayer(String arg0, String arg1, double arg2) {
    MoneyAPI.addMoney(Bukkit.getPlayerExact(arg0), arg2);
    return new EconomyResponse(arg2, getBalance(arg0), EconomyResponse.ResponseType.SUCCESS, "It worked!");
  }
  
  public EconomyResponse depositPlayer(OfflinePlayer arg0, String arg1, double arg2) {
    MoneyAPI.addMoney(arg0, arg2);
    return new EconomyResponse(arg2, getBalance(arg0), EconomyResponse.ResponseType.SUCCESS, "It worked!");
  }
  
  public String format(double arg0) {
    return String.valueOf(arg0);
  }
  
  public int fractionalDigits() {
    return 0;
  }
  
  public double getBalance(String arg0) {
    return MoneyAPI.getMoney(Objects.requireNonNull(Bukkit.getPlayerExact(arg0)));
  }
  
  public double getBalance(OfflinePlayer arg0) {
    return MoneyAPI.getMoney(arg0);
  }
  
  public double getBalance(String arg0, String arg1) {
    return MoneyAPI.getMoney(Objects.requireNonNull(Bukkit.getPlayerExact(arg0)));
  }
  
  public double getBalance(OfflinePlayer arg0, String arg1) {
    return MoneyAPI.getMoney(arg0);
  }
  
  public List<String> getBanks() {
    return null;
  }
  
  public String getName() {
    return "Libary";
  }
  
  public boolean has(String arg0, double arg1) {
    return (MoneyAPI.getMoney(Objects.requireNonNull(Bukkit.getPlayerExact(arg0))) >= arg1);
  }
  
  public boolean has(OfflinePlayer arg0, double arg1) {
    return (MoneyAPI.getMoney(arg0) >= arg1);
  }
  
  public boolean has(String arg0, String arg1, double arg2) {
    return (MoneyAPI.getMoney(Objects.requireNonNull(Bukkit.getPlayerExact(arg0))) >= arg2);
  }
  
  public boolean has(OfflinePlayer arg0, String arg1, double arg2) {
    return (MoneyAPI.getMoney(arg0) >= arg2);
  }
  
  public boolean hasAccount(String arg0) {
    return Objects.requireNonNull(Bukkit.getPlayerExact(arg0)).hasPlayedBefore();
  }
  
  public boolean hasAccount(OfflinePlayer arg0) {
    return arg0.hasPlayedBefore();
  }
  
  public boolean hasAccount(String arg0, String arg1) {
    return Objects.requireNonNull(Bukkit.getPlayerExact(arg0)).hasPlayedBefore();
  }
  
  public boolean hasAccount(OfflinePlayer arg0, String arg1) {
    return arg0.hasPlayedBefore();
  }
  
  public boolean hasBankSupport() {
    return false;
  }
  
  public EconomyResponse isBankMember(String arg0, String arg1) {
    return null;
  }
  
  public EconomyResponse isBankMember(String arg0, OfflinePlayer arg1) {
    return null;
  }
  
  public EconomyResponse isBankOwner(String arg0, String arg1) {
    return null;
  }
  
  public EconomyResponse isBankOwner(String arg0, OfflinePlayer arg1) {
    return null;
  }
  
  public boolean isEnabled() {
    return true;
  }
  
  public EconomyResponse withdrawPlayer(String arg0, double arg1) {
    MoneyAPI.removeMoney(Bukkit.getPlayerExact(arg0), arg1);
    return new EconomyResponse(arg1, getBalance(arg0), EconomyResponse.ResponseType.SUCCESS, "It worked!");
  }
  
  public EconomyResponse withdrawPlayer(OfflinePlayer arg0, double arg1) {
    MoneyAPI.removeMoney(arg0, arg1);
    return new EconomyResponse(arg1, getBalance(arg0), EconomyResponse.ResponseType.SUCCESS, "It worked!");
  }
  
  public EconomyResponse withdrawPlayer(String arg0, String arg1, double arg2) {
    MoneyAPI.removeMoney(Bukkit.getPlayerExact(arg0), arg2);
    return new EconomyResponse(arg2, getBalance(arg0), EconomyResponse.ResponseType.SUCCESS, "It worked!");
  }
  
  public EconomyResponse withdrawPlayer(OfflinePlayer arg0, String arg1, double arg2) {
    MoneyAPI.removeMoney(arg0, arg2);
    return new EconomyResponse(arg2, getBalance(arg0), EconomyResponse.ResponseType.SUCCESS, "It worked!");
  }
}
