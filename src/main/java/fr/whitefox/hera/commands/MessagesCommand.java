package fr.whitefox.hera.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessagesCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command cmd, String msg, String[] args) {

        if (cmd.getName().equalsIgnoreCase("msg")) {
            Player player = (Player) commandSender;
            if (args.length == 0 || args.length == 1) {
                player.sendMessage(ChatColor.RED + "Utilise bien la commande");
            }
            if (args.length >= 2) {
                if (Bukkit.getPlayer(args[0]) == null) {
                    player.sendMessage(ChatColor.RED + "Joueur pas en ligne");
                } else {
                    Player target = Bukkit.getPlayer(args[0]);

                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < args.length; i++) {
                        sb.append(args[i] + " ");
                    }
                    String message = sb.toString();
                    player.sendMessage("§7(To §c" + target.getName() + "§7) " + msg);
                    target.sendMessage("§7(From §c" + player.getName() + "§7) " + msg);
                }
            }
        }

        return true;
    }
}
