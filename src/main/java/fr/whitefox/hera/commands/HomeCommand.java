package fr.whitefox.hera.commands;

import fr.whitefox.hera.Main;
import fr.whitefox.hera.db.HomeManager;
import fr.whitefox.hera.db.PlayerInfos;
import org.bukkit.Bukkit;
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

public class HomeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if (cmd.getName().equalsIgnoreCase("home")) {

            if (!(sender instanceof Player)) return false;
            Player player = (Player) sender;

            if (args.length == 0) {
                player.sendMessage("§6[§9Hera§6] §cVeuillez spécifier un home");
                return false;
            }

            if (!(HomeManager.exist(player.getUniqueId(), args[0]))) {
                player.sendMessage("§6[§9Hera§6] §cAucun de vos homes ne porte le nom de §6§l" + args[0] + "§c !");
                return false;
            }

            String coordinates = getCoordinates(player.getUniqueId(), args[0]);
            String[] parts = coordinates.split(",");
            String x = parts[0];
            String y = parts[1];
            String z = parts[2];
            String world = parts[3];

            Location destination = new Location(Bukkit.getServer().getWorld(world), Double.parseDouble(x), Double.parseDouble(y), Double.parseDouble(z));
            player.teleport(destination);

            player.sendMessage("§6[§9Hera§6] §aVous avez bien été téléporté au home : §6§l" + args[0]);

            return true;
        }

        if (cmd.getName().equalsIgnoreCase("homes")) {


            return true;
        }

        if (cmd.getName().equalsIgnoreCase("sethome")) {

            if (!(sender instanceof Player)) return false;
            Player player = (Player) sender;

            if (args.length == 0) {
                player.sendMessage("§6[§9Hera§6] §cVeuillez spécifier un home");
                return false;
            }

            if (HomeManager.exist(player.getUniqueId(), args[0])) {
                player.sendMessage("§6[§9Hera§6] §cVous possédez déjà un home portant ce nom !");
                return false;
            }

            Location loc = player.getLocation();

            String x = String.valueOf(loc.getX());
            String y = String.valueOf(loc.getY());
            String z = String.valueOf(loc.getZ());
            String world = String.valueOf(loc.getWorld().getName());

            String coordinates = x + "," + y + "," + z + "," + world;

            HomeManager.set(player.getUniqueId(), args[0], coordinates);

            player.sendMessage("§6[§9Hera§6] §aVotre home a bien été enregistré sous le nom : §6§l" + args[0]);

            return true;
        }

        if (cmd.getName().equalsIgnoreCase("delhome")) {

            if (args.length == 0) {
                sender.sendMessage("§6[§9Hera§6] §cVeuillez spécifier un home");
                return false;
            }

            if (args.length == 1) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;

                    if (!(HomeManager.exist(player.getUniqueId(), args[0]))) {
                        player.sendMessage("§6[§9Hera§6] §cAucun de vos homes ne portent le nom de §6§l" + args[0] + "§c !");
                        return false;
                    } else {
                        HomeManager.delete(player.getUniqueId(), args[0]);
                        player.sendMessage("§6[§9Hera§6] §aVotre home portant le nom §6§l" + args[0] + "§a a bien été supprimé.");
                        return true;
                    }
                } else {
                    sender.sendMessage("§6[§9Hera§6] §cVeuillez spécifier un joueur ainsi qu'un home !");
                }

            } else {
                if (sender.hasPermission("hera.home.others")) {
                    if (!(PlayerInfos.exist(args[0]))) {
                        sender.sendMessage(ChatColor.RED + "Le joueur n'existe pas ou ne s'est jamais connecté !");
                        return false;
                    }
                    if (!(HomeManager.exist(PlayerInfos.getUUID(args[0]), args[1]))) {
                        sender.sendMessage("§6[§9Hera§6] §cAucun des homes de §6§l" + args[0] + "§a ne portent le nom de §6§l" + args[1] + "§c !");
                        return false;
                    } else {
                        HomeManager.delete(PlayerInfos.getUUID(args[0]), args[1]);
                        sender.sendMessage("§6[§9Hera§6] §aLe home de §6§l" + args[0] + "§a portant le nom §6§l" + args[1] + "§a a bien été supprimé.");
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public String getCoordinates(UUID playerUUID, String homeName) {
        try {
            PreparedStatement sts = Main.getInstance().sqlite.getConnection().prepareStatement("SELECT coordinates FROM homes WHERE (player_uuid=? AND home_name=?)");
            sts.setString(1, playerUUID.toString());
            sts.setString(2, homeName);
            ResultSet rs = sts.executeQuery();

            if (rs.next()) {
                return rs.getString("coordinates");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new NullPointerException("Le joueur n'a pas d'informations dans la table");
    }
}
