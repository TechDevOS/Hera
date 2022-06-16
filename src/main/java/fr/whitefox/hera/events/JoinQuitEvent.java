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

    Main plugin;

    public JoinQuitEvent(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) throws IOException {

        Player player = event.getPlayer();

        AntiVPN.verif(player);

        event.setJoinMessage("");
        Bukkit.broadcastMessage("[§a+§r] " + player.getDisplayName());
        player.sendMessage("§6[§9Hera§6] §rBienvenue à toi §b" + player.getDisplayName() + "§r sur ce serveur !");

    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {

        Player player = event.getPlayer();

        event.setQuitMessage("");
        Bukkit.broadcastMessage("[§4-§r] " + player.getDisplayName());

    }

}