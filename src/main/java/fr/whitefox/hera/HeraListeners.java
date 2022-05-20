package fr.whitefox.hera;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.IOException;

public class HeraListeners implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) throws IOException {

        Player player = event.getPlayer();

        Bukkit.broadcastMessage("[§a+§r] " + player.getDisplayName());
        event.setJoinMessage("§6[§9Hera§6] §rBienvenue à toi §b" + player.getDisplayName() + "§r sur ce serveur !");
    }

    public void onLeave(PlayerQuitEvent event) {

        Player player = event.getPlayer();

        Bukkit.broadcastMessage("[§4-§r] " + player.getDisplayName());
        event.setQuitMessage("Bye !");
    }

}

