package fr.whitefox.hera.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandsTeleportation implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            if (cmd.getName().equalsIgnoreCase("tpall")) {
                if (Bukkit.getServer().getOnlinePlayers().size() == 1) {
                    player.sendMessage(ChatColor.RED + "There are no other players on the server");

                } else {
                    for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                        p.teleport(player.getLocation());
                    }

                    player.sendMessage("§b" + (Bukkit.getServer().getOnlinePlayers().size() - 1) + "§a players have been teleported to your position");
                }
            }

            return true;
        }

        return true;
    }
}
