package fr.whitefox.hera.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StopCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if (cmd.getName().equalsIgnoreCase("s")) {

            if (args.length == 0) {
                for (Player people : Bukkit.getOnlinePlayers()) {
                    people.kickPlayer(
                            "§4§lVous avez été éjecté du serveur !" +
                                    "\n\n\n§c§lRaison : §eLe serveur redémarre" +
                                    "\n\n\n§l§6[§9§lHera§l§6]");
                }

                Bukkit.shutdown();
            }

            if (args.length >= 1) {
                StringBuilder bc = new StringBuilder();
                for (String part : args) {
                    bc.append(part + " ");
                }

                String reason = bc.toString();

                for (Player people : Bukkit.getOnlinePlayers()) {
                    people.kickPlayer(
                            "§4§lVous avez été éjecté du serveur !" +
                                    "\n\n\n§c§lRaison : §e" + reason +
                                    "\n\n\n§l§6[§9§lHera§l§6]");
                }

                Bukkit.shutdown();
            }

            return true;
        }

        return true;
    }
}
