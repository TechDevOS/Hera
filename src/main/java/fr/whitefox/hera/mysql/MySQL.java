package fr.whitefox.hera.mysql;

import org.bukkit.ChatColor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.bukkit.Bukkit.getServer;

public class MySQL {

    private Connection conn;

    public void connect(String host, int port, String database, String user, String password) {
        if (!isConnected()) {
            try {
                conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user, password);
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
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean isConnected() {
        try {
            if ((conn == null) || (conn.isClosed()) || conn.isValid(5)) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return conn;
    }
}
