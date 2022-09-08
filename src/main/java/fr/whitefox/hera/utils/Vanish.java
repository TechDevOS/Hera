package fr.whitefox.hera.utils;

import fr.whitefox.hera.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Vanish {

    public static void vanish(Player player) {

        if (Main.getInstance().invisible_list.contains(player)) {
            player.removePotionEffect(PotionEffectType.INVISIBILITY);
            Main.getInstance().invisible_list.remove(player);
            player.sendMessage(ChatColor.GREEN + "§6[§9Hera§6] §aVous êtes maintenant visible pour les autres joueurs du serveur.");
            Bukkit.broadcastMessage("[§a+§r] " + player.getDisplayName());

        } else {
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 2));
            Main.getInstance().invisible_list.add(player);
            player.sendMessage(ChatColor.GREEN + "§6[§9Hera§6] §aVous êtes maintenant invisible !");
            player.setGameMode(GameMode.CREATIVE);
            Bukkit.broadcastMessage("[§4-§r] " + player.getDisplayName());
        }
    }
}