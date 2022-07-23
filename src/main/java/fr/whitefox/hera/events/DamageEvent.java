package fr.whitefox.hera.events;

import fr.whitefox.hera.utils.*;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageEvent implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event){

        if(event.getDamager() instanceof Player && event.getEntity() instanceof Player){
            Player damager = (Player) event.getDamager();
            Player trigger = (Player) event.getEntity();

            damager.sendMessage(ChatColor.GREEN + "Vous tapez " + trigger.getName());
            trigger.sendMessage(ChatColor.RED + "Attention, " + damager.getName() + "vous tape");
        }
    }
}
