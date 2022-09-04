package fr.whitefox.hera.events;

import fr.whitefox.hera.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class FreezeEvent implements Listener {

    private Main plugin;

    public FreezeEvent(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onFreeze(PlayerMoveEvent event) {

        Player player = event.getPlayer();

        if (plugin.freeze_list.contains(player)) {
            event.setCancelled(true);
        }
    }
}