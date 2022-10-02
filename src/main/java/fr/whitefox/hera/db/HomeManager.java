package fr.whitefox.hera.db;

import fr.whitefox.hera.Main;
import org.bukkit.ChatColor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public class HomeManager {

    public static void set(UUID playerUUID, String homeName, String coordinates) {
        try {
            PreparedStatement sts = Main.getInstance().sqlite.getConnection().prepareStatement("INSERT INTO homes (player_uuid, home_name, coordinates) VALUES (?, ?, ?)");
            sts.setString(1, playerUUID.toString());
            sts.setString(2, homeName);
            sts.setString(3, coordinates);
            sts.executeUpdate();

            getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "[Hera DB] New Home of : " + playerUUID + "," + homeName + "," + coordinates);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(UUID playerUUID, String homeName) {
        try {
            PreparedStatement sts = Main.getInstance().sqlite.getConnection().prepareStatement("DELETE FROM homes WHERE (player_uuid=? AND home_name=?)");
            sts.setString(1, playerUUID.toString());
            sts.setString(2, homeName);
            sts.executeUpdate();

            getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "[Hera DB] Delete home info of " + playerUUID + "," + homeName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean exist(UUID playerUUID, String homeName) {
        try {
            PreparedStatement sts = Main.getInstance().sqlite.getConnection().prepareStatement("SELECT * FROM homes WHERE (player_uuid=? AND home_name=?)");
            sts.setString(1, playerUUID.toString());
            sts.setString(2, homeName);
            ResultSet rs = sts.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
