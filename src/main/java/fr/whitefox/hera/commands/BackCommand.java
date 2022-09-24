package fr.whitefox.hera.commands;

import fr.whitefox.hera.Main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public class BackCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if (cmd.getName().equalsIgnoreCase("back")) {

            if(!(sender instanceof Player)) return false;
            Player player = (Player) sender;

            if(!(exist(player.getUniqueId()))){
                player.sendMessage(ChatColor.RED + "Vous n'avez pas de dernier point de mort disponible !");
                return false;
            }

            String coordinates = getCoordinates(player.getUniqueId());
            String[] parts = coordinates.split(",");
            String x = parts[0];
            String y = parts[1];
            String z = parts[2];

            Location destination = new Location(player.getWorld(), Double.parseDouble(x), Double.parseDouble(y), Double.parseDouble(z));
            player.teleport(destination);

            player.sendMessage("§6[§9Hera§6] §aVous avez bien été téléporté à votre dernier point de mort.");

            deleteCoordinates(player.getUniqueId());
        }

        return true;
    }

    public boolean exist(UUID playerUUID) {
        try {
            PreparedStatement sts = Main.getInstance().sqlite.getConnection().prepareStatement("SELECT * FROM back WHERE player_uuid=?");
            sts.setString(1, playerUUID.toString());
            ResultSet rs = sts.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getCoordinates(UUID playerUUID) {
        try {
            PreparedStatement sts = Main.getInstance().sqlite.getConnection().prepareStatement("SELECT coordinates FROM back WHERE player_uuid=?");
            sts.setString(1, playerUUID.toString());
            ResultSet rs = sts.executeQuery();

            if (rs.next()) {
                return rs.getString("coordinates");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new NullPointerException("Le joueur n'a pas d'informations dans la table");
    }

    public void deleteCoordinates(UUID playerUUID) {
        try {
            PreparedStatement sts = Main.getInstance().sqlite.getConnection().prepareStatement("DELETE FROM back WHERE player_uuid=?");
            sts.setString(1, playerUUID.toString());
            sts.executeUpdate();

            getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "[Hera DB] Delete back info of " + playerUUID.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


