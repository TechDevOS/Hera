package fr.whitefox.hera.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static fr.whitefox.hera.Main.that;

public class VanishCommand implements CommandExecutor {

    ArrayList<Player> invisible_list = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;

            if(invisible_list.contains(player)){

                for(Player people : Bukkit.getOnlinePlayers()){
                    people.showPlayer(that, player);
                }
                invisible_list.remove(player);
                player.sendMessage(ChatColor.GREEN + "You are now visible to other players on the server !");

            } else if(!invisible_list.contains(player)){
                for(Player people : Bukkit.getOnlinePlayers()){
                    if(people.hasPermission("hera.vanish")){
                        people.showPlayer(that, player);
                    } else{
                        people.hidePlayer(that, player);
                    }

                }
                invisible_list.add(player);
                player.sendMessage(ChatColor.GREEN + "You are now invisible !");
                player.setDisplayName("§7[§aVANISH§7] " + player.getName());
                player.setPlayerListName("§7[§aVANISH§7] " + player.getName());
            }
        }

        return true;
    }
}
