package fr.whitefox.hera.commands;

import fr.whitefox.hera.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FreezeCommand implements CommandExecutor {

    Main plugin;

    public FreezeCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        if(args.length == 0) {
            player.sendMessage(ChatColor.RED + "Vous devez spécifier un joueur !");
            return false;
        }

        if(args[0].equalsIgnoreCase(player.getName())){
            player.sendMessage(ChatColor.RED + "Vous ne pouvez pas vous freeze vous même !");
            return false;
        }

        if(args.length > 0) {
            Player target = Bukkit.getServer().getPlayer(args[0]);

            if(target == null) {
                player.sendMessage(ChatColor.RED + "Le joueur n'existe pas ou n'est pas connecté !");
                return false;
            }

            if(!plugin.freeze_list.contains(target)){
                plugin.freeze_list.add(target);
                target.sendMessage("§6[§9Hera§6] §cVous avez été freeze !");
                player.sendMessage("§6[§9Hera§6] §aLe joueur §c" + target.getName() + "§a a bien été freeze.");
            } else {
                plugin.freeze_list.remove(target);
                target.sendMessage("§6[§9Hera§6] §aVous n'êtes plus freeze !");
                player.sendMessage("§6[§9Hera§6] §aLe joueur §c" + target.getName() + "§a n'est plus freeze.");
            }
        }

        return true;
    }
}
