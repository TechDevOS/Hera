package fr.whitefox.hera.commands;

import fr.whitefox.hera.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    Main plugin;

    public FlyCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        if(cmd.getName().equalsIgnoreCase("fly")) {
            if (args.length == 0) {
                if(!plugin.fly_list.contains(player)){
                    plugin.fly_list.add(player);
                    player.setAllowFlight(true);
                    player.sendMessage("§6[§9Hera§6] §aVous êtes désormais en fly !");
                } else {
                    plugin.fly_list.remove(player);
                    player.setAllowFlight(false);
                    player.sendMessage("§6[§9Hera§6] §cVous n'êtes désormais plus en fly !");
                }
            }

            if (args.length > 0){
                Player target = Bukkit.getServer().getPlayer(args[0]);

                if (target == null) {
                    player.sendMessage(ChatColor.RED + "Le joueur n'existe pas ou n'est pas connecté !");
                    return true;
                }

                if(!plugin.fly_list.contains(target)){
                    plugin.fly_list.add(target);
                    target.setAllowFlight(true);
                    target.sendMessage("§6[§9Hera§6] §aVous êtes désormais en fly !");
                    player.sendMessage("§6[§9Hera§6] §aVous venez de mettre en fly §c" + target.getName() + "§a !");
                } else {
                    plugin.fly_list.remove(target);
                    target.setAllowFlight(false);
                    target.sendMessage("§6[§9Hera§6] §cVous n'êtes désormais plus en fly !");
                    player.sendMessage("§6[§9Hera§6] §aVous venez de retirer le fly de §c" + target.getName() + "§a !");
                }

                return true;
            }

            return true;
        }

        return true;
    }
}
