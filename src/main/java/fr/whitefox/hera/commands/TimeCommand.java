package fr.whitefox.hera.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TimeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if (cmd.getName().equalsIgnoreCase("day")) {

            if (!(sender instanceof Player)) return false;
            Player player = (Player) sender;

            player.getLocation().getWorld().setTime(6000);
            player.sendMessage("§6[§9Hera§6] §aLe monde a bien été défini sur §2§lJour");
        }

        if (cmd.getName().equalsIgnoreCase("night")) {

            if (!(sender instanceof Player)) return false;
            Player player = (Player) sender;

            player.getLocation().getWorld().setTime(18000);
            player.sendMessage("§6[§9Hera§6] §aLe monde a bien été défini sur §2§lNuit");
        }

        return true;
    }
}
