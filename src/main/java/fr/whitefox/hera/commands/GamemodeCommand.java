package fr.whitefox.hera.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class GamemodeCommand implements CommandExecutor {

    public static final HashMap<String, GameMode> gm = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        gm.put("0", GameMode.SURVIVAL);
        gm.put("1", GameMode.CREATIVE);
        gm.put("2", GameMode.ADVENTURE);
        gm.put("3", GameMode.SPECTATOR);

        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        if(args.length <= 0) {
            player.sendMessage(ChatColor.RED + "Vous devez spécifier un mode de jeu ! Exemple : /gm <0;1;2;3>");
            return false;
        }

        GameMode gamemode;
        if(gm.containsKey(args[0])) {
            gamemode = gm.get(args[0]);
        } else {
            player.sendMessage(ChatColor.RED + "Vous devez spécifier un mode de jeu valide ! Exemple : /gm <0;1;2;3>");
            return false;
        }

        if(args.length > 1) {
            Player target = Bukkit.getServer().getPlayer(args[1]);

            if(target == null) {
                player.sendMessage(ChatColor.RED + "Le joueur n'existe pas ou n'est pas connecté !");
                return false;
            }
            target.setGameMode(gamemode);
            target.sendMessage("§6[§9Hera§6] §aVotre mode de jeu a bien été modifié");
            player.sendMessage("§6[§9Hera§6] §aLe mode de jeu de §c" + target.getName() + "§a a bien été modifié");
        } else {
            player.setGameMode(gamemode);
            player.sendMessage("§6[§9Hera§6] §aVotre mode de jeu a bien été modifié");
        }

        return true;
    }
}
