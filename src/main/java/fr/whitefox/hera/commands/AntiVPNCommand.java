package fr.whitefox.hera.commands;

import fr.whitefox.hera.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AntiVPNCommand implements CommandExecutor {

    Main plugin;

    public AntiVPNCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        Player player = (Player) sender;

        if(cmd.getName().equalsIgnoreCase("antivpn")){
            if(args.length == 0){
                player.sendMessage(ChatColor.RED + "Vous devez spécifier un état. Exemple : /antivpn <on/off>");

            } else if (args[0].equalsIgnoreCase("on")){
                player.sendMessage("§6[§9Hera§6] §aL'antiVPN a bien été §2§lactivé");
                plugin.getConfig().set("antiVPN.global", "true");

            } else if (args[0].equalsIgnoreCase("off")){
                player.sendMessage("§6[§9Hera§6] §aL'antiVPN a bien été §2§ldésactivé");
                plugin.getConfig().set("antiVPN.global", "false");

            } else{
                player.sendMessage(ChatColor.RED + "Vous devez spécifier un état valide. Exemple : /antivpn <on/off>");

            }
        }


        return true;
    }
}
