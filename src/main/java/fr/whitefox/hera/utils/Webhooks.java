package fr.whitefox.hera.utils;

import fr.whitefox.hera.Main;
import org.bukkit.entity.Player;

import javax.net.ssl.HttpsURLConnection;
import java.io.OutputStream;
import java.net.URL;
import java.time.Instant;

public class Webhooks {

    private static Main plugin;

    public Webhooks(Main plugin) {
        this.plugin = plugin;
    }

    public static void up(){
        // Config
        // String tokenWebhook = plugin.getConfig().getString("WebhooksDiscord.PluginUpDown");
        String tokenWebhook = "https://discord.com/api/webhooks/1002666275559972964/WMaYGyjbzbcVzFyA6qwe-aTG4IL_9gTgfXPE18hHtsqCIW6J33DpWroKHV1ORpzvcIPT";
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

    public static void down(){
        // Config
        String tokenWebhook = plugin.getConfig().getString("WebhooksDiscord.PluginUpDown");
        long timestamp = Instant.now().getEpochSecond();

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
}
