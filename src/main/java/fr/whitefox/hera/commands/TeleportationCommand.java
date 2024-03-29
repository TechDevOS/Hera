package fr.whitefox.hera.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportationCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("tpall")) {

            if (!(sender instanceof Player)) return false;
            Player player = (Player) sender;

            if (Bukkit.getServer().getOnlinePlayers().size() == 1) {
                player.sendMessage(ChatColor.RED + "Il n'y a pas d'autres joueurs sur le serveur.");

            } else {
                for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                    p.teleport(player.getLocation());
                }

                player.sendMessage("§6[§9Hera§6] §b" + (Bukkit.getServer().getOnlinePlayers().size() - 1) + "§a joueurs ont été téléportés sur votre position.");
            }
        }

        return true;
    }
}