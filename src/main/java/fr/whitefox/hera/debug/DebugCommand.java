package fr.whitefox.hera.debug;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

import static org.bukkit.Bukkit.getServer;

public class DebugCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if(cmd.getName().equalsIgnoreCase("debug")){

            if(args.length == 0){
                try {
                    Runtime.getRuntime().exec("cp /Users/whitefox/IdeaProjects/Hera/target/hera-1.0-SNAPSHOT.jar /Users/whitefox/Desktop/Server/plugins");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if(sender instanceof Player) {
                    Player player = (Player) sender;

                    player.sendMessage("§6[§9Hera§6]§r §4§lDEBUG§r ▶ §aLe plugin a été mis à jour !");
                } else{
                    getServer().getConsoleSender().sendMessage("§6[§9Hera§6]§r §4§lDEBUG§r ▶ §aLe plugin a été mis à jour !");
                }

                return true;
            }

            if(args[0].equalsIgnoreCase("info")){
                if(sender instanceof Player) {
                    Player player = (Player) sender;
                    long maxMemory = Runtime.getRuntime().maxMemory();
                    long cores = Runtime.getRuntime().availableProcessors();
                    long freeMemory = Runtime.getRuntime().freeMemory();
                    long totalMemory = Runtime.getRuntime().totalMemory();

                    player.sendMessage("§6[§9Hera§6]§r §4§lDEBUG §r▶ §cServer Information");
                    player.sendMessage("\n▶ §aAvailable processors (cores) : §4§l" + cores);
                    player.sendMessage("▶ §aFree memory (bytes): §4§l" + freeMemory);
                    player.sendMessage("▶ §aMaximum memory (bytes): §4§l" + maxMemory);
                    player.sendMessage("▶ §aTotal memory available to JVM (bytes): §4§l" + totalMemory);
                } else {
                    long maxMemory = Runtime.getRuntime().maxMemory();

                    getServer().getConsoleSender().sendMessage("§6[§9Hera§6]§r §4§lDEBUG §r▶ §cServer Information");
                    getServer().getConsoleSender().sendMessage(" ");
                    getServer().getConsoleSender().sendMessage("▶ §aAvailable processors (cores) : §4§l" + Runtime.getRuntime().availableProcessors());
                    getServer().getConsoleSender().sendMessage("▶ §aFree memory (bytes): §4§l" + Runtime.getRuntime().freeMemory());
                    getServer().getConsoleSender().sendMessage("▶ §aMaximum memory (bytes): §4§l" + (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory));
                    getServer().getConsoleSender().sendMessage("▶ §aTotal memory available to JVM (bytes): §4§l" + Runtime.getRuntime().totalMemory());
                }

                return true;

            } else return false;
        }

        return true;
    }
}
