package fr.whitefox.hera.events;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {

        Player player = event.getPlayer();

        event.setQuitMessage("");
        Bukkit.broadcastMessage("[§4-§r] " + player.getDisplayName());
    }

}

