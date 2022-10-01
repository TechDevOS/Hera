package fr.whitefox.hera.utils;

import fr.whitefox.hera.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Automod {

    private static final Main main = Main.getInstance();

    public static boolean isFlood(Player player) {
        return main.antiflood_list.contains(player);
    }

    public static void checkFlood(Player player) {
        if (!main.antiflood_list.contains(player)) {
            main.antiflood_list.add(player);
            Bukkit.getScheduler().runTaskLater(main, () -> main.antiflood_list.remove(player), 40L);
        }
    }

    public static boolean isGood(String message) {
        if (!message.toLowerCase().contains(" ")) {
            return !main.automod.contains(message.toLowerCase());
        } else {
            String[] parts = message.split(" ");
            for (String part : parts) {
                if (main.automod.contains(part.toLowerCase())) {
                    return false;
                }
            }
        }

        return true;
    }
}
