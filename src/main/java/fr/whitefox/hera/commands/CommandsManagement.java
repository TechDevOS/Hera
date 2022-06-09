package fr.whitefox.hera.commands;

import fr.whitefox.hera.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static fr.whitefox.hera.Main.that;

public class CommandsManagement implements CommandExecutor {

    Main plugin;

    public CommandsManagement(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {


        Player player = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("down")){

            Bukkit.broadcastMessage("§e----------------------------------------");
            Bukkit.broadcastMessage(" ");
            Bukkit.broadcastMessage(plugin.getConfig().getString("messages.down").replace("&", "§"));
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

                Bukkit.broadcastMessage(" ");
                Bukkit.broadcastMessage("   §6" + player.getName() + "§r » §4§l" + bc.toString());
                Bukkit.broadcastMessage(" ");

            }

            return true;
        }

        return false;
    }
}
