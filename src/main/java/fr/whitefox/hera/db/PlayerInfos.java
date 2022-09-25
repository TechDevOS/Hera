package fr.whitefox.hera.db;

import fr.whitefox.hera.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public class PlayerInfos {

    public void update(Player player) {
        try {
            PreparedStatement sts = Main.getInstance().sqlite.getConnection().prepareStatement("SELECT player_name FROM player_infos WHERE player_uuid=?");
            sts.setString(1, player.getUniqueId().toString());
            ResultSet rs = sts.executeQuery();

            if (rs.next()) {
                PreparedStatement update = Main.getInstance().sqlite.getConnection().prepareStatement("UPDATE player_infos SET player_name=? WHERE player_uuid=?");
                update.setString(1, player.getName());
                update.setString(2, player.getUniqueId().toString());
                update.executeUpdate();
                update.close();

                getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "[Hera DB] Update : " + player.getName() + " , " + player.getUniqueId().toString());
            } else {
                PreparedStatement insert = Main.getInstance().sqlite.getConnection().prepareStatement("INSERT INTO player_infos (player_uuid, player_name) VALUES (?, ?)");
                insert.setString(1, player.getUniqueId().toString());
                insert.setString(2, player.getName());
                insert.executeUpdate();
                insert.close();

                getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "[Hera DB] Insertion : " + player.getName() + "," + player.getUniqueId().toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setIPAddress(Player player) {
        try {
            PreparedStatement sts = Main.getInstance().sqlite.getConnection().prepareStatement("SELECT ip_address FROM player_infos WHERE player_uuid=?");
            sts.setString(1, player.getUniqueId().toString());
            ResultSet rs = sts.executeQuery();

            if (rs.next()) {
                PreparedStatement update = Main.getInstance().sqlite.getConnection().prepareStatement("UPDATE player_infos SET ip_address=? WHERE player_uuid=?");
                update.setString(1, player.getAddress().toString().substring(1).split(":")[0]);
                update.setString(2, player.getUniqueId().toString());
                update.executeUpdate();
                update.close();

                getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "[Hera DB] Update IP address of " + player.getUniqueId().toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getIPAddress(UUID playerUUID) {
        try {
            PreparedStatement sts = Main.getInstance().sqlite.getConnection().prepareStatement("SELECT ip_address FROM player_infos WHERE player_uuid=?");
            sts.setString(1, playerUUID.toString());
            ResultSet rs = sts.executeQuery();

            if (rs.next()) {
                return rs.getString("ip_address");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new NullPointerException("Le joueur n'a pas d'informations dans la table");
    }

    public void setLastConnexion(Player player) {
        try {
            PreparedStatement sts = Main.getInstance().sqlite.getConnection().prepareStatement("SELECT last_connection FROM player_infos WHERE player_uuid=?");
            sts.setString(1, player.getUniqueId().toString());
            ResultSet rs = sts.executeQuery();

            if (rs.next()) {
                PreparedStatement update = Main.getInstance().sqlite.getConnection().prepareStatement("UPDATE player_infos SET last_connection=? WHERE player_uuid=?");
                update.setLong(1, System.currentTimeMillis());
                update.setString(2, player.getUniqueId().toString());
                update.executeUpdate();
                update.close();

                getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "[Hera DB] Update last connection of " + player.getUniqueId().toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public long getLastConnexion(UUID playerUUID) {
        try {
            PreparedStatement sts = Main.getInstance().sqlite.getConnection().prepareStatement("SELECT last_connection FROM player_infos WHERE player_uuid=?");
            sts.setString(1, playerUUID.toString());
            ResultSet rs = sts.executeQuery();

            if (rs.next()) {
                return rs.getLong("last_connection");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new NullPointerException("Le joueur n'a pas d'informations dans la table");
    }

    public static boolean exist(String playerName) {
        try {
            PreparedStatement sts = Main.getInstance().sqlite.getConnection().prepareStatement("SELECT * FROM player_infos WHERE player_name=?");
            sts.setString(1, playerName);
            ResultSet rs = sts.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static UUID getUUID(String playerName) {
        try {
            PreparedStatement sts = Main.getInstance().sqlite.getConnection().prepareStatement("SELECT player_uuid FROM player_infos WHERE player_name=?");
            sts.setString(1, playerName);
            ResultSet rs = sts.executeQuery();

            if (rs.next()) {
                return UUID.fromString(rs.getString("player_uuid"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new NullPointerException("Le joueur n'a pas d'informations dans la table");
    }

}

