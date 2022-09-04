package fr.whitefox.hera.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChat implements Listener {

    @EventHandler
    public void onPlayerMention(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = String.format(event.getMessage());

        event.setCancelled(true);

        for (Player people : Bukkit.getServer().getOnlinePlayers()) {
            if (message.contains(people.getName())) {
                String formatedMessage = message.replaceAll(people.getName(), "§5§l" + people.getName() + "§7");
                people.sendMessage("§7" + player.getName() + " : " + formatedMessage);

            } else {
                people.sendMessage("§7" + player.getName() + " : " + message);
            }
        }
    }
}
