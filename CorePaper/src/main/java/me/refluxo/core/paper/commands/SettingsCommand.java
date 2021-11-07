package me.refluxo.core.paper.commands;

import me.refluxo.core.paper.CorePaper;
import me.refluxo.libary.spigot.utils.inventories.InventoryBuilder;
import me.refluxo.libary.spigot.utils.inventories.ItemBuilder;
import me.refluxo.libary.spigot.utils.language.LanguageAPI;
import me.refluxo.libary.spigot.utils.player.Player;
import me.refluxo.libary.spigot.utils.player.PlayerAPI;
import me.refluxo.libary.threaded.AsyncThreadScheduler;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SettingsCommand implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        PlayerAPI playerAPI = new PlayerAPI(new Player(Objects.requireNonNull(sender.getServer().getPlayer(sender.getName())).getUniqueId().toString()));
        org.bukkit.entity.Player player = (org.bukkit.entity.Player) sender;
        player.openInventory(getInventory(playerAPI));
        return false;
    }

    private Inventory getInventory(PlayerAPI player) {
        InventoryBuilder inventoryBuilder = new InventoryBuilder(CorePaper.getProjectManager().getMessage("core.gui.settings.title.settings", player.getLanguage()));
        Map<Integer, ItemStack> items = new HashMap<>();
        if(player.getLanguage().equals(LanguageAPI.langs.DE)) {
            items.put(11, new ItemBuilder(CorePaper.getProjectManager().getMessage("core.gui.settings.items.language.de", player.getLanguage()), Material.PAPER, CorePaper.getProjectManager().getMessage("core.gui.settings.items.language.de.lore", player.getLanguage())).setAmount(1).setEnchanted(true).buildItem());
            items.put(15, new ItemBuilder(CorePaper.getProjectManager().getMessage("core.gui.settings.items.language.en", player.getLanguage()), Material.PAPER, CorePaper.getProjectManager().getMessage("core.gui.settings.items.language.en.lore", player.getLanguage())).setAmount(1).setEnchanted(false).buildItem());
        } else {
            items.put(11, new ItemBuilder(CorePaper.getProjectManager().getMessage("core.gui.settings.items.language.de", player.getLanguage()), Material.PAPER, CorePaper.getProjectManager().getMessage("core.gui.settings.items.language.de.lore", player.getLanguage())).setAmount(1).setEnchanted(false).buildItem());
            items.put(15, new ItemBuilder(CorePaper.getProjectManager().getMessage("core.gui.settings.items.language.en", player.getLanguage()), Material.PAPER, CorePaper.getProjectManager().getMessage("core.gui.settings.items.language.en.lore", player.getLanguage())).setAmount(1).setEnchanted(true).buildItem());
        }
        return inventoryBuilder.buildInventory(items);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        PlayerAPI player = new PlayerAPI(event.getWhoClicked().getUniqueId().toString());
        if(event.getView().getTitle().equalsIgnoreCase(CorePaper.getProjectManager().getMessage("core.gui.settings.title.settings", player.getLanguage()))) {
            event.setCancelled(true);
            if(event.getCurrentItem() != null) {
                if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CorePaper.getProjectManager().getMessage("core.gui.settings.items.language.de", player.getLanguage()))) {
                    player.setLanguage(LanguageAPI.langs.DE);
                    event.getWhoClicked().closeInventory();
                    new AsyncThreadScheduler(() -> event.getWhoClicked().openInventory(getInventory(player))).runAsyncTaskLater(1);
                    event.getWhoClicked().sendMessage(CorePaper.getProjectManager().getMessage("core.settings.rejoin", player.getLanguage()));
                } else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CorePaper.getProjectManager().getMessage("core.gui.settings.items.language.en", player.getLanguage()))) {
                    player.setLanguage(LanguageAPI.langs.EN);
                    event.getWhoClicked().closeInventory();
                    new AsyncThreadScheduler(() -> event.getWhoClicked().openInventory(getInventory(player))).runAsyncTaskLater(1);
                    event.getWhoClicked().sendMessage(CorePaper.getProjectManager().getMessage("core.settings.rejoin", player.getLanguage()));
                }
            }
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        if(event.getPlayer().isSneaking()) {
            event.setCancelled(true);
            event.getPlayer().openInventory(getInventory(new PlayerAPI(event.getPlayer().getUniqueId().toString())));
        }
    }

}
