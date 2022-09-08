package fr.whitefox.hera.commands;

import fr.whitefox.hera.Main;
import fr.whitefox.hera.mysql.TimeUnit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.UUID;

public class MuteCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        /**
         *    /mute <joueur> perm <raison>
         *    /mute <joueur> <durée>:<unité> <raison>
         */

        if (msg.equalsIgnoreCase("mute")) {
            if (!sender.hasPermission("hera.mute")) {
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

            if (Main.getInstance().muteManager.isMuted(targetUUID)) {
                sender.sendMessage("§cCe joueur est déjà mute !");
                return false;
            }

            String reason = "";
            for (int i = 2; i < args.length; i++) {
                reason += args[i] + " ";
            }

            if (args[1].equalsIgnoreCase("perm")) {
                Main.getInstance().muteManager.mute(targetUUID, -1, reason);
                sender.sendMessage("§6[§9Hera§6] §aVous avez mute §6" + targetName + " §c(Permanent) §apour : §e" + reason);
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
            long muteTime = unit.getToSecond() * duration;

            Main.getInstance().muteManager.mute(targetUUID, muteTime, reason);
            sender.sendMessage("§6[§9Hera§6] §aVous avez mute §6" + targetName + " §b(" + duration + " " + unit.getName() + ") §apour : §e" + reason);
            return false;
        }

        /**
         *    /unmute <joueur>
         */

        if (msg.equalsIgnoreCase("unmute")) {
            if (!sender.hasPermission("hera.mute")) {
                sender.sendMessage("§cVous n'avez pas la permission d'éxecuter cette commande !");
                return false;
            }

            if (args.length != 1) {
                sender.sendMessage("§cSyntaxe : /unmute <joueur>");
                return false;
            }

            String targetName = args[0];

            if (!Main.getInstance().playerInfos.exist(targetName)) {
                sender.sendMessage("§cCe joueur ne s'est jamais connecté au serveur !");
                return false;
            }

            UUID targetUUID = Main.getInstance().playerInfos.getUUID(targetName);

            if (!Main.getInstance().muteManager.isMuted(targetUUID)) {
                sender.sendMessage("§cCe joueur n'est pas mute !");
                return false;
            }

            Main.getInstance().muteManager.unmute(targetUUID);
            sender.sendMessage("§6[§9Hera§6] §aVous avez unmute §6" + targetName);
            return false;
        }

        return false;
    }

    public void helpMessage(CommandSender sender) {
        sender.sendMessage("§cSyntaxe : /mute <joueur> perm <raison>");
        sender.sendMessage("§cSyntaxe : /mute <joueur> <durée><unité> <raison>");
    }
}


