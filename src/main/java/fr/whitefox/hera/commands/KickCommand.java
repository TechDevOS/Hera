package fr.whitefox.hera.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;

public class KickCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if (cmd.getName().equalsIgnoreCase("kick")) {

            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "La commande est : /kick <pseudo> {raison}");
            }

            if (args.length >= 1) {
                Player target = getServer().getPlayer(args[0]);

                StringBuilder builder = new StringBuilder();
                for (int i = 1; i < args.length; i++) {
                    builder.append(args[i]).append(" ");
                }
                String reason = builder.toString();

                if (reason.equals("")) {
                    reason = "Aucune raison fournie";
                }

                if (target == null) {
                    sender.sendMessage(ChatColor.RED + "Le joueur n'existe pas ou n'est pas connecté !");
                    return true;
                }

                target.kickPlayer(
                        "§4§lVous avez été éjecté du serveur !" +
                                "\n\n\n§c§lRaison : §e" + reason +
                                "\n\n\n§l§6[§9§lHera§l§6]"
                );
            }

        }

        return true;
    }
}
