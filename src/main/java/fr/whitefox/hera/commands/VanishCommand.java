package fr.whitefox.hera.commands;

import fr.whitefox.hera.Main;
import fr.whitefox.hera.utils.Vanish;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {

    Main plugin;

    public VanishCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if (cmd.getName().equalsIgnoreCase("vanish")) {

            if (sender instanceof Player) return false;
            Player player = (Player) sender;

            if (args.length == 0) {
                Vanish.vanish(player);

                return true;
            }

            if (args.length > 0) {

                Player target = Bukkit.getServer().getPlayer(args[0]);

                if (target == null) {
                    player.sendMessage(ChatColor.RED + "Le joueur n'existe pas ou n'est pas connecté !");
                    return true;
                }

                if (plugin.invisible_list.contains(target)) {
                    player.sendMessage(ChatColor.GREEN + "§6[§9Hera§6] §aLe joueur §c" + target.getName() + "§a est maintenant visible pour les autres joueurs du serveur.");
                } else {
                    player.sendMessage(ChatColor.GREEN + "§6[§9Hera§6] §aLe joueur §c" + target.getName() + "§a est maintenant invisible.");
                }

                Vanish.vanish(target);

                return true;
            }
        }

        return true;
    }
}