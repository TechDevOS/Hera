package fr.whitefox.hera.events;

import fr.whitefox.hera.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathEvent implements Listener {

    private Main plugin;

    public DeathEvent(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void DeathPlayer(PlayerDeathEvent event){

        Player player = event.getEntity();

        if(player.getKiller() instanceof Player){
            event.setDeathMessage("§6[§9Hera§6] §aLe joueur §b" + player.getName() + "§a a été éliminé par §b" + player.getKiller().getName() + ".");
        } else{
            event.setDeathMessage("§6[§9Hera§6] §aLe joueur §b" + player.getName() + "§a est mort.");
        }
    }
}
