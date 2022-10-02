package fr.whitefox.hera.commands;

import fr.whitefox.hera.utils.APICall;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

import static org.bukkit.Bukkit.getServer;

public class DupeipCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if (cmd.getName().equalsIgnoreCase("dupeip")) {

            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "La commande est : /dupeip <pseudo>");
            }

            if (args.length >= 1) {
                Player target = getServer().getPlayer(args[0]);

                if (target == null) {
                    sender.sendMessage(ChatColor.RED + "Le joueur n'existe pas ou n'est pas connecté !");
                    return true;
                }

                String ip = target.getAddress().toString().substring(1).split(":")[0];

                JSONObject obj = APICall.getIP(ip);
                if (obj == null) {
                    sender.sendMessage(ChatColor.RED + "L'adresse IP n'existe pas !");
                    return false;
                }

                if (obj.get("status") == "success") {
                    sender.sendMessage(ChatColor.RED + "L'IP saisie est incorrecte !");
                    return false;
                }

                String connexion = "Réseau domestique";
                if ((boolean) obj.get("mobile")) connexion = "4G";
                if ((boolean) obj.get("proxy")) connexion = "Proxy";
                if ((boolean) obj.get("hosting")) connexion = "Hébergeur";

                sender.sendMessage("\n§eAdresse IP de §b" + target.getName());
                sender.sendMessage("\n» §6Adresse IP :§c " + ip);
                sender.sendMessage("» §6Pays :§c " + obj.get("country"));
                sender.sendMessage("» §6FAI : §c" + obj.get("isp"));
                sender.sendMessage("» §6Type de connexion : §c" + connexion + "\n");
            }

            return true;
        }
        return false;
    }
}
