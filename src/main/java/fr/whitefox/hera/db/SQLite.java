package fr.whitefox.hera.db;

import org.bukkit.ChatColor;

import java.sql.*;

import static org.bukkit.Bukkit.getServer;

public class SQLite {

    private Connection conn;

    public void connect(String db) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");

        if (!isConnected()) {
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:" + db);
                getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "[Hera DB] Connexion établie avec la bdd");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void disconnect() {
        if (isConnected()) {
            try {
                conn.close();
                getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "[Hera DB] Connexion terminée avec la bdd");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean isConnected() {
        try {
            return (conn != null) && (!conn.isClosed());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public void initTables() throws SQLException {
        try{
            Connection conn = getConnection();
            Statement sts = conn.createStatement();
            sts.execute("CREATE TABLE  IF NOT EXISTS player_infos(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "player_uuid VARCHAR(255)," +
                    "player_name VARCHAR(255)," +
                    "ip_address VARCHAR(255)," +
                    "last_connection BIGINT)");
            sts.execute("CREATE TABLE  IF NOT EXISTS bans(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "player_uuid VARCHAR(255)," +
                    "reason VARCHAR(255)," +
                    "end BIGINT)");
            sts.execute("CREATE TABLE  IF NOT EXISTS mutes(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "player_uuid VARCHAR(255)," +
                    "reason VARCHAR(255)," +
                    "end BIGINT)");
            sts.execute("CREATE TABLE  IF NOT EXISTS mod_history(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "player_uuid VARCHAR(255)," +
                    "reason VARCHAR(255)," +
                    "duration INTEGER," +
                    "moderator VARCHAR(255)," +
                    "time BIGINT," +
                    "type VARCHAR(255))");
            sts.execute("CREATE TABLE  IF NOT EXISTS back(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "player_uuid VARCHAR(255)," +
                    "coordinates VARCHAR(255))");

            sts.close();
            getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "[Hera DB] Tables update");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

}
