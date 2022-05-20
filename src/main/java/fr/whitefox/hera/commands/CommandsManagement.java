package fr.whitefox.hera.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static fr.whitefox.hera.Main.that;

public class CommandsManagement implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {


        Player player = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("down")){

            Bukkit.broadcastMessage("§e----------------------------------------");
            Bukkit.broadcastMessage(" ");
            Bukkit.broadcastMessage("§6ANNONCE§r » §4§lLe serveur va redémarrer dans §l10 secondes§r ! ");
            Bukkit.broadcastMessage(" ");
            Bukkit.broadcastMessage("§e----------------------------------------");

            Bukkit.getScheduler().runTaskLater(that, new Runnable() {
                @Override
                public void run() {
                    Bukkit.shutdown();
                }
            }, 200L);

            return true;
        }

        if(cmd.getName().equalsIgnoreCase("alert")) {

            if (args.length == 0) {
                player.sendMessage("");
                player.sendMessage(ChatColor.RED + "La commande est : /alert <message>");
            }

            if (args.length >= 1) {
                StringBuilder bc = new StringBuilder();
                for (String part : args) {
                    bc.append(part + " ");
                }

                Bukkit.broadcastMessage("§e----------------------------------------");
                Bukkit.broadcastMessage(" ");
                Bukkit.broadcastMessage("§6ANNONCE§r » §4§l" + bc.toString());
                Bukkit.broadcastMessage(" ");
                Bukkit.broadcastMessage("§e----------------------------------------");

            }

            return true;
        }

        return false;
    }
}
