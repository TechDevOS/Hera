package fr.whitefox.hera.debug;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.IOException;

import static org.bukkit.Bukkit.getServer;

public class DebugCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if (cmd.getName().equalsIgnoreCase("debug")) {

            if (args.length == 0) {
                try {
                    Runtime.getRuntime().exec("cp /Users/whitefox/IdeaProjects/Hera/target/hera-1.0.jar /Users/whitefox/Desktop/Server/plugins");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                sender.sendMessage("§6[§9Hera§6]§r §4§lDEBUG§r ▶ §aLe plugin a été mis à jour ! Redémarrage en cours...");
                getServer().dispatchCommand(getServer().getConsoleSender(), "rl");

                return true;
            }

            if (args[0].equalsIgnoreCase("info")) {

                long maxMemory = Runtime.getRuntime().maxMemory();
                long cores = Runtime.getRuntime().availableProcessors();
                long freeMemory = Runtime.getRuntime().freeMemory();
                long totalMemory = Runtime.getRuntime().totalMemory();

                sender.sendMessage("\n§7" + ChatColor.STRIKETHROUGH + "------------------" + "§9§lDEBUG SERVER INF§7" + ChatColor.STRIKETHROUGH + "------------------");
                sender.sendMessage("\n▶ §aAvailable processors (cores) : §4§l" + cores);
                sender.sendMessage("▶ §aFree memory (bytes): §4§l" + freeMemory);
                sender.sendMessage("▶ §aMaximum memory (bytes): §4§l" + maxMemory);
                sender.sendMessage("▶ §aTotal memory available to JVM (bytes): §4§l" + totalMemory);
                sender.sendMessage("\n§7" + ChatColor.STRIKETHROUGH + "----------------------------------------------------");
                sender.sendMessage(" ");
            }

            return true;
        }

        return true;
    }
}
