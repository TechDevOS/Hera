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

                String coordinates = main.getConfig().getString("config.spawn_x");
                String[] parts = coordinates.split(",");
                String x = parts[0];
                String y = parts[1];
                String z = parts[2];

                Location spawn = new Location(player.getWorld(), Double.parseDouble(x), Double.parseDouble(y), Double.parseDouble(z), 0f, 0f);
                player.teleport(spawn);

                player.sendMessage("§6[§9Hera§6] §aVous venez d'être téléporté au spawn.");
            }

            return true;
        }

        return true;
    }
}
