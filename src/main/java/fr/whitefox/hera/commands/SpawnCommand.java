package fr.whitefox.hera.commands;

import fr.whitefox.hera.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    private final Main main = Main.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if (cmd.getName().equalsIgnoreCase("spawn")) {

            if (!(sender instanceof Player)) return false;
            Player player = (Player) sender;

            if (args.length == 0) {

                double x = main.getConfig().getDouble("config.spawn_x");
                double y = main.getConfig().getDouble("config.spawn_y");
                double z = main.getConfig().getDouble("config.spawn_z");

                Location spawn = new Location(player.getWorld(), x, y, z, 0f, 0f);
                player.teleport(spawn);

                player.sendMessage("§6[§9Hera§6] §aVous venez d'être téléporté au spawn.");
            }

            return true;
        }

        return true;
    }
}
