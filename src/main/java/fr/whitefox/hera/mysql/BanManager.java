package fr.whitefox.hera.mysql;

import fr.whitefox.hera.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class BanManager {

    public void ban(UUID uuid, long endInSeconds, String reason) {
        if (isBanned(uuid)) return;

        long endToMillis = endInSeconds * 1000;
        long end = endToMillis + System.currentTimeMillis();

        if (endInSeconds == -1) {
            end = -1;
        }

        try {
            PreparedStatement sts = Main.getInstance().mysql.getConnection().prepareStatement("INSERT INTO bans (player_uuid, end, reason) VALUES (?, ?, ?)");
            sts.setString(1, uuid.toString());
            sts.setLong(2, end);
            sts.setString(3, reason);
            sts.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (Bukkit.getPlayer(uuid) != null) {
            Player target = Bukkit.getPlayer(uuid);
            target.kickPlayer("§4§lVous avez été banni du serveur !" +
                    "\n\n\n§c§lRaison : §e" + reason +
                    "\n§c§lTemps restant : §e" + getTimeLeft(uuid) +
                    "\n\n\n§l§6[§9§lHera§l§6]"
            );
        }
    }

    public void unban(UUID uuid) {
        if (!isBanned(uuid)) return;

        try {
            PreparedStatement sts = Main.getInstance().mysql.getConnection().prepareStatement("DELETE FROM bans WHERE player_uuid=?");
            sts.setString(1, uuid.toString());
            sts.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isBanned(UUID uuid) {
        try {
            PreparedStatement sts = Main.getInstance().mysql.getConnection().prepareStatement("SELECT * FROM bans WHERE player_uuid=?");
            sts.setString(1, uuid.toString());
            ResultSet rs = sts.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void checkDuration(UUID uuid) {
        if (!isBanned(uuid)) return;

        if (getEnd(uuid) == -1) return;

        if (getEnd(uuid) < System.currentTimeMillis()) {
            unban(uuid);
        }
    }

    public long getEnd(UUID uuid) {
        if (!isBanned(uuid)) return 0;

        try {
            PreparedStatement sts = Main.getInstance().mysql.getConnection().prepareStatement("SELECT * FROM bans WHERE player_uuid=?");
            sts.setString(1, uuid.toString());
            ResultSet rs = sts.executeQuery();
            if (rs.next()) {
                return rs.getLong("end");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getTimeLeft(UUID uuid) {
        if (!isBanned(uuid)) return "§cNon banni";

        if (getEnd(uuid) == -1) {
            return "§cPermanent";
        }

        long tempsRestant = (getEnd(uuid) - System.currentTimeMillis()) / 1000;
        int jours = 0;
        int heures = 0;
        int minutes = 0;
        int secondes = 0;

        while (tempsRestant >= TimeUnit.JOUR.getToSecond()) {
            jours++;
            tempsRestant -= TimeUnit.JOUR.getToSecond();
        }

        while (tempsRestant >= TimeUnit.HEURE.getToSecond()) {
            heures++;
            tempsRestant -= TimeUnit.HEURE.getToSecond();
        }

        while (tempsRestant >= TimeUnit.MINUTE.getToSecond()) {
            minutes++;
            tempsRestant -= TimeUnit.MINUTE.getToSecond();
        }

        while (tempsRestant >= TimeUnit.SECONDE.getToSecond()) {
            secondes++;
            tempsRestant -= TimeUnit.SECONDE.getToSecond();
        }

        return jours + " " + TimeUnit.JOUR.getName() + ", " + heures + " " + TimeUnit.HEURE.getName() + ", " + minutes + " " + TimeUnit.MINUTE.getName() + ", " + secondes + " " + TimeUnit.SECONDE.getName();
    }

    public String getReason(UUID uuid) {
        if (!isBanned(uuid)) return "§cNon banni";

        try {
            PreparedStatement sts = Main.getInstance().mysql.getConnection().prepareStatement("SELECT * FROM bans WHERE player_uuid=?");
            sts.setString(1, uuid.toString());
            ResultSet rs = sts.executeQuery();
            if (rs.next()) {
                return rs.getString("reason");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "§cNon banni";
    }

}