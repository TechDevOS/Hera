package fr.whitefox.hera.events;

import fr.whitefox.hera.Main;
import fr.whitefox.hera.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import static fr.whitefox.hera.Main.that;


public class DamageEvent implements Listener {

    private Main plugin;

    public DamageEvent(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {

        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            Player damager = (Player) event.getDamager();
            Player trigger = (Player) event.getEntity();
            Double damage = event.getDamage();

            Bukkit.getScheduler().runTaskLater(that, () -> {
                if (plugin.getConfig().getBoolean("WebhooksDiscord.activate")) {
                    Webhooks.damage(damager, trigger, damage);
                }
            }, 20L);
        }
    }
}