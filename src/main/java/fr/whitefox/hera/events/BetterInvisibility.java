package fr.whitefox.hera.events;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffectType;

public class BetterInvisibility implements Listener {

    @EventHandler
    public void OnEntityDamageByEntity(EntityDamageByEntityEvent event) {

        if(event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            Player player = (Player) event.getEntity();
            Entity damager = event.getDamager();

            if (player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
                player.removePotionEffect(PotionEffectType.INVISIBILITY);
                player.sendMessage(ChatColor.GREEN + "Vous êtes de nouveau visible !");
                damager.sendMessage(ChatColor.GREEN + "Oh ! Vous avez trouvé " + player.getName() + " !");

            }
        }
    }
}
