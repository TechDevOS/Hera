package fr.whitefox.hera.commands;

import fr.whitefox.hera.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {

    Main plugin;

    public VanishCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;
            if (plugin.invisible_list.contains(player)){
                for (Player people : Bukkit.getOnlinePlayers()){
                    people.showPlayer(plugin, player);
                }
                plugin.invisible_list.remove(player);
                player.sendMessage(ChatColor.GREEN + "You are now visible to other players on the server.");
            }else if(!plugin.invisible_list.contains(player)){
                for (Player people : Bukkit.getOnlinePlayers()){
                    people.hidePlayer(plugin, player);
                }
                plugin.invisible_list.add(player);
                player.sendMessage(ChatColor.GREEN + "You are now invisible!");
                System.out.println(plugin.invisible_list); // DEBUG
            }
        }

        return true;
    }
}
