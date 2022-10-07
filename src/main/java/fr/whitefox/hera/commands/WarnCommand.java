package fr.whitefox.hera.commands;

import fr.whitefox.hera.Main;
import fr.whitefox.hera.db.PlayerInfos;
import fr.whitefox.hera.utils.DiscordLogger;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public class WarnCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if(cmd.getName().equalsIgnoreCase("warn")) {

            if (args.length <= 1) {
                helpMessage(sender);
                return false;
            }

            String targetName = args[0];

            if (!PlayerInfos.exist(targetName)) {
                sender.sendMessage("§cCe joueur ne s'est jamais connecté au serveur !");
                return false;
            }

            UUID targetUUID = PlayerInfos.getUUID(targetName);

            StringBuilder reason = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                reason.append(args[i]).append(" ");
            }

            Player target = getServer().getPlayer(args[0]);

            Main.getInstance().historyManager.warnRegister(targetUUID, sender.getName(), reason.toString());
            sender.sendMessage("§6[§9Hera§6] §aVous avez warn §6" + targetName + "§a pour la raison suivante : §e" + reason);

            if (Bukkit.getPlayer(targetUUID) != null) {
                target.sendMessage("§6[§9Hera§6] §cVous avez été averti pour la raison suivante : §e" + reason);
            }

            DiscordLogger.register(targetName, sender,"none", reason.toString(), "warn");
        }
        return true;
    }

    public void helpMessage(CommandSender sender) {
        sender.sendMessage("§cUsage : /warn <player> <reason>");
    }
}
