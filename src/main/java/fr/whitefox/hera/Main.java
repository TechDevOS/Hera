package fr.whitefox.hera;

import fr.whitefox.hera.commands.*;
import fr.whitefox.hera.debug.*;
import fr.whitefox.hera.events.*;
import fr.whitefox.hera.utils.*;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;


public final class Main extends JavaPlugin {

    public ArrayList<Player> invisible_list = new ArrayList<>();

    public static Main that;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        new AntiVPN(this);
        new Vanish(this);
        new Webhooks(this);

        getServer().getConsoleSender().sendMessage(" ");
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[Hera] Up !");
        getServer().getConsoleSender().sendMessage(" ");

        if (this.getConfig().getBoolean("WebhooksDiscord.activate")) {
            Webhooks.up();
        }

        getCommand("dupeip").setExecutor(new DupeipCommand());
        getCommand("vanish").setExecutor(new VanishCommand(this));
        getCommand("tpall").setExecutor(new TeleportationCommand());
        getCommand("gm").setExecutor(new GamemodeCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("feed").setExecutor(new FeedCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("s").setExecutor(new StopCommand());
        getCommand("sun").setExecutor(new WeatherCommand());
        getCommand("rain").setExecutor(new WeatherCommand());
        getCommand("thunder").setExecutor(new WeatherCommand());
        getCommand("wl").setExecutor(new WhitelistCommand(this));
        getCommand("antivpn").setExecutor(new AntiVPNCommand(this));
        getCommand("msg").setExecutor(new MessagesCommand());
        getCommand("debug").setExecutor(new DebugCommand());

        getServer().getPluginManager().registerEvents(new JoinQuitEvent(this), this);
        getServer().getPluginManager().registerEvents(new BetterInvisibility(), this);
        getServer().getPluginManager().registerEvents(new BetterTnt(), this);
        getServer().getPluginManager().registerEvents(new PlayerChat(), this);
        getServer().getPluginManager().registerEvents(new DamageEvent(), this);
    }

    @Override
    public void onDisable() {
        that = null;

        if (this.getConfig().getBoolean("WebhooksDiscord.activate")) {
            Webhooks.down();
        }

        getServer().getConsoleSender().sendMessage(" ");
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[Hera] Goodbye :)");
        getServer().getConsoleSender().sendMessage(" ");
    }
}