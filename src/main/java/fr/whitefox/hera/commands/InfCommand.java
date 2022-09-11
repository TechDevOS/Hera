package fr.whitefox.hera.commands;

import fr.whitefox.hera.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Date;
import java.util.UUID;

public class InfCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if (cmd.getName().equalsIgnoreCase("inf")) {

            if (args.length < 1) {
                sender.sendMessage(helpMessage());
                return false;
            }

            String targetName = args[0];

            if (!Main.getInstance().playerInfos.exist(targetName)) {
                sender.sendMessage("§cCe joueur ne s'est jamais connecté au serveur !");
                return false;
            }

            UUID targetUUID = Main.getInstance().playerInfos.getUUID(targetName);
            String isBanned = isBanned(targetUUID);
            String isMuted = isMuted(targetUUID);
            String lastConnection = "§6§l" + getLastConnexion(targetUUID);

            sender.sendMessage("\n§7" + ChatColor.STRIKETHROUGH + "----------------" + "§9§lPLAYER INFORMATIONS§7" + ChatColor.STRIKETHROUGH + "----------------");
            sender.sendMessage("\n\n§cPseudo : §6§l" + targetName);
            sender.sendMessage("§cDernière connexion : " + lastConnection);
            if (sender.hasPermission("hera.dupeip")) {
                sender.sendMessage("§cDernière IP connue : §6§l" + Main.getInstance().playerInfos.getIPAddress(targetUUID));
            }
            sender.sendMessage("\n§cMuet : " + isMuted);
            sender.sendMessage("§cBanni : " + isBanned);

            sender.sendMessage("\n§7" + ChatColor.STRIKETHROUGH + "---------------------------------------------------");
            sender.sendMessage(" ");
        }

        return true;
    }

    public String helpMessage() {
        return "§cSyntaxe : /inf <joueur>";
    }

    private String isBanned(UUID playerUUID) {
        return Main.getInstance().banManager.isBanned(playerUUID) ? "§4§lOui" : "§a§lNon";
    }

    private String isMuted(UUID playerUUID) {
        return Main.getInstance().muteManager.isMuted(playerUUID) ? "§4§lOui" : "§a§lNon";
    }

    private String getLastConnexion(UUID playerUUID) {
        if (Bukkit.getPlayer(playerUUID) != null) {
            return "§a§lJoueur actuellement connecté";
        } else {
            Date lastConnection = new Date(Main.getInstance().playerInfos.getLastConnexion(playerUUID));
            return lastConnection.toString();
        }
    }
}
