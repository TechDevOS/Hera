package fr.whitefox.hera.commands;

import fr.whitefox.hera.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Date;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

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
            if (Bukkit.getPlayer(targetUUID) != null && sender.hasPermission("hera.dupeip")) {
                Player target = getServer().getPlayer(args[0]);
                String ip = target.getAddress().toString().substring(1).split(":")[0];
                sender.sendMessage("§cDernière IP connue : §6§l" + ip);
            }
            sender.sendMessage("\n§cMuet : " + isMuted);
            sender.sendMessage("§cBanni : " + isBanned);

            sender.sendMessage("\n§7" + ChatColor.STRIKETHROUGH + "----------------------------------------------------");
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
