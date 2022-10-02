package fr.whitefox.hera.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;

public class WeatherCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if (cmd.getName().equalsIgnoreCase("rain")) {

            if (sender instanceof Player) {
                Player player = (Player) sender;
                World world = player.getWorld();
                world.setStorm(true);
                world.setThundering(false);
                player.sendMessage("§6[§9Hera§6] §aLe temps du monde a bien été défini sur §2§lPluvieux");
            } else {
                if (args.length == 0) {
                    getServer().getConsoleSender().sendMessage(ChatColor.RED + "Vous devez spécifier un joueur !");
                    return true;
                }

                Player target = Bukkit.getServer().getPlayer(args[0]);

                if (target == null) {
                    getServer().getConsoleSender().sendMessage(ChatColor.RED + "Le joueur n'existe pas ou n'est pas connecté !");
                    return true;
                }
                target.getWorld().setThundering(true);
                target.getWorld().setStorm(false);
                getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Le temps du monde a bien été défini sur Pluvieux");
                target.sendMessage("§6[§9Hera§6] §aLe temps du monde a bien été défini sur §2§lPluvieux");
            }
        }

        if (cmd.getName().equalsIgnoreCase("sun")) {

            if (sender instanceof Player) {
                Player player = (Player) sender;
                World world = player.getWorld();
                world.setThundering(false);
                world.setStorm(false);
                player.sendMessage("§6[§9Hera§6] §aLe temps du monde a bien été défini sur §2§lClair");
            } else {
                if (args.length == 0) {
                    getServer().getConsoleSender().sendMessage(ChatColor.RED + "Vous devez spécifier un joueur !");
                    return true;
                }

                Player target = Bukkit.getServer().getPlayer(args[0]);

                if (target == null) {
                    getServer().getConsoleSender().sendMessage(ChatColor.RED + "Le joueur n'existe pas ou n'est pas connecté !");
                    return true;
                }
                target.getWorld().setThundering(false);
                target.getWorld().setStorm(false);
                getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Le temps du monde a bien été défini sur Clair");
                target.sendMessage("§6[§9Hera§6] §aLe temps du monde a bien été défini sur §2§lClair");
            }
        }

        if (cmd.getName().equalsIgnoreCase("thunder")) {

            if (sender instanceof Player) {
                Player player = (Player) sender;
                World world = player.getWorld();
                world.setThundering(true);
                world.setStorm(true);
                player.sendMessage("§6[§9Hera§6] §aLe temps du monde a bien été défini sur §2§lOrageux");
            } else {
                if (args.length == 0) {
                    getServer().getConsoleSender().sendMessage(ChatColor.RED + "Vous devez spécifier un joueur !");
                    return true;
                }

                Player target = Bukkit.getServer().getPlayer(args[0]);

                if (target == null) {
                    getServer().getConsoleSender().sendMessage(ChatColor.RED + "Le joueur n'existe pas ou n'est pas connecté !");
                    return true;
                }
                target.getWorld().setThundering(true);
                target.getWorld().setStorm(true);
                getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Le temps du monde a bien été défini sur Orageux");
                target.sendMessage("§6[§9Hera§6] §aLe temps du monde a bien été défini sur §2§lOrageux");
            }
        }

        return true;
    }
}
