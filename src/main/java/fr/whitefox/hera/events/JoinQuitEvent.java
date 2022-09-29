package fr.whitefox.hera.events;

import fr.whitefox.hera.Main;
import fr.whitefox.hera.db.PlayerInfos;
import fr.whitefox.hera.utils.AntiVPN;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitEvent implements Listener {

    PlayerInfos playerInfos = new PlayerInfos();
    private Main main = Main.getInstance();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        playerInfos.update(player);
        playerInfos.setIPAddress(player);

        if (main.getConfig().getBoolean("antiVPN.activate") && !(player.hasPermission("hera.antivpn.bypass"))) {
            AntiVPN.verif(player);
        }

        event.setJoinMessage("");
        
        if (main.getConfig().getBoolean("join.joinMessage")) {
            Bukkit.broadcastMessage(main.getConfig().getString("join.broadcast").replace("&", "§") + player.getDisplayName());
            player.sendMessage(main.getConfig().getString("join.custom1").replace("&", "§") + player.getDisplayName() + main.getConfig().getString("join.custom2").replace("&", "§"));
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {

        Player player = event.getPlayer();
        playerInfos.setLastConnexion(player);

        event.setQuitMessage("");

        if (main.getConfig().getBoolean("leave.leaveMessage")) {
            Bukkit.broadcastMessage(main.getConfig().getString("leave.broadcast").replace("&", "§") + player.getDisplayName());
        }
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        Main.getInstance().banManager.checkDuration(player.getUniqueId());

        if (main.banManager.isBanned(player.getUniqueId())) {
            event.setResult(PlayerLoginEvent.Result.KICK_BANNED);
            event.setKickMessage("§4§lVous avez été banni du serveur !" +
                    "\n\n\n§c§lRaison : §e" + main.banManager.getReason(player.getUniqueId()) +
                    "\n§c§lTemps restant : §e" + main.banManager.getTimeLeft(player.getUniqueId()) +
                    "\n\n\n§l§6[§9§lHera§l§6]"
            );
        }
    }
}