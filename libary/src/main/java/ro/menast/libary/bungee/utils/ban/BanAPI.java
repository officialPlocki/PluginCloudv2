package ro.menast.libary.bungee.utils.ban;

import ro.menast.libary.bungee.utils.player.Player;

public class BanAPI {

    public BanAPI() {}

    public void ban(String replayID, Player toBan, Player bannedFrom, Boolean permanent, String reason) {
        BanMySQL.createBan(replayID, toBan, bannedFrom, permanent, reason);
    }

    public IBan getBanInfo(String banID) {
        return BanMySQL.getBanInfo(banID);
    }

    public boolean isBanned(Player check) {
        return BanMySQL.isBanned(check.getUniqueId());
    } 

}
