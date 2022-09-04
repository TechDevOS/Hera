package fr.whitefox.hera.events;

import fr.whitefox.hera.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import static fr.whitefox.hera.Main.that;

public class Fight implements Listener {

    Main plugin;

    public Fight(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {

        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            Player damager = (Player) event.getDamager();
            Player trigger = (Player) event.getEntity();

            if (!plugin.fight_list.contains(damager) || !plugin.fight_list.contains(trigger)) {
                plugin.fight_list.add(damager);
                plugin.fight_list.add(trigger);

                damager.sendMessage("§6[§9Hera §cFight§6] §cVous entrez en combat avec §b" + trigger.getName() + "§c. Ne vous déconnectez pas !");
                trigger.sendMessage("§6[§9Hera §cFight§6] §cVous entrez en combat avec §b" + damager.getName() + "§c. Ne vous déconnectez pas !");
                Bukkit.getScheduler().runTaskLater(that, () -> {
                    plugin.fight_list.add(trigger);
                    plugin.fight_list.remove(damager);
                    damager.sendMessage("§6[§9Hera §cFight§6] §cVous n'êtes plus en combat avec §b" + trigger.getName() + ".");
                    trigger.sendMessage("§6[§9Hera §cFight§6] §cVous n'êtes plus en combat avec §b" + damager.getName() + ".");
                }, 400L);
            }
        }
    }
}
