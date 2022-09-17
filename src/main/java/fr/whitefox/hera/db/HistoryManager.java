package fr.whitefox.hera.db;

import fr.whitefox.hera.Main;
import org.bukkit.ChatColor;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public class HistoryManager {

    public void banRegister(UUID uuid, long duration, String reason, String moderator) {

        if (!(moderator.equalsIgnoreCase("CONSOLE")))
            moderator = Main.getInstance().playerInfos.getUUID(moderator).toString();
        try {
            PreparedStatement sts = Main.getInstance().sqlite.getConnection().prepareStatement("INSERT INTO mod_history (player_uuid, duration, reason, moderator, time, type) VALUES (?, ?, ?, ?, ?, ?)");
            sts.setString(1, uuid.toString());
            sts.setLong(2, duration);
            sts.setString(3, reason);
            sts.setString(4, moderator);
            sts.setLong(5, System.currentTimeMillis());
            sts.setString(6, "ban");
            sts.executeUpdate();

            getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "[Hera DB] Insert ban log of " + uuid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void unbanRegister(UUID uuid, String moderator) {

        if (!(moderator.equalsIgnoreCase("CONSOLE")))
            moderator = Main.getInstance().playerInfos.getUUID(moderator).toString();
        try {
            PreparedStatement sts = Main.getInstance().sqlite.getConnection().prepareStatement("INSERT INTO mod_history (player_uuid, duration, reason, moderator, time, type) VALUES (?, ?, ?, ?, ?, ?)");
            sts.setString(1, uuid.toString());
            sts.setLong(2, -1);
            sts.setString(3, "none");
            sts.setString(4, moderator);
            sts.setLong(5, System.currentTimeMillis());
            sts.setString(6, "unban");
            sts.executeUpdate();

            getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "[Hera DB] Insert unban log of " + uuid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void muteRegister(UUID uuid, long duration, String reason, String moderator) {
        if (!(moderator.equalsIgnoreCase("CONSOLE")))
            moderator = Main.getInstance().playerInfos.getUUID(moderator).toString();
        try {
            PreparedStatement sts = Main.getInstance().sqlite.getConnection().prepareStatement("INSERT INTO mod_history (player_uuid, duration, reason, moderator, time, type) VALUES (?, ?, ?, ?, ?, ?)");
            sts.setString(1, uuid.toString());
            sts.setLong(2, duration);
            sts.setString(3, reason);
            sts.setString(4, moderator);
            sts.setLong(5, System.currentTimeMillis());
            sts.setString(6, "mute");
            sts.executeUpdate();

            getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "[Hera DB] Insert mute log of " + uuid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void unmuteRegister(UUID uuid, String moderator) {

        if (!(moderator.equalsIgnoreCase("CONSOLE")))
            moderator = Main.getInstance().playerInfos.getUUID(moderator).toString();
        try {
            PreparedStatement sts = Main.getInstance().sqlite.getConnection().prepareStatement("INSERT INTO mod_history (player_uuid, duration, reason, moderator, time, type) VALUES (?, ?, ?, ?, ?, ?)");
            sts.setString(1, uuid.toString());
            sts.setLong(2, -1);
            sts.setString(3, "none");
            sts.setString(4, moderator);
            sts.setLong(5, System.currentTimeMillis());
            sts.setString(6, "unmute");
            sts.executeUpdate();

            getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "[Hera DB] Insert unmute log of " + uuid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void warnRegister(UUID uuid, String moderator, String reason) {

        if (!(moderator.equalsIgnoreCase("CONSOLE")))
            moderator = Main.getInstance().playerInfos.getUUID(moderator).toString();
        try {
            PreparedStatement sts = Main.getInstance().sqlite.getConnection().prepareStatement("INSERT INTO mod_history (player_uuid, duration, reason, moderator, time, type) VALUES (?, ?, ?, ?, ?, ?)");
            sts.setString(1, uuid.toString());
            sts.setLong(2, -1);
            sts.setString(3, reason);
            sts.setString(4, moderator);
            sts.setLong(5, System.currentTimeMillis());
            sts.setString(6, "warn");
            sts.executeUpdate();

            getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "[Hera DB] Insert warn log of " + uuid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
