package fr.whitefox.hera.commands;

import fr.whitefox.hera.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FreezeCommand implements CommandExecutor {

    private final Main main = Main.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if (cmd.getName().equalsIgnoreCase("freeze")) {

            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Vous devez spécifier un joueur !");
                return false;
            }

            if (args[0].equalsIgnoreCase(sender.getName())) {
                sender.sendMessage(ChatColor.RED + "Vous ne pouvez pas vous freeze vous même !");
                return false;
            }

            Player target = Bukkit.getServer().getPlayer(args[0]);

            if (target == null) {
                sender.sendMessage(ChatColor.RED + "Le joueur n'existe pas ou n'est pas connecté !");
                return false;
            }

            if (!main.freeze_list.contains(target)) {
                main.freeze_list.add(target);
                target.sendMessage("§6[§9Hera§6] §cVous avez été freeze !");
                sender.sendMessage("§6[§9Hera§6] §aLe joueur §c" + target.getName() + "§a a bien été freeze.");
            } else {
                main.freeze_list.remove(target);
                target.sendMessage("§6[§9Hera§6] §aVous n'êtes plus freeze !");
                sender.sendMessage("§6[§9Hera§6] §aLe joueur §c" + target.getName() + "§a n'est plus freeze.");
            }
        }

        return true;
    }
}
