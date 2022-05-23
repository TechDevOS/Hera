package fr.whitefox.hera.events;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import static fr.whitefox.hera.Main.that;


import java.io.IOException;

public class JoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) throws IOException {

        Player player = event.getPlayer();

        event.setJoinMessage("");
        Bukkit.broadcastMessage("[§a+§r] " + player.getDisplayName());
        player.sendMessage("§6[§9Hera§6] §rBienvenue à toi §b" + player.getDisplayName() + "§r sur ce serveur !");
    }
}

