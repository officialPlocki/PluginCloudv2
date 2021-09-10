package ro.menast.libary.spigot.utils.player;

import org.bukkit.entity.Player;
import ro.menast.libary.spigot.utils.language.LanguageAPI;
import ro.menast.libary.spigot.utils.permissions.AstropermsPlayerAPI;
import ro.menast.libary.spigot.utils.permissions.Group;

public class PlayerAPI {

    private final AstropermsPlayerAPI astropermsPlayerAPI;

    public PlayerAPI(Player player) {
        this.astropermsPlayerAPI = new AstropermsPlayerAPI(player);
    }

    public PlayerAPI(String uuid) {
        this.astropermsPlayerAPI = new AstropermsPlayerAPI(uuid);
    }

    public boolean hasPermission(String permission) {
        return this.astropermsPlayerAPI.hasPermission(permission);
    }

    public void addPermission(String permission) {
        this.astropermsPlayerAPI.addPermission(permission);
    }

    public void removePermission(String permission) {
        this.astropermsPlayerAPI.removePermission(permission);
    }

    public Group getGroup() {
        return this.astropermsPlayerAPI.getGroupOfPlayer();
    }

    public LanguageAPI.langs getLanguage() {
        return null;
    }
}
