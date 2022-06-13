package fr.whitefox.hera.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class GamemodeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;

            if(cmd.getName().equalsIgnoreCase("gm")) {
                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "Vous devez spécifier un mode de jeu ! Exemple : /gm <0;1;2;3>");

                } else if (args[0].equalsIgnoreCase("0")) {
                    player.setGameMode(GameMode.SURVIVAL);
                    player.sendMessage("§6[§9Hera§6] §aVotre mode de jeu a bien été défini sur §2§lSurvival");

                } else if (args[0].equalsIgnoreCase("1")) {
                    player.setGameMode(GameMode.CREATIVE);
                    player.sendMessage("§6[§9Hera§6] §aVotre mode de jeu a bien été défini sur §2§lCréatif");

                } else if (args[0].equalsIgnoreCase("2")) {
                    player.setGameMode(GameMode.ADVENTURE);
                    player.sendMessage("§6[§9Hera§6] §aVotre mode de jeu a bien été défini sur §2§lAventure");

                } else if (args[0].equalsIgnoreCase("3")) {
                    player.setGameMode(GameMode.SPECTATOR);
                    player.sendMessage("§6[§9Hera§6] §aVotre mode de jeu a bien été défini sur §2§lSpectateur");

                } else {
                    player.sendMessage(ChatColor.RED + "Vous devez spécifier un mode de jeu valide ! Exemple : /gm <0;1;2;3>");

                }

                return true;
            }
        }

        return true;
    }
}
