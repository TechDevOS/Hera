package fr.whitefox.hera;

import fr.whitefox.hera.commands.*;
import fr.whitefox.hera.debug.DebugCommand;
import fr.whitefox.hera.events.*;
import fr.whitefox.hera.db.*;
import fr.whitefox.hera.utils.Webhooks;
import io.github.cdimascio.dotenv.Dotenv;
import net.luckperms.api.LuckPerms;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.*;
import java.util.ArrayList;


public class Main extends JavaPlugin {

    private static Main instance;
    public ArrayList<Player> invisible_list = new ArrayList<>();
    public ArrayList<Player> fight_list = new ArrayList<>();
    public ArrayList<Player> freeze_list = new ArrayList<>();
    public ArrayList<Player> fly_list = new ArrayList<>();
    public ArrayList<Player> pday_list = new ArrayList<>();
    public ArrayList<Player> pnight_list = new ArrayList<>();
    public SQLite sqlite = new SQLite();
    public PlayerInfos playerInfos = new PlayerInfos();
    public BanManager banManager = new BanManager();
    public MuteManager muteManager = new MuteManager();
    public HistoryManager historyManager = new HistoryManager();

    private LuckPerms luckPerms;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[Hera] Starting...");

        instance = this;
        this.luckPerms = getServer().getServicesManager().load(LuckPerms.class);

        try {
            sqlite.connect("Hera.db");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            sqlite.initTables();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        saveDefaultConfig();

        if (this.getConfig().getBoolean("WebhooksDiscord.activate")) {
            Webhooks.up();
        }

        getCommand("dupeip").setExecutor(new DupeipCommand());
        getCommand("vanish").setExecutor(new VanishCommand());
        getCommand("tpall").setExecutor(new TeleportationCommand());
        getCommand("gm").setExecutor(new GamemodeCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("feed").setExecutor(new FeedCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("s").setExecutor(new StopCommand());
        getCommand("sun").setExecutor(new WeatherCommand());
        getCommand("rain").setExecutor(new WeatherCommand());
        getCommand("thunder").setExecutor(new WeatherCommand());
        getCommand("wl").setExecutor(new WhitelistCommand());
        getCommand("antivpn").setExecutor(new AntiVPNCommand());
        getCommand("debug").setExecutor(new DebugCommand());
        getCommand("freeze").setExecutor(new FreezeCommand());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("day").setExecutor(new TimeCommand());
        getCommand("night").setExecutor(new TimeCommand());
        getCommand("pday").setExecutor(new TimeCommand());
        getCommand("pnight").setExecutor(new TimeCommand());
        getCommand("kick").setExecutor(new KickCommand());
        getCommand("ban").setExecutor(new BanCommand());
        getCommand("unban").setExecutor(new BanCommand());
        getCommand("mute").setExecutor(new MuteCommand());
        getCommand("unmute").setExecutor(new MuteCommand());
        getCommand("inf").setExecutor(new InfCommand());
        getCommand("warn").setExecutor(new WarnCommand());
        getCommand("back").setExecutor(new BackCommand());

        getServer().getPluginManager().registerEvents(new JoinQuitEvent(), this);
        getServer().getPluginManager().registerEvents(new Fight(), this);
        getServer().getPluginManager().registerEvents(new PlayerChat(this.luckPerms), this);
        getServer().getPluginManager().registerEvents(new BetterInvisibility(), this);
        getServer().getPluginManager().registerEvents(new BetterTnt(), this);
        getServer().getPluginManager().registerEvents(new DeathEvent(), this);
        getServer().getPluginManager().registerEvents(new FreezeEvent(), this);

        getServer().getConsoleSender().sendMessage("");
        getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "[Hera] Up !");
        getServer().getConsoleSender().sendMessage("");
    }

    @Override
    public void onDisable() {
        instance = null;
        sqlite.disconnect();

        if (this.getConfig().getBoolean("WebhooksDiscord.activate")) {
            Webhooks.down();
        }

        getServer().getConsoleSender().sendMessage(" ");
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[Hera] Goodbye :)");
        getServer().getConsoleSender().sendMessage(" ");
    }
}