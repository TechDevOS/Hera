package fr.whitefox.hera.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StopCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {


        Player player = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("down")){


        }

        return false;
    }
}
