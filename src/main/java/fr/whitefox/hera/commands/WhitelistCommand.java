package fr.whitefox.hera.commands;

import fr.whitefox.hera.Main;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;

public class WhitelistCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if (cmd.getName().equalsIgnoreCase("wl")) {

            if (sender instanceof Player) {
                Player player = (Player) sender;

                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "La commande est : /wl <on/off>");

                } else if (args[0].equalsIgnoreCase("on")) {
                    Bukkit.setWhitelist(true);
                    player.sendMessage("§6[§9Hera§6] §aLa whitelist a bien été §2§lactivée");

                } else if (args[0].equalsIgnoreCase("off")) {
                    Bukkit.setWhitelist(false);
                    player.sendMessage("§6[§9Hera§6] §aLa whitelist a bien été §2§ldésactivée");

                } else if (args[0].equalsIgnoreCase("add")) {
                    if (args.length != 2) {
                        player.sendMessage(ChatColor.RED + "Veuillez spécifier un joueur !");
                        return true;
                    }

                    String target = (args[1]);
                    getServer().dispatchCommand(getServer().getConsoleSender(), "whitelist add " + target);

                    player.sendMessage("§6[§9Hera§6] §aLe joueur §e" + target + " §aa bien été §2§lajouté §aà la whitelist");
                    Bukkit.reloadWhitelist();


                } else if (args[0].equalsIgnoreCase("remove")) {
                    if (args.length != 2) {
                        player.sendMessage(ChatColor.RED + "Veuillez spécifier un joueur !");
                        return true;
                    }

                    String target = (args[1]);
                    getServer().dispatchCommand(getServer().getConsoleSender(), "whitelist remove " + target);

                    player.sendMessage("§6[§9Hera§6] §aLe joueur §e" + target + "§aa bien été §2§lsupprimé §ade la whitelist");
                    Bukkit.reloadWhitelist();

                } else {
                    player.sendMessage(ChatColor.RED + "La commande est : /wl <on/off>");
                }

                return true;

            } else {
                if (args.length == 0) {
                    getServer().getConsoleSender().sendMessage(ChatColor.RED + "La commande est : /wl <on/off>");

                } else if (args[0].equalsIgnoreCase("on")) {
                    Bukkit.setWhitelist(true);
                    getServer().getConsoleSender().sendMessage("§6[§9Hera§6] §aLa whitelist a bien été §2§lactivée");

                } else if (args[0].equalsIgnoreCase("off")) {
                    Bukkit.setWhitelist(false);
                    getServer().getConsoleSender().sendMessage("§6[§9Hera§6] §aLa whitelist a bien été §2§ldésactivée");

                } else if (args[0].equalsIgnoreCase("add")) {
                    if (args.length != 2) {
                        getServer().getConsoleSender().sendMessage(ChatColor.RED + "Veuillez spécifier un joueur !");
                        return true;
                    }

                    String target = (args[1]);
                    getServer().dispatchCommand(getServer().getConsoleSender(), "whitelist add " + target);

                    getServer().getConsoleSender().sendMessage("§6[§9Hera§6] §aLe joueur §e" + target + " §aa bien été §2§lajouté§a à la whitelist");
                    Bukkit.reloadWhitelist();


                } else if (args[0].equalsIgnoreCase("remove")) {
                    if (args.length != 2) {
                        getServer().getConsoleSender().sendMessage(ChatColor.RED + "Veuillez spécifier un joueur !");
                        return true;
                    }

                    String target = (args[1]);
                    getServer().dispatchCommand(getServer().getConsoleSender(), "whitelist remove " + target);

                    getServer().getConsoleSender().sendMessage("§6[§9Hera§6] §aLe joueur §e" + target + "§e) §aa bien été §2§lsupprimé§a de la whitelist");
                    Bukkit.reloadWhitelist();

                } else {
                    getServer().getConsoleSender().sendMessage(ChatColor.RED + "La commande est : /wl <on/off>");
                }

                return true;
            }
        }

        return true;
    }
}
