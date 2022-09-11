package fr.whitefox.hera.mysql;

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
            PreparedStatement sts = Main.getInstance().mysql.getConnection().prepareStatement("INSERT INTO ban_history (player_uuid, duration, reason, moderator) VALUES (?, ?, ?, ?)");
            sts.setString(1, uuid.toString());
            sts.setLong(2, duration);
            sts.setString(3, reason);
            sts.setString(4, moderator);
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
            PreparedStatement sts = Main.getInstance().mysql.getConnection().prepareStatement("INSERT INTO ban_history (player_uuid, duration, reason, moderator) VALUES (?, ?, ?, ?)");
            sts.setString(1, uuid.toString());
            sts.setLong(2, 0);
            sts.setString(3, "[HERA] UNBAN");
            sts.setString(4, moderator);
            sts.executeUpdate();

            getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "[Hera DB] Insert unban log of " + uuid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
