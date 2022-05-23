package fr.whitefox.hera.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.net.InetSocketAddress;

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

                    InetSocketAddress ip = target.getAddress();
                    player.sendMessage(" ");
                    player.sendMessage("§e----------------------------------------");
                    player.sendMessage("§eAdresse IP de §b" + target.getName());
                    player.sendMessage(" ");
                    player.sendMessage("» §6" + ip);
                    player.sendMessage(" ");
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
                        if(player.hasPermission("hera.sc")){
                            p.sendMessage("§2[§aStaffChat§2] §b" + player.getName() + " : " + bc.toString());
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