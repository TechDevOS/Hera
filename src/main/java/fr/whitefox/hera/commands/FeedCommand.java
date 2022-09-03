package fr.whitefox.hera.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;

            if(cmd.getName().equalsIgnoreCase("feed")) {
                if (args.length == 0) {
                    player.setFoodLevel(20);
                    player.sendMessage("§6[§9Hera§6] §aVotre nourriture vient d'être restaurée !");
                }

                if (args.length > 0){
                    Player target = Bukkit.getServer().getPlayer(args[0]);

                    if (target == null) {
                        player.sendMessage(ChatColor.RED + "Le joueur n'existe pas ou n'est pas connecté !");
                        return true;
                    }
                    target.setFoodLevel(20);
                    target.sendMessage("§6[§9Hera§6] §aVotre nourriture vient d'être restaurée !");
                    player.sendMessage("§6[§9Hera§6] §aVous venez de restaurer la nourriture de §c" + target.getName() + "§a !");

                    return true;
                }

                return true;
            }
        }

        return true;
    }
}
