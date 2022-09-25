package fr.whitefox.hera.events;

import fr.whitefox.hera.Main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.bukkit.Bukkit.getServer;

public class DeathEvent implements Listener {

    @EventHandler
    public void DeathPlayer(PlayerDeathEvent event) {

        Player player = event.getEntity();

        if (player.getKiller() instanceof Player) {
            event.setDeathMessage("§6[§9Hera§6] §aLe joueur §b" + player.getName() + "§a a été éliminé par §b" + player.getKiller().getName() + ".");
        } else {
            event.setDeathMessage("§6[§9Hera§6] §aLe joueur §b" + player.getName() + "§a est mort.");
        }

        Location loc = player.getLocation();

        String x = String.valueOf(loc.getX());
        String y = String.valueOf(loc.getY());
        String z = String.valueOf(loc.getZ());

        String coordinates = x + "," + y + "," + z;

        try {
            PreparedStatement sts = Main.getInstance().sqlite.getConnection().prepareStatement("INSERT INTO back (player_uuid, coordinates) VALUES (?, ?)");
            sts.setString(1, player.getUniqueId().toString());
            sts.setString(2, coordinates);
            sts.executeUpdate();

            getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "[Hera DB] Insert back info of " + player.getUniqueId().toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
