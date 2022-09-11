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
    private final LuckPerms luckPerms;

    public PlayerChat(LuckPerms luckPerms) {
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
        String group = metaData.getPrimaryGroup();

        event.setCancelled(true);

        Main.getInstance().muteManager.checkDuration(player.getUniqueId());

        if(Main.getInstance().muteManager.isMuted(player.getUniqueId())){
            player.sendMessage("\n§cVous avez été rendu muet pour : §e" + Main.getInstance().muteManager.getReason(player.getUniqueId()) + ".\n§cFin de votre sanction dans : §e" + Main.getInstance().muteManager.getTimeLeft(player.getUniqueId()) + "\n");
        } else {
            for (Player people : Bukkit.getServer().getOnlinePlayers()) {
                if (message.contains(people.getName())) {
                    String formatedMessage = message.replaceAll(people.getName(), "§5§l" + people.getName() + "§7");
                    if (group.equalsIgnoreCase("default")) {
                        people.sendMessage("§7" + player.getName() + "§r: §7" + formatedMessage);
                    } else if(prefix == null){
                        people.sendMessage("§7" + player.getName() + "§r: " + suffix + formatedMessage);
                    } else if(suffix == null){
                        people.sendMessage(prefix + player.getName() + "§r: §7" + formatedMessage);
                    } else if(suffix == null && prefix == null){
                        people.sendMessage("§7" + player.getName() + "§r: §7" + formatedMessage);
                    } else {
                        people.sendMessage(prefix + player.getName() + "§r: " + suffix + formatedMessage);
                    }

                } else {
                    if (group.equalsIgnoreCase("default")) {
                        people.sendMessage("§7" + player.getName() + "§r: §7" + message);
                    } else if(suffix == null && prefix == null){
                        people.sendMessage("§7" + player.getName() + "§r: §7" + message);
                    } else if(prefix == null){
                        people.sendMessage("§7" + player.getName() + "§r: " + suffix + message);
                    } else if(suffix == null){
                        people.sendMessage(prefix + player.getName() + "§r: §7" + message);
                    }  else {
                        people.sendMessage(prefix + player.getName() + "§r: " + suffix + message);
                    }
                }
            }
        }
    }
}
