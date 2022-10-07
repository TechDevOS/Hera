package fr.whitefox.hera.utils;

import fr.whitefox.hera.Main;
import fr.whitefox.hera.db.PlayerInfos;

public class DiscordLogger {

    private static final Main main = Main.getInstance();

    public static void register(String player, String moderator, String duration, String reason, String type) {

        if (main.getConfig().getBoolean("WebhooksDiscord.activate")) {
            String moderator_uuid;
            String player_uuid = String.valueOf(PlayerInfos.getUUID(player));

            if (moderator.equalsIgnoreCase("CONSOLE")) {
                moderator_uuid = "none";
            } else
                moderator_uuid = String.valueOf(PlayerInfos.getUUID(moderator));

            if (type.equalsIgnoreCase("warn")) {
                Webhooks.registerWarn(player, moderator, reason, player_uuid, moderator_uuid);
            } else if (type.equalsIgnoreCase("ban")) {
                Webhooks.registerBan(player, moderator, reason, player_uuid, moderator_uuid, duration);
            } else if (type.equalsIgnoreCase("unban")) {
                Webhooks.registerUnban(player, moderator, player_uuid, moderator_uuid);
            } else if (type.equalsIgnoreCase("unmute")) {
                Webhooks.registerUnmute(player, moderator, player_uuid, moderator_uuid);
            } else if (type.equalsIgnoreCase("mute")) {
                Webhooks.registerMute(player, moderator, reason, player_uuid, moderator_uuid, duration);
            }
        }
    }
}

