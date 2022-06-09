package fr.whitefox.hera.commands;

import fr.whitefox.hera.utils.APICall;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.*;

public class CommandsModeration implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(cmd.getName().equalsIgnoreCase("dupeip")) {

                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "La commande est : /dupeip <pseudo>");
                }

                if (args.length >= 1) {
                    Player target = Bukkit.getServer().getPlayer(args[0]);

                    if (target == null) {
                        player.sendMessage(ChatColor.RED + "Le joueur n'existe pas ou n'est pas connecté !");
                        return true;
                    }

                    String ip = target.getAddress().toString().substring(1).split(":")[0] ;

                    JSONObject obj = APICall.getIP(ip);
                    if(obj == null){
                        player.sendMessage(ChatColor.RED + "L'adresse IP n'existe pas !");
                        return false;
                    }

                    if(obj.get("status") == "success"){
                        player.sendMessage(ChatColor.RED + "L'IP saisie est incorrecte !");
                        return false;
                    }

                    String connexion = "Réseau domestique";
                    if((boolean) obj.get("mobile")) connexion = "4G";
                    if((boolean) obj.get("proxy")) connexion = "Proxy";
                    if((boolean) obj.get("hosting")) connexion = "Hébergeur";

                    player.sendMessage(" ");
                    player.sendMessage("§e----------------------------------------");
                    player.sendMessage("§eAdresse IP de §b" + target.getName());
                    player.sendMessage(" ");
                    player.sendMessage("» §6Adresse IP :§c " + ip);
                    player.sendMessage("» §6Pays :§c " + obj.get("country"));
                    player.sendMessage("» §6FAI : §c" + obj.get("isp"));
                    player.sendMessage("» §6Type de connexion : §c" + connexion);
                    player.sendMessage("§e----------------------------------------");
                    player.sendMessage(" ");
                }

                return true;
            }

            if(cmd.getName().equalsIgnoreCase("sc")) {

                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "La commande est : /sc <message>");
                }

                if (args.length >= 1) {
                    StringBuilder bc = new StringBuilder();
                    for (String part : args) {
                        bc.append(part + " ");
                    }

                    int numOfPLayers = 0;
                    for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                        if(p.hasPermission("hera.sc")){
                            p.sendMessage("§5[§dStaffChat§5] §a[" + player.getWorld().getName() +  "]§b " + player.getName() + " : " + bc.toString());
                        }

                        numOfPLayers++;
                    }

                }

                return true;
            }

        }
        return false;
    }
}