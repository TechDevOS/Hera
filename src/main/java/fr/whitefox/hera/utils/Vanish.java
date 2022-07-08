package fr.whitefox.hera.utils;
import fr.whitefox.hera.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Vanish {

    private static Main plugin;

    public Vanish(Main plugin) {
        this.plugin = plugin;
    }

    public static void vanish(Player player) {

        if (plugin.invisible_list.contains(player)) {
            player.removePotionEffect(PotionEffectType.INVISIBILITY);
            plugin.invisible_list.remove(player);
            player.sendMessage(ChatColor.GREEN + "§6[§9Hera§6] §aVous êtes maintenant visible pour les autres joueurs du serveur.");
            Bukkit.broadcastMessage("[§a+§r] " + player.getDisplayName());

        } else {
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 2));
            plugin.invisible_list.add(player);
            player.sendMessage(ChatColor.GREEN + "§6[§9Hera§6] §aVous êtes maintenant invisible !");
            player.setGameMode(GameMode.CREATIVE);
            Bukkit.broadcastMessage("[§4-§r] " + player.getDisplayName());
        }
    }
}