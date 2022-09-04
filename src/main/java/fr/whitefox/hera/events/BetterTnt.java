package fr.whitefox.hera.events;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BetterTnt implements Listener {

    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event) {

        if (event.getTo() != event.getFrom()) {
            Player player = event.getPlayer();

            ItemStack tnt = new ItemStack(Material.TNT);
            ItemMeta tntMeta = tnt.getItemMeta();
            tnt.setItemMeta(tntMeta);

            if (player.getInventory().contains(tnt)) {
                player.playEffect(player.getLocation(), Effect.MOBSPAWNER_FLAMES, null);
            }
        }
    }
}
