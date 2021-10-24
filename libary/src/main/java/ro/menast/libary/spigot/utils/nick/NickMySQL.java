package ro.menast.libary.spigot.utils.nick;

import ro.menast.libary.spigot.LibarySpigot;
import ro.menast.libary.spigot.utils.mysql.MySQLService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NickMySQL {

    private static final MySQLService mySQLService = LibarySpigot.getMySQL();
    private static final Connection connection = mySQLService.getConnection();

    public static void setup() {
        try {
            PreparedStatement ps = connection.prepareStatement("CREATE DATABASE IF NOT EXISTS nick(uuid TEXT, nickName TEXT, nicked BOOLEAN)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
