package fr.whitefox.hera.commands;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WeatherCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if (cmd.getName().equalsIgnoreCase("rain")) {

            if (!(sender instanceof Player)) return false;
            Player player = (Player) sender;

            World world = player.getWorld();
            world.setStorm(true);
            world.setThundering(false);
            player.sendMessage("§6[§9Hera§6] §aLe temps du monde a bien été défini sur §2§lPluvieux");
        }

        if (cmd.getName().equalsIgnoreCase("sun")) {

            if (!(sender instanceof Player)) return false;
            Player player = (Player) sender;

            World world = player.getWorld();
            world.setThundering(false);
            world.setStorm(false);
            player.sendMessage("§6[§9Hera§6] §aLe temps du monde a bien été défini sur §2§lClair");

        }

        if (cmd.getName().equalsIgnoreCase("thunder")) {

            if (!(sender instanceof Player)) return false;
            Player player = (Player) sender;

            World world = player.getWorld();
            world.setThundering(true);
            world.setStorm(true);
            player.sendMessage("§6[§9Hera§6] §aLe temps du monde a bien été défini sur §2§lOrageux");

        }

        return true;
    }
}
