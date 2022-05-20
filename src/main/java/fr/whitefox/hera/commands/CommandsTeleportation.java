package fr.whitefox.hera.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandsTeleportation implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            if (cmd.getName().equalsIgnoreCase("tpall")) {
                if (Bukkit.getServer().getOnlinePlayers().size() == 1) {
                    player.sendMessage("");
                    player.sendMessage(ChatColor.RED + "Il n'y a pas d'autres joueurs sur le serveur");
                } else if (Bukkit.getServer().getOnlinePlayers().size() > 1) {
                    int numOfPLayers = 0;
                    for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                        p.teleport(player.getLocation());
                        numOfPLayers++;
                    }


                    player.sendMessage("§aTeleportation effectuée de §b" + (numOfPLayers - 1) + "§a joueurs sur votre position.");
                }
            }

            if (cmd.getName().equalsIgnoreCase("spawn")) {

                Location spawn = new Location(Bukkit.getWorld("word_of_the_end"), 200, 200, 200, 1.8f, 7.4f);
                player.teleport(spawn);
                player.sendMessage("");
                player.sendMessage(ChatColor.GREEN + "Téléportation au spawn effectuée ");

            }

            return true;
        }


        return false;
    }
}
