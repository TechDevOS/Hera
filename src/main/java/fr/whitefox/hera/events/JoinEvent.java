package fr.whitefox.hera.events;

import fr.whitefox.hera.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;


import java.io.IOException;

public class JoinEvent implements Listener {

    Main plugin;

    public JoinEvent(Main plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent event) throws IOException {

        Player player = event.getPlayer();

        for (int i = 0; i < plugin.invisible_list.size(); i++){
            player.hidePlayer(plugin, plugin.invisible_list.get(i));
            System.out.println(plugin.invisible_list); // DEBUG
        }

        if (plugin.invisible_list.contains(player)){
            for (Player people : Bukkit.getOnlinePlayers()){
                people.hidePlayer(plugin, player);
            }
            player.sendMessage(ChatColor.GREEN + "You are still invisible on the server !");
        }


        event.setJoinMessage("");
        Bukkit.broadcastMessage("[§a+§r] " + player.getDisplayName());
        player.sendMessage("§6[§9Hera§6] §rBienvenue à toi §b" + player.getDisplayName() + "§r sur ce serveur !");
    }
}

