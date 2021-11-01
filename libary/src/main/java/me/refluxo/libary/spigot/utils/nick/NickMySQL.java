package me.refluxo.libary.spigot.utils.nick;

import me.refluxo.libary.spigot.LibarySpigot;
import me.refluxo.libary.spigot.utils.mysql.MySQLService;

import java.sql.Connection;

public class NickMySQL {

    private static final MySQLService mySQLService = LibarySpigot.getMySQL();
    private static final Connection connection = mySQLService.getConnection();

    public static void setup() {
        mySQLService.executeUpdate("CREATE DATABASE IF NOT EXISTS nick(uuid TEXT, nickName TEXT, nicked BOOLEAN)");
    }

}
