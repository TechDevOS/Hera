package fr.whitefox.hera.events;

import fr.whitefox.hera.Main;
import fr.whitefox.hera.utils.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChat implements Listener {

    private Main plugin;

    public PlayerChat(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        String message = String.format(event.getMessage());

        if (plugin.getConfig().getBoolean("WebhooksDiscord.activate")) {
            Webhooks.message(player, message);
        }
    }
}
