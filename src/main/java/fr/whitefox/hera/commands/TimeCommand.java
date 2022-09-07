package fr.whitefox.hera.commands;

import fr.whitefox.hera.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;

public class TimeCommand implements CommandExecutor {

    Main plugin;

    public TimeCommand(Main plugin) {
        this.plugin = plugin;
    }

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

        if (cmd.getName().equalsIgnoreCase("pday")) {

            if (sender instanceof Player) {
                Player player = (Player) sender;

                if (!plugin.pday_list.contains(player)) {
                    plugin.pday_list.add(player);
                    plugin.pnight_list.remove(player);
                    player.setPlayerTime(6000, true);
                    player.sendMessage("§6[§9Hera§6] §aVotre vision personnelle a bien été définie sur §2§lJour ");
                } else {
                    plugin.pday_list.remove(player);
                    player.resetPlayerTime();
                    player.sendMessage("§6[§9Hera§6] §aVotre vision personnelle est bien revenue sur celle du serveur. ");
                }

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

                if (!plugin.pday_list.contains(target)) {
                    plugin.pday_list.add(target);
                    target.setPlayerTime(6000, true);
                    getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "La vision personnelle de §6" + target.getName() + "§a a bien été définie sur Jour");
                } else {
                    plugin.pday_list.remove(target);
                    target.resetPlayerTime();
                    getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "La vision personnelle de §6" + target.getName() + "§a est bien revenue sur celle du serveur.");
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

        if (cmd.getName().equalsIgnoreCase("pnight")) {

            if (sender instanceof Player) {
                Player player = (Player) sender;

                if (!plugin.pnight_list.contains(player)) {
                    plugin.pnight_list.add(player);
                    plugin.pday_list.remove(player);
                    player.getLocation().getWorld().setTime(18000);
                    player.sendMessage(" §aVotre vision personnelle a bien été défini sur §2§lNuit");
                } else {
                    plugin.pnight_list.remove(player);
                    player.resetPlayerTime();
                    player.sendMessage("§6[§9Hera§6] §aVotre vision personnelle est bien revenue sur celle du serveur. ");
                }

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

                if (!plugin.pnight_list.contains(target)) {
                    plugin.pnight_list.add(target);
                    target.getLocation().getWorld().setTime(18000);
                    getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "La vision personnelle de §6" + target.getName() + "§a a bien été définie sur Nuit");
                } else {
                    plugin.pnight_list.remove(target);
                    target.resetPlayerTime();
                    getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "La vision personnelle de §6" + target.getName() + "§a est bien revenue sur celle du serveur.");
                }
            }
        }

        return true;
    }
}
