package fr.whitefox.hera.utils;

import io.github.cdimascio.dotenv.Dotenv;

import javax.net.ssl.HttpsURLConnection;
import java.io.OutputStream;
import java.net.URL;
import java.time.Instant;

public class Webhooks {

    public static void up() {

        Dotenv dotenv = null;
        dotenv = Dotenv.configure().load();

        // Config
        String tokenWebhook = dotenv.get("WEBHOOKS");

        long timestamp = Instant.now().getEpochSecond();

        // Build
        String jsonBrut = "";
        jsonBrut += "{\"content\": null,"
                + "\"embeds\": [{"
                + "\"title\": \"🟢 Plugin Up\","
                + "\"color\": 5763719,"
                + "\"fields\": [{"
                + "\"name\" : \"Date et heure\","
                + "\"value\": \"<t:" + timestamp + ":F>\","
                + "\"inline\": true}],"
                + "\"footer\": {"
                + "\"text\": \"Hera Logger\","
                + "\"icon_url\": \"https://media.discordapp.net/attachments/785951129187778614/1000094167302668398/sync.png?width=1264&height=1264\"},"
                + "\"thumbnail\": {\"url\": \"https://cdn.discordapp.com/attachments/785951129187778614/1000409927401099375/StatusON.png\""
                + "}}],"
                + "\"username\": \"Hera Logger\","
                + "\"avatar_url\": \"https://media.discordapp.net/attachments/785951129187778614/1000094167302668398/sync.png?width=1264&height=1264\","
                + "\"attachments\": []}";

        try {
            URL url = new URL(tokenWebhook);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.addRequestProperty("Content-Type", "application/json");
            con.addRequestProperty("User-Agent", "Java-DiscordWebhook-BY-Gelox_");
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            OutputStream stream = con.getOutputStream();
            stream.write(jsonBrut.getBytes());
            stream.flush();
            stream.close();
            con.getInputStream().close();
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void down() {

        Dotenv dotenv = null;
        dotenv = Dotenv.configure().load();

        // Config
        String tokenWebhook = dotenv.get("WEBHOOKS");
        long timestamp = System.currentTimeMillis() / 1000;

        // Build
        String jsonBrut = "";
        jsonBrut += "{\"content\": null,"
                + "\"embeds\": [{"
                + "\"title\": \"🔴 Plugin Down\","
                + "\"color\": 15548997,"
                + "\"fields\": [{"
                + "\"name\" : \"Date et heure\","
                + "\"value\": \"<t:" + timestamp + ":F>\","
                + "\"inline\": true}],"
                + "\"footer\": {"
                + "\"text\": \"Hera Logger\","
                + "\"icon_url\": \"https://media.discordapp.net/attachments/785951129187778614/1000094167302668398/sync.png?width=1264&height=1264\"},"
                + "\"thumbnail\": {\"url\": \"https://cdn.discordapp.com/attachments/785951129187778614/1000409926893580298/StatusOff.png\""
                + "}}],"
                + "\"username\": \"Hera Logger\","
                + "\"avatar_url\": \"https://media.discordapp.net/attachments/785951129187778614/1000094167302668398/sync.png?width=1264&height=1264\","
                + "\"attachments\": []}";

        try {
            URL url = new URL(tokenWebhook);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.addRequestProperty("Content-Type", "application/json");
            con.addRequestProperty("User-Agent", "Java-DiscordWebhook-BY-Gelox_");
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            OutputStream stream = con.getOutputStream();
            stream.write(jsonBrut.getBytes());
            stream.flush();
            stream.close();
            con.getInputStream().close();
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void registerWarn(String player, String moderator, String reason, String player_uuid, String moderator_uuid) {
        // Config
        Dotenv dotenv = null;
        dotenv = Dotenv.configure().load();

        // Config
        String tokenWebhook = dotenv.get("WEBHOOKS");
        // Build
        String jsonBrut = "";
        jsonBrut += "{\n" +
                "  \"content\": \"<:infraction:1027957601851424848> <t:" + System.currentTimeMillis() / 1000 + ":R>\",\n" +
                "  \"embeds\": [\n" +
                "    {\n" +
                "      \"description\": \"```ansi\\n\\u001b[2;37mRaison:\\u001b[0m \\u001b[2;34m\\u001b[2;33m\\u001b[2;31m" + reason + "\\u001b[0m\\u001b[2;33m\\u001b[0m\\u001b[2;34m\\u001b[0m\\n```\",\n" +
                "      \"color\": 16705372,\n" +
                "      \"fields\": [\n" +
                "        {\n" +
                "          \"name\": \"<:user:1027957581941047297> Joueur\",\n" +
                "          \"value\": \"```ansi\\n\\u001b[2;35m" + player + "\\u001b[0m\\n\\u001b[2;37m» " + player_uuid + "\\u001b[0m\\n```\",\n" +
                "          \"inline\": true\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"<:mod:1027957564291420180> Modérateur\",\n" +
                "          \"value\": \"```ansi\\n\\u001b[2;35m" + moderator + "\\u001b[0m\\n\\u001b[2;37m» " + moderator_uuid + "\\u001b[0m\\n```\",\n" +
                "          \"inline\": true\n" +
                "        }\n" +
                "      ],\n" +
                "      \"author\": {\n" +
                "        \"name\": \"" + player + " a été averti.\",\n" +
                "        \"icon_url\": \"http://cravatar.eu/avatar/" + player + "/64.png\"\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"attachments\": []\n" +
                "}";

        try {
            URL url = new URL(tokenWebhook);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.addRequestProperty("Content-Type", "application/json");
            con.addRequestProperty("User-Agent", "Java-DiscordWebhook-BY-Gelox_");
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            OutputStream stream = con.getOutputStream();
            stream.write(jsonBrut.getBytes());
            stream.flush();
            stream.close();
            con.getInputStream().close();
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void registerBan(String player, String moderator, String reason, String player_uuid, String moderator_uuid, String duration) {
        // Config
        Dotenv dotenv = null;
        dotenv = Dotenv.configure().load();

        // Config
        String tokenWebhook = dotenv.get("WEBHOOKS");
        // Build
        String jsonBrut = "";
        jsonBrut += "{\n" +
                "  \"content\": \"<:infraction:1027957601851424848> <t:" + System.currentTimeMillis() / 1000 + ":R>\",\n" +
                "  \"embeds\": [\n" +
                "    {\n" +
                "      \"description\": \"```ansi\\n\\u001b[2;37mRaison:\\u001b[0m \\u001b[2;31m" + reason + "\\u001b[0m\\n\\u001b[2;37mDurée:\\u001b[0m \\u001b[2;31m" + duration + "\\u001b[0m\\n```\",\n" +
                "      \"color\": 15548997,\n" +
                "      \"fields\": [\n" +
                "        {\n" +
                "          \"name\": \"<:user:1027957581941047297> Joueur\",\n" +
                "          \"value\": \"```ansi\\n\\u001b[2;35m" + player + "\\u001b[0m\\n\\u001b[2;37m» " + player_uuid +"\\u001b[0m\\n```\",\n" +
                "          \"inline\": true\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"<:mod:1027957564291420180> Modérateur\",\n" +
                "          \"value\": \"```ansi\\n\\u001b[2;35m" + moderator + "\\u001b[0m\\n\\u001b[2;37m» " + moderator_uuid + "\\u001b[0m\\n```\",\n" +
                "          \"inline\": true\n" +
                "        }\n" +
                "      ],\n" +
                "      \"author\": {\n" +
                "        \"name\": \"" + player + " a été banni.\",\n" +
                "        \"icon_url\": \"http://cravatar.eu/avatar/" + player + "/64.png\"\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"attachments\": []\n" +
                "}";

        try {
            URL url = new URL(tokenWebhook);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.addRequestProperty("Content-Type", "application/json");
            con.addRequestProperty("User-Agent", "Java-DiscordWebhook-BY-Gelox_");
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            OutputStream stream = con.getOutputStream();
            stream.write(jsonBrut.getBytes());
            stream.flush();
            stream.close();
            con.getInputStream().close();
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void registerUnban(String player, String moderator, String player_uuid, String moderator_uuid) {
        // Config
        Dotenv dotenv = null;
        dotenv = Dotenv.configure().load();

        // Config
        String tokenWebhook = dotenv.get("WEBHOOKS");
        // Build
        String jsonBrut = "";
        jsonBrut += "{\n" +
                "  \"content\": \"<:infraction:1027957601851424848> <t:" + System.currentTimeMillis() / 1000 + ":R>\",\n" +
                "  \"embeds\": [\n" +
                "    {\n" +
                "      \"color\": 5763719,\n" +
                "      \"fields\": [\n" +
                "        {\n" +
                "          \"name\": \"<:user:1027957581941047297> Joueur\",\n" +
                "          \"value\": \"```ansi\\n\\u001b[2;35m" + player + "\\u001b[0m\\n\\u001b[2;37m» " + player_uuid + "\\u001b[0m\\n```\",\n" +
                "          \"inline\": true\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"<:mod:1027957564291420180> Modérateur\",\n" +
                "          \"value\": \"```ansi\\n\\u001b[2;35m" + moderator + "\\u001b[0m\\n\\u001b[2;37m» " + moderator_uuid + "\\u001b[0m\\n```\",\n" +
                "          \"inline\": true\n" +
                "        }\n" +
                "      ],\n" +
                "      \"author\": {\n" +
                "        \"name\": \"" + player + " a été débanni.\",\n" +
                "        \"icon_url\": \"http://cravatar.eu/avatar/" + player_uuid + "/64.png\"\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"attachments\": []\n" +
                "}";

        try {
            URL url = new URL(tokenWebhook);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.addRequestProperty("Content-Type", "application/json");
            con.addRequestProperty("User-Agent", "Java-DiscordWebhook-BY-Gelox_");
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            OutputStream stream = con.getOutputStream();
            stream.write(jsonBrut.getBytes());
            stream.flush();
            stream.close();
            con.getInputStream().close();
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void registerUnmute(String player, String moderator, String player_uuid, String moderator_uuid) {
        // Config
        Dotenv dotenv = null;
        dotenv = Dotenv.configure().load();

        // Config
        String tokenWebhook = dotenv.get("WEBHOOKS");
        // Build
        String jsonBrut = "";
        jsonBrut += "{\n" +
                "  \"content\": \"<:infraction:1027957601851424848> <t:" + System.currentTimeMillis() / 1000 + ":R>\",\n" +
                "  \"embeds\": [\n" +
                "    {\n" +
                "      \"color\": 5763719,\n" +
                "      \"fields\": [\n" +
                "        {\n" +
                "          \"name\": \"<:user:1027957581941047297> Joueur\",\n" +
                "          \"value\": \"```ansi\\n\\u001b[2;35m" + player + "\\u001b[0m\\n\\u001b[2;37m» " + player_uuid + "\\u001b[0m\\n```\",\n" +
                "          \"inline\": true\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"<:mod:1027957564291420180> Modérateur\",\n" +
                "          \"value\": \"```ansi\\n\\u001b[2;35m" + moderator + "\\u001b[0m\\n\\u001b[2;37m» " + moderator_uuid + "\\u001b[0m\\n```\",\n" +
                "          \"inline\": true\n" +
                "        }\n" +
                "      ],\n" +
                "      \"author\": {\n" +
                "        \"name\": \"" + player + " a été unmute.\",\n" +
                "        \"icon_url\": \"http://cravatar.eu/avatar/" + player_uuid + "/64.png\"\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"attachments\": []\n" +
                "}";

        try {
            URL url = new URL(tokenWebhook);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.addRequestProperty("Content-Type", "application/json");
            con.addRequestProperty("User-Agent", "Java-DiscordWebhook-BY-Gelox_");
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            OutputStream stream = con.getOutputStream();
            stream.write(jsonBrut.getBytes());
            stream.flush();
            stream.close();
            con.getInputStream().close();
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void registerMute(String player, String moderator, String reason, String player_uuid, String moderator_uuid, String duration) {
        // Config
        Dotenv dotenv = null;
        dotenv = Dotenv.configure().load();

        // Config
        String tokenWebhook = dotenv.get("WEBHOOKS");
        // Build
        String jsonBrut = "";
        jsonBrut += "{\n" +
                "  \"content\": \"<:infraction:1027957601851424848> <t:" + System.currentTimeMillis() / 1000 + ":R>\",\n" +
                "  \"embeds\": [\n" +
                "    {\n" +
                "      \"description\": \"```ansi\\n\\u001b[2;37mRaison:\\u001b[0m \\u001b[2;31m" + reason + "\\u001b[0m\\n\\u001b[2;37mDurée:\\u001b[0m \\u001b[2;31m" + duration + "\\u001b[0m\\n```\",\n" +
                "      \"color\": 5793266,\n" +
                "      \"fields\": [\n" +
                "        {\n" +
                "          \"name\": \"<:user:1027957581941047297> Joueur\",\n" +
                "          \"value\": \"```ansi\\n\\u001b[2;35m" + player + "\\u001b[0m\\n\\u001b[2;37m» " + player_uuid +"\\u001b[0m\\n```\",\n" +
                "          \"inline\": true\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"<:mod:1027957564291420180> Modérateur\",\n" +
                "          \"value\": \"```ansi\\n\\u001b[2;35m" + moderator + "\\u001b[0m\\n\\u001b[2;37m» " + moderator_uuid + "\\u001b[0m\\n```\",\n" +
                "          \"inline\": true\n" +
                "        }\n" +
                "      ],\n" +
                "      \"author\": {\n" +
                "        \"name\": \"" + player + " a été rendu muet.\",\n" +
                "        \"icon_url\": \"http://cravatar.eu/avatar/" + player + "/64.png\"\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"attachments\": []\n" +
                "}";

        try {
            URL url = new URL(tokenWebhook);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.addRequestProperty("Content-Type", "application/json");
            con.addRequestProperty("User-Agent", "Java-DiscordWebhook-BY-Gelox_");
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            OutputStream stream = con.getOutputStream();
            stream.write(jsonBrut.getBytes());
            stream.flush();
            stream.close();
            con.getInputStream().close();
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
