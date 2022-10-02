package fr.whitefox.hera.commands;

import fr.whitefox.hera.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AntiVPNCommand implements CommandExecutor {

    private final Main main = Main.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if (cmd.getName().equalsIgnoreCase("antivpn")) {

            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Vous devez spécifier un état. Exemple : /antivpn <on/off>");

            } else if (args[0].equalsIgnoreCase("on")) {
                sender.sendMessage("§6[§9Hera§6] §aL'antiVPN a bien été §2§lactivé§r§a. \nRelancez le serveur pour que la configuration soit effective.");
                main.getConfig().set("antiVPN.activate", true);

            } else if (args[0].equalsIgnoreCase("off")) {
                sender.sendMessage("§6[§9Hera§6] §aL'antiVPN a bien été §2§ldésactivé§r§a. \nRelancez le serveur pour que la configuration soit effective.");
                main.getConfig().set("antiVPN.activate", false);

            } else {
                sender.sendMessage(ChatColor.RED + "Vous devez spécifier un état valide. Exemple : /antivpn <on/off>");
            }
        }

        return true;
    }
}
