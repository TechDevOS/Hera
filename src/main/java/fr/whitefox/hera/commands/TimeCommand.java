package fr.whitefox.hera.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;

public class TimeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if (cmd.getName().equalsIgnoreCase("day")) {

            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.getLocation().getWorld().setTime(6000);
                player.sendMessage("§6[§9Hera§6] §aLe monde a bien été défini sur §2§lJour");
            } else {
                if (args.length == 0) {
                    getServer().getConsoleSender().sendMessage(ChatColor.RED + "Vous devez spécifier un joueur !");
                    return true;
                }

                if (args.length >= 1) {
                    Player target = Bukkit.getServer().getPlayer(args[0]);

                    if (target == null) {
                        getServer().getConsoleSender().sendMessage(ChatColor.RED + "Le joueur n'existe pas ou n'est pas connecté !");
                        return true;
                    }
                    target.getLocation().getWorld().setTime(6000);
                    getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Le monde a bien été défini sur Jour");
                }
            }
        }

        if (cmd.getName().equalsIgnoreCase("night")) {

            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.getLocation().getWorld().setTime(18000);
                player.sendMessage("§6[§9Hera§6] §aLe monde a bien été défini sur §2§lNuit");
            } else {
                if (args.length == 0) {
                    getServer().getConsoleSender().sendMessage(ChatColor.RED + "Vous devez spécifier un joueur !");
                    return false;
                }

                if (args.length >= 1) {
                    Player target = Bukkit.getServer().getPlayer(args[0]);

                    if (target == null) {
                        getServer().getConsoleSender().sendMessage(ChatColor.RED + "Le joueur n'existe pas ou n'est pas connecté !");
                        return false;
                    }
                    target.getLocation().getWorld().setTime(18000);
                    getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Le monde a bien été défini sur Nuit");
                }

                return true;
            }
        }

        return true;
    }
}
