package me.refluxo.bansystem.utils.ban;

import me.refluxo.libary.spigot.LibarySpigot;
import me.refluxo.libary.spigot.utils.mysql.MySQLService;
import me.refluxo.libary.spigot.utils.player.Player;

import java.sql.*;
import java.util.Date;
import java.util.UUID;

public class BanMySQL {

    private static final MySQLService mysql = LibarySpigot.getMySQL();
    private static final Connection con = mysql.getConnection();

    public static void init() {
        try {
            PreparedStatement ps = con.prepareStatement("CREATE TABLE IF NOT EXISTS tempbans(bannedUUID TEXT, fromUUID TEXT, now BIGINT, later BIGINT, reason TEXT, banID TEXT)");
            mysql.executeUpdate(ps);
            PreparedStatement ps1 = con.prepareStatement("CREATE TABLE IF NOT EXISTS bans(uuid TEXT, amount INT)");
            mysql.executeUpdate(ps1);
            PreparedStatement ps2 = con.prepareStatement("CREATE TABLE IF NOT EXISTS permbans(bannedUUID TEXT, fromUUID TEXT, bannedAt BIGINT, reason TEXT, canUnbanned BOOLEAN, banID TEXT)");
            mysql.executeUpdate(ps2);
            PreparedStatement ps3 = con.prepareStatement("CREATE TABLE IF NOT EXISTS history(uuid TEXT, banID TEXT, reason TEXT)");
            mysql.executeUpdate(ps3);
            PreparedStatement ps4 = con.prepareStatement("CREATE TABLE IF NOT EXISTS replayIDs(banID TEXT, replayID TEXT)");
            mysql.executeUpdate(ps4);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static IBan createBan(String replayID, Player toBan, Player from, boolean isPerma, String reason) {
        UUID banid = UUID.randomUUID();
        int amount = getBanAmount(toBan.getUniqueId());
        if(amount >= 5) {
            insertPermBan(toBan.getUniqueId(), from.getUniqueId(), System.currentTimeMillis(), reason, false, banid.toString());
        } else {
            if(isPerma) {
                insertPermBan(toBan.getUniqueId(), from.getUniqueId(), System.currentTimeMillis(), reason, true, banid.toString());
            } else {
                insertTempBan(toBan.getUniqueId(), from.getUniqueId(), System.currentTimeMillis(), System.currentTimeMillis()+(1728000L*amount), reason, banid.toString());
            }
        }
        pullReplayID(banid.toString(), replayID);
        addBanToHistory(banid.toString());
        return getBanInfo(banid.toString());
    }

    private static Date timestampToDate(long l) {
        return new Date(new Timestamp(l).getTime());
    }

    private static int getBanAmount(String uuid) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT amount FROM bans WHERE uuid = '"+uuid+"'");
            ResultSet rs = mysql.getResult(ps);
            if(rs.next()) {
                return rs.getInt("amount");
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean isBanned(String uuid) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tempbans WHERE bannedUUID = '"+uuid+"'");
            ResultSet rs = mysql.getResult(ps);
            if(rs.next()) {
                return true;
            } else {
                PreparedStatement ps1 = con.prepareStatement("SELECT * FROM permbans WHERE bannedUUID = '"+uuid+"'");
                ResultSet rs1 = mysql.getResult(ps1);
                return rs1.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getBanIDByUUID(String uuid) {
        if(isPermBan(uuid)) {
            try {
                PreparedStatement ps = con.prepareStatement("SELECT banID from permbans WHERE bannedUUID = '"+uuid+"'");
                ResultSet rs = mysql.getResult(ps);
                if(rs.next()) {
                    return rs.getString("banID");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                PreparedStatement ps = con.prepareStatement("SELECT banID from tempbans WHERE bannedUUID = '"+uuid+"'");
                ResultSet rs = mysql.getResult(ps);
                if(rs.next()) {
                    return rs.getString("banID");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void unBan(String banID) {
        IBan ban = getBanInfo(banID);
        if(ban.isPerma()) {
            if(ban.isUnbannable()) {
                try {
                    PreparedStatement ps = con.prepareStatement("DELETE FROM permbans WHERE banID = '"+banID+"'");
                    mysql.executeUpdate(ps);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                PreparedStatement ps = con.prepareStatement("DELETE FROM tempbans WHERE banID = '"+banID+"'");
                mysql.executeUpdate(ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public static IBan getBanInfo(String banID) {
        return new BanBuilder(getReplayID(banID),isUnbannable(banID),getBanner(banID),new Player(getUniqueIdFromBanned(banID)),getBanDate(banID),getUnBanDate(banID),isPermBan(getUniqueIdFromBanned(banID)),banID);
    }

    private static Date getUnBanDate(String banID) {
        if(isBanned(getUniqueIdFromBanned(banID))) {
            if(isPermBan(getUniqueIdFromBanned(banID))) {
                return null;
            } else {
                try {
                    PreparedStatement perm = con.prepareStatement("SELECT later FROM tempbans WHERE banID = '"+banID+"'");
                    ResultSet prs = mysql.getResult(perm);
                    if(prs.next()) {
                        return timestampToDate(prs.getLong("later"));
                    } else {
                        return null;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private static Date getBanDate(String banID) {
        if(isBanned(getUniqueIdFromBanned(banID))) {
            if(isPermBan(getUniqueIdFromBanned(banID))) {
                try {
                    PreparedStatement perm = con.prepareStatement("SELECT bannedAt FROM permbans WHERE banID = '"+banID+"'");
                    ResultSet prs = mysql.getResult(perm);
                    if(prs.next()) {
                        return timestampToDate(prs.getLong("bannedAt"));
                    } else {
                        return null;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    PreparedStatement perm = con.prepareStatement("SELECT now FROM tempbans WHERE banID = '"+banID+"'");
                    ResultSet prs = mysql.getResult(perm);
                    if(prs.next()) {
                        return timestampToDate(prs.getLong("now"));
                    } else {
                        return null;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private static Player getBanner(String banID) {
        if(isBanned(getUniqueIdFromBanned(banID))) {
            if(isPermBan(getUniqueIdFromBanned(banID))) {
                try {
                    PreparedStatement ps = con.prepareStatement("SELECT fromUUID FROM permbans WHERE banID = '"+banID+"'");
                    ResultSet rs = mysql.getResult(ps);
                    if(rs.next()) {
                        return new Player(rs.getString("fromUUID"));
                    } else {
                        return null;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    PreparedStatement ps = con.prepareStatement("SELECT fromUUID FROM tempbans WHERE banID = '"+banID+"'");
                    ResultSet rs = mysql.getResult(ps);
                    if(rs.next()) {
                        return new Player(rs.getString("fromUUID"));
                    } else {
                        return null;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private static String getReplayID(String banID) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT replayID FROM replayids WHERE banID = '"+banID+"'");
            ResultSet rs = mysql.getResult(ps);
            if(rs.next()) {
                return rs.getString("replayID");
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void pullReplayID(String banID, String replayID) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO replayids(banID, replayID) VALUES (?,?)");
            ps.setString(1, banID);
            ps.setString(2, replayID);
            mysql.executeUpdate(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean isUnbannable(String banID) {
        if(isBanned(getUniqueIdFromBanned(banID))) {
            if (isPermBan(getUniqueIdFromBanned(banID))) {
                try {
                    PreparedStatement ps = con.prepareStatement("SELECT canUnbanned FROM permbans WHERE banID = '"+banID+"'");
                    ResultSet rs = mysql.getResult(ps);
                    if(rs.next()) {
                        return rs.getBoolean("canUnbanned");
                    } else {
                        return true;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    private static String getUniqueIdFromBanned(String banID) {
        try {
            PreparedStatement perm = con.prepareStatement("SELECT bannedUUID FROM permbans WHERE banID = '"+banID+"'");
            ResultSet prs = mysql.getResult(perm);
            if(prs.next()) {
                return prs.getString("bannedUUID");
            } else {
                PreparedStatement ps = con.prepareStatement("SELECT bannedUUID FROM tempbans WHERE banID = '"+banID+"'");
                ResultSet rs = mysql.getResult(ps);
                if(rs.next()) {
                    return rs.getString("bannedUUID");
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getReason(String banID) {
        if(isBanned(getUniqueIdFromBanned(banID))) {
            if(isPermBan(getUniqueIdFromBanned(banID))) {
                try {
                    PreparedStatement perm = con.prepareStatement("SELECT reason FROM permbans WHERE banID = '"+banID+"'");
                    ResultSet prs = mysql.getResult(perm);
                    if(prs.next()) {
                        return prs.getString("reason");
                    } else {
                        return null;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    PreparedStatement perm = con.prepareStatement("SELECT reason FROM tempbans WHERE banID = '"+banID+"'");
                    ResultSet prs = mysql.getResult(perm);
                    if(prs.next()) {
                        return prs.getString("reason");
                    } else {
                        return null;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private static void addBanToHistory(String banID) {
        try {
            int amount = 0;
            PreparedStatement amp = con.prepareStatement("SELECT amount FROM bans WHERE uuid = ?");
            amp.setString(1, getUniqueIdFromBanned(banID));
            ResultSet am = mysql.getResult(amp);
            if(am.next()) {
                amount = am.getInt("amount");
            }
            PreparedStatement ps = con.prepareStatement("UPDATE bans SET amount = ? WHERE uuid = ?");
            ps.setInt(1, amount);
            ps.setString(2, getUniqueIdFromBanned(banID));
            mysql.executeUpdate(ps);

            PreparedStatement ps1 = con.prepareStatement("INSERT INTO history(uuid, banID, reason) VALUES (?,?,?)");
            ps1.setString(1, getUniqueIdFromBanned(banID));
            ps1.setString(2, banID);
            ps1.setString(3, getReason(banID));
            mysql.executeUpdate(ps1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean isPermBan(String uuid) {
        if(isBanned(uuid)) {
            try {
                PreparedStatement ps = con.prepareStatement("SELECT * FROM tempbans WHERE bannedUUID = '"+uuid+"'");
                ResultSet rs = mysql.getResult(ps);
                return !rs.next();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private static void insertPermBan(String bannedUUID, String fromUUID, long now, String reason, boolean canUnbanned, String banid) {
        if(!isBanned(bannedUUID)) {
            try {
                PreparedStatement ps = con.prepareStatement("INSERT INTO permbans(bannedUUID, fromUUID, bannedAt, canUnbanned, banID, reason) VALUES (?,?,?,?,?,?)");
                ps.setString(1, bannedUUID);
                ps.setString(2, fromUUID);
                ps.setLong(3, now);
                ps.setBoolean(4, canUnbanned);
                ps.setString(5, banid);
                ps.setString(6, reason);
                mysql.executeUpdate(ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void insertTempBan(String bannedUUID, String fromUUID, long now, long later, String reason, String banid) {
        if(!isBanned(bannedUUID)) {
            try {
                PreparedStatement ps = con.prepareStatement("INSERT INTO tempbans(bannedUUID, fromUUID, now, later, reason, banID) VALUES (?,?,?,?,?,?)");
                ps.setString(1, bannedUUID);
                ps.setString(2, fromUUID);
                ps.setLong(3, now);
                ps.setLong(4, later);
                ps.setString(5, reason);
                ps.setString(6, banid);
                mysql.executeUpdate(ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
