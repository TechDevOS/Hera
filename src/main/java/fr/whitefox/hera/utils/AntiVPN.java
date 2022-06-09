package fr.whitefox.hera.utils;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

import static org.bukkit.Bukkit.getServer;

public class AntiVPN {

    public static void verif(Player player) {

        String ip = player.getAddress().toString().substring(1).split(":")[0] ;

        JSONObject obj = APICall.getIP(ip);

        if((boolean) obj.get("proxy") || (boolean) obj.get("hosting")) {
            player.sendMessage("Vous utilisez un VPN !");
            player.kickPlayer("§4§lLes VPN ne sont pas autorisés sur ce serveur, merci de désactiver ce type de logiciel !");
            getServer().getConsoleSender().sendMessage("§4L'utilisateur " + player.getName() + " (" + player.getUniqueId() + ") tente de se connecter via un VPN avec l'IP §e" + ip);
        }

    }
}