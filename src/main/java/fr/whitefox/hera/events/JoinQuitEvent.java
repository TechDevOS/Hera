package fr.whitefox.hera.events;

import fr.whitefox.hera.Main;
import fr.whitefox.hera.utils.AntiVPN;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
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

        AntiVPN.verif(player);

        event.setJoinMessage("");

        if(plugin.getConfig().getBoolean("join.joinMessage")){
            Bukkit.broadcastMessage(plugin.getConfig().getString("join.broadcast").replace("&", "§") + player.getDisplayName());
            player.sendMessage(plugin.getConfig().getString("join.custom1").replace("&", "§") + player.getDisplayName() + plugin.getConfig().getString("join.custom2").replace("&", "§"));

        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {

        Player player = event.getPlayer();

        event.setQuitMessage("");

        if(plugin.getConfig().getBoolean("leave.quitMessage")){
            Bukkit.broadcastMessage(plugin.getConfig().getString("leave.broadcast").replace("&", "§") + player.getDisplayName());

        }
    }
}