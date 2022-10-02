package fr.whitefox.hera.commands;

import fr.whitefox.hera.Main;
import fr.whitefox.hera.db.PlayerInfos;
import fr.whitefox.hera.db.TimeUnit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.UUID;

public class BanCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if (msg.equalsIgnoreCase("ban")) {

            if (args.length <= 2) {
                helpMessage(sender);
                return false;
            }

            String targetName = args[0];

            if (!PlayerInfos.exist(targetName)) {
                sender.sendMessage("§cCe joueur ne s'est jamais connecté au serveur !");
                return false;
            }

            UUID targetUUID = PlayerInfos.getUUID(targetName);

            if (Main.getInstance().banManager.isBanned(targetUUID)) {
                sender.sendMessage("§cCe joueur est déjà banni !");
                return false;
            }

            StringBuilder reason = new StringBuilder();
            for (int i = 2; i < args.length; i++) {
                reason.append(args[i]).append(" ");
            }

            if (args[1].equalsIgnoreCase("perm")) {
                Main.getInstance().historyManager.banRegister(targetUUID, -1, reason.toString(), sender.getName());
                Main.getInstance().banManager.ban(targetUUID, -1, reason.toString());
                sender.sendMessage("§6[§9Hera§6] §aVous avez banni §6" + targetName + " §c(Permanent) §apour : §e" + reason);
                return false;
            }

            if (args[1].contains("-")) {
                sender.sendMessage("§cVous ne pouvez pas saisir de valeur négative !");
                return false;
            }

            long banTime = getTime(args[1]);
            String finalBanTime = getTimeBan(banTime);
            if (finalBanTime.equalsIgnoreCase("")) {
                sender.sendMessage("§cVous devez entrer une valeure valide !");
                return false;
            }

            Main.getInstance().banManager.ban(targetUUID, banTime, reason.toString());
            Main.getInstance().historyManager.banRegister(targetUUID, banTime, reason.toString(), sender.getName());
            sender.sendMessage("§6[§9Hera§6] §aVous avez banni §6" + targetName + " pendant §b" + finalBanTime + " §apour : §e" + reason);
            return false;
        }

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

            if (!PlayerInfos.exist(targetName)) {
                sender.sendMessage("§cCe joueur ne s'est jamais connecté au serveur !");
                return false;
            }

            UUID targetUUID = PlayerInfos.getUUID(targetName);

            if (!Main.getInstance().banManager.isBanned(targetUUID)) {
                sender.sendMessage("§cCe joueur n'est pas banni !");
                return false;
            }

            Main.getInstance().historyManager.unbanRegister(targetUUID, sender.getName());
            Main.getInstance().banManager.unban(targetUUID);
            sender.sendMessage("§6[§9Hera§6] §aVous avez débanni §6" + targetName);
            return false;
        }

        return false;
    }

    public void helpMessage(CommandSender sender) {
        sender.sendMessage("§cSyntaxe : /ban <joueur> perm <raison>");
        sender.sendMessage("§cSyntaxe : /ban <joueur> <durée>:<unité> <raison>");
    }

    public long getTime(String s) {
        try {
            long z = parseLong(s);
            if (s.contains("d")) {
                z += parseLong(s.split("d")[0]) * 86400;
                s = split(s, "d");
            }
            if (s.contains("h")) {
                z += parseLong(s.split("h")[0]) * 3600;
                s = split(s, "h");
            }
            if (s.contains("m")) {
                z += parseLong(s.split("m")[0]) * 60;
                s = split(s, "m");
            }
            if (s.contains("s")) {
                z += parseLong(s.split("s")[0]);
            }
            return z;
        } catch (Exception e) {
            return -1;
        }
    }

    private String split(String arg0, String arg1) {
        try {
            return arg0.split(arg1)[1];
        } catch (Exception e) {
            return "";
        }
    }

    private long parseLong(String arg0) {
        try {
            return Long.parseLong(arg0);
        } catch (Exception e) {
            return 0;
        }
    }

    private String getTimeBan(long tempsRestant) {
        int jours = 0;
        int heures = 0;
        int minutes = 0;
        int secondes = 0;

        while (tempsRestant >= TimeUnit.JOUR.getToSecond()) {
            jours++;
            tempsRestant -= TimeUnit.JOUR.getToSecond();
        }

        while (tempsRestant >= TimeUnit.HEURE.getToSecond()) {
            heures++;
            tempsRestant -= TimeUnit.HEURE.getToSecond();
        }

        while (tempsRestant >= TimeUnit.MINUTE.getToSecond()) {
            minutes++;
            tempsRestant -= TimeUnit.MINUTE.getToSecond();
        }

        while (tempsRestant >= TimeUnit.SECONDE.getToSecond()) {
            secondes++;
            tempsRestant -= TimeUnit.SECONDE.getToSecond();
        }

        String d = "";
        String h = "";
        String m = "";
        String s = "";

        if (!(jours == 0)) {
            d = jours + " " + TimeUnit.JOUR.getName();
        }
        if (!(heures == 0)) {
            h = heures + " " + TimeUnit.HEURE.getName();
        }
        if (!(minutes == 0)) {
            m = minutes + " " + TimeUnit.MINUTE.getName();
        }
        if (!(secondes == 0)) {
            s = secondes + " " + TimeUnit.SECONDE.getName();
        }

        return d + h + m + s;
    }
}

