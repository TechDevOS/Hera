package fr.whitefox.hera.utils;

import fr.whitefox.hera.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

import static org.bukkit.Bukkit.getServer;

public class AntiVPN {

    public static void verif(Player player) {

        String ip = player.getAddress().toString().substring(1).split(":")[0];

        JSONObject obj = APICall.getIP(ip);

        if ((boolean) obj.get("proxy") && Main.getInstance().getConfig().getBoolean("antiVPN.proxy")) {
            player.kickPlayer(
                    "§4§lVous avez été éjecté du serveur !" +
                            "\n\n\n§c§lRaison : §eLes Proxy ne sont pas autorisés sur ce serveur, merci de passer sur une connexion classique !" +
                            "\n\n\n§l§6[§9§lHera§l§6]");
            getServer().getConsoleSender().sendMessage(ChatColor.RED + "L'utilisateur " + player.getName() + " (" + player.getUniqueId() + ") tente de se connecter avec un Proxy via l'IP " + ip);

        } else if ((boolean) obj.get("hosting") && Main.getInstance().getConfig().getBoolean("antiVPN.hosting")) {
            player.kickPlayer(
                    "§4§lVous avez été éjecté du serveur !" +
                            "\n\n\n§c§lRaison : §eLes connexions de type hébergeur ne sont pas autorisées sur ce serveur, merci de passer sur une connexion classique !" +
                            "\n\n\n§l§6[§9§lHera§l§6]");
            getServer().getConsoleSender().sendMessage(ChatColor.RED + "L'utilisateur " + player.getName() + " (" + player.getUniqueId() + ") tente de se connecter avec une connexion d'hébergeur via l'IP " + ip);

        } else if ((boolean) obj.get("mobile") && Main.getInstance().getConfig().getBoolean("antiVPN.4G")) {
            player.kickPlayer(
                    "§4§lVous avez été éjecté du serveur !" +
                            "\n\n\n§c§lRaison : §eLes connexions 4G ne sont pas autorisées sur ce serveur, merci de passer sur une connexion classique !" +
                            "\n\n\n§l§6[§9§lHera§l§6]");
            getServer().getConsoleSender().sendMessage(ChatColor.RED + "L'utilisateur " + player.getName() + " (" + player.getUniqueId() + ") tente de se connecter avec une connexion 4G via l'IP " + ip);
        }
    }
}