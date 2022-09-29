package fr.whitefox.hera.utils;

import fr.whitefox.hera.Main;

public class Automod {

    private static Main main = Main.getInstance();

    public static boolean isGood(String message) {
        if (!message.contains(" ")) {
            return !main.automod.contains(message);
        } else {
            String[] parts = message.split(" ");
            for (String part : parts) {
                if (main.automod.contains(part)) {
                    return false;
                }
            }
        }
        return true;
    }
}
