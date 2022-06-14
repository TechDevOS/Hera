package fr.whitefox.hera.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StopCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        Player player = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("s")){

            if(args.length == 0){
                for (Player people : Bukkit.getOnlinePlayers()) {
                    people.kickPlayer("" +
                            "§e§l----------------------------------------------------------------" +
                            "\n§l§6[§9§lHera§l§6]" +
                            "\n\n\n§c§lLe serveur redémarre" +
                            "\n\n\n§e§l----------------------------------------------------------------");
                }

                Bukkit.shutdown();
            }

            if(args.length >= 1){
                StringBuilder bc = new StringBuilder();
                for (String part : args) {
                    bc.append(part + " ");
                }

                String reason = bc.toString();

                for (Player people : Bukkit.getOnlinePlayers()) {
                    people.kickPlayer("" +
                            "§e§l----------------------------------------------------------------" +
                            "\n§l§6[§9§lHera§l§6]" +
                            "\n\n\n§c§l" + reason +
                            "\n\n\n§e§l----------------------------------------------------------------");
                }

                Bukkit.shutdown();
            }

            return true;
        }

        return true;
    }
}
