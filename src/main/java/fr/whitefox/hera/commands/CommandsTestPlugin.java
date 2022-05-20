package fr.whitefox.hera.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

import static org.bukkit.Bukkit.getServer;


public class CommandsTestPlugin implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if(cmd.getName().equalsIgnoreCase("update")){

            try {
                Runtime.getRuntime().exec("cp /Users/whitefox/IdeaProjects/Hera/target/hera-1.0-SNAPSHOT.jar /Users/whitefox/Desktop/Server/plugins");
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(sender instanceof Player) {
                Player player = (Player) sender;

                player.sendMessage("");
                player.sendMessage("§aLe plugin a été mis à jour !");
            } else{
                getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Le plugin a été mis à jour !");
            }

            return true;

        }
        return false;
    }
}
