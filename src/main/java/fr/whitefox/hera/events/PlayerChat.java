package fr.whitefox.hera.events;

import fr.whitefox.hera.Main;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.cacheddata.CachedMetaData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChat implements Listener {
    private final Main plugin;
    private final LuckPerms luckPerms;

    public PlayerChat(Main plugin, LuckPerms luckPerms) {
        this.plugin = plugin;
        this.luckPerms = luckPerms;
    }

    @EventHandler
    public void onPlayerMention(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = String.format(event.getMessage());

        // LuckPerms Data
        CachedMetaData metaData = this.luckPerms.getPlayerAdapter(Player.class).getMetaData(player);
        String prefix = metaData.getPrefix();
        String suffix = metaData.getSuffix();

        event.setCancelled(true);

        for (Player people : Bukkit.getServer().getOnlinePlayers()) {
            if (message.contains(people.getName())) {
                String formatedMessage = message.replaceAll(people.getName(), "§5§l" + people.getName() + "§7");
                people.sendMessage(prefix + player.getName() + "§r: "+ suffix + formatedMessage);

            } else {
                people.sendMessage(prefix + player.getName() + "§r: "+ suffix + message);
            }
        }
    }
}
