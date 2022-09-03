package fr.whitefox.hera.commands;

import fr.whitefox.hera.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    Main plugin;

    public SpawnCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;

            if(cmd.getName().equalsIgnoreCase("spawn")) {
                if (args.length == 0) {

                    double x = plugin.getConfig().getDouble("config.spawn_x");
                    double y = plugin.getConfig().getDouble("config.spawn_y");
                    double z = plugin.getConfig().getDouble("config.spawn_z");

                    Location spawn = new Location(player.getWorld(), x, y, z, 0f, 0f);
                    player.teleport(spawn);

                    player.sendMessage("§6[§9Hera§6] §aVous venez d'être téléporté au spawn.");

                }

                return true;
            }

        }

        return true;
    }
}
