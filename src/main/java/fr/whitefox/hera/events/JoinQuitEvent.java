package fr.whitefox.hera.events;

import fr.whitefox.hera.Main;
import fr.whitefox.hera.mysql.PlayerInfos;
import fr.whitefox.hera.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.IOException;

public class JoinQuitEvent implements Listener {

    private Main plugin;

    public JoinQuitEvent(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) throws IOException {

        Player player = event.getPlayer();
        PlayerInfos playerInfos = new PlayerInfos();

        playerInfos.update(player);

        if (plugin.getConfig().getBoolean("antiVPN.activate")) {
            AntiVPN.verif(player);
        }

        event.setJoinMessage("");

        if (plugin.getConfig().getBoolean("join.joinMessage")) {
            Bukkit.broadcastMessage(plugin.getConfig().getString("join.broadcast").replace("&", "§") + player.getDisplayName());
            player.sendMessage(plugin.getConfig().getString("join.custom1").replace("&", "§") + player.getDisplayName() + plugin.getConfig().getString("join.custom2").replace("&", "§"));
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {

        Player player = event.getPlayer();

        event.setQuitMessage("");

        if (plugin.getConfig().getBoolean("leave.leaveMessage")) {
            Bukkit.broadcastMessage(plugin.getConfig().getString("leave.broadcast").replace("&", "§") + player.getDisplayName());
        }
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event){
        Player player = event.getPlayer();
        Main.getInstance().banManager.checkDuration(player.getUniqueId());

        if(Main.getInstance().banManager.isBanned(player.getUniqueId())){
            event.setResult(PlayerLoginEvent.Result.KICK_BANNED);
            event.setKickMessage("§4§lVous avez été banni du serveur !" +
                    "\n\n\n§c§lRaison : §e" + Main.getInstance().banManager.getReason(player.getUniqueId()) +
                    "\n§c§lTemps restant : §e" + Main.getInstance().banManager.getTimeLeft(player.getUniqueId()) +
                    "\n\n\n§l§6[§9§lHera§l§6]"
            );
        }
    }
}