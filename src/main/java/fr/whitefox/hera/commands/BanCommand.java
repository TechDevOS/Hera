package fr.whitefox.hera.commands;

import fr.whitefox.hera.Main;
import fr.whitefox.hera.utils.TimeUnit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.UUID;

public class BanCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        /**
         *    /ban <joueur> perm <raison>
         *    /ban <joueur> <durée>:<unité> <raison>
         */

        if (msg.equalsIgnoreCase("ban")) {
            if (!sender.hasPermission("hera.ban")) {
                sender.sendMessage("§cVous n'avez pas la permission d'éxecuter cette commande !");
                return false;
            }

            if (args.length < 3) {
                helpMessage(sender);
                return false;
            }

            String targetName = args[0];

            if (!Main.getInstance().playerInfos.exist(targetName)) {
                sender.sendMessage("§cCe joueur ne s'est jamais connecté au serveur !");
                return false;
            }

            UUID targetUUID = Main.getInstance().playerInfos.getUUID(targetName);

            if (Main.getInstance().banManager.isBanned(targetUUID)) {
                sender.sendMessage("§cCe joueur est déjà banni !");
                return false;
            }

            String reason = "";
            for (int i = 2; i < args.length; i++) {
                reason += args[i] + " ";
            }

            if (args[1].equalsIgnoreCase("perm")) {
                Main.getInstance().banManager.ban(targetUUID, -1, reason);
                sender.sendMessage("§6[§9Hera§6] §aVous avez banni §6" + targetName + " §c(Permanent) §apour : §e" + reason);
                return false;
            }

            if (!args[1].contains(":")) {
                helpMessage(sender);
                return false;
            }

            int duration = 0;
            try {
                duration = Integer.parseInt(args[1].split(":")[0]);
            } catch (NumberFormatException e) {
                sender.sendMessage("§cLa valeur 'durée' doit être un nombre !");
                return false;
            }

            if (!TimeUnit.existFromShortcut(args[1].split(":")[1])) {
                sender.sendMessage("§cCette unité de temps n'existe pas !");
                for (TimeUnit units : TimeUnit.values()) {
                    sender.sendMessage("§b" + units.getName() + " §f: §e" + units.getShortcut());
                }
                return false;
            }

            TimeUnit unit = TimeUnit.getFromShortcut(args[1].split(":")[1]);
            long banTime = unit.getToSecond() * duration;

            Main.getInstance().banManager.ban(targetUUID, banTime, reason);
            sender.sendMessage("§6[§9Hera§6] §aVous avez banni §6" + targetName + " §b(" + duration + " " + unit.getName() + ") §apour : §e" + reason);
            return false;
        }

        /**
         *    /unban <joueur>
         */

        if (msg.equalsIgnoreCase("unban")) {
            if (!sender.hasPermission("hera.ban")) {
                sender.sendMessage("§cVous n'avez pas la permission d'éxecuter cette commande !");
                return false;
            }

            if (args.length != 1) {
                sender.sendMessage("§cSyntaxe : /unban <joueur>");
                return false;
            }

            String targetName = args[0];

            if (!Main.getInstance().playerInfos.exist(targetName)) {
                sender.sendMessage("§cCe joueur ne s'est jamais connecté au serveur !");
                return false;
            }

            UUID targetUUID = Main.getInstance().playerInfos.getUUID(targetName);

            if (!Main.getInstance().banManager.isBanned(targetUUID)) {
                sender.sendMessage("§cCe joueur n'est pas banni !");
                return false;
            }

            Main.getInstance().banManager.unban(targetUUID);
            sender.sendMessage("§6[§9Hera§6] §aVous avez débanni §6" + targetName);
            return false;
        }

        return false;
    }

    public void helpMessage(CommandSender sender) {
        sender.sendMessage("§cSyntaxe : /ban <joueur> perm <raison>");
        sender.sendMessage("§cSyntaxe : /ban <joueur> <durée><unité> <raison>");
    }
}

