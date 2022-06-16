package fr.whitefox.hera.commands;

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

                return true;
            }

        }
        return true;
    }
}
