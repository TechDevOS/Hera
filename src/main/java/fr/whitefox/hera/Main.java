package fr.whitefox.hera;

import fr.whitefox.hera.commands.*;
import fr.whitefox.hera.debug.DebugCommand;
import fr.whitefox.hera.events.*;
import fr.whitefox.hera.mysql.MuteManager;
import fr.whitefox.hera.mysql.MySQL;
import fr.whitefox.hera.mysql.PlayerInfos;
import fr.whitefox.hera.mysql.BanManager;
import fr.whitefox.hera.utils.Webhooks;
import io.github.cdimascio.dotenv.Dotenv;
import net.luckperms.api.LuckPerms;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;


public class Main extends JavaPlugin {

    private static Main instance;
    public ArrayList<Player> invisible_list = new ArrayList<>();
    public ArrayList<Player> fight_list = new ArrayList<>();
    public ArrayList<Player> freeze_list = new ArrayList<>();
    public ArrayList<Player> fly_list = new ArrayList<>();
    public ArrayList<Player> pday_list = new ArrayList<>();
    public ArrayList<Player> pnight_list = new ArrayList<>();
    public MySQL mysql = new MySQL();
    public PlayerInfos playerInfos = new PlayerInfos();
    public BanManager banManager = new BanManager();
    public MuteManager muteManager = new MuteManager();

    private LuckPerms luckPerms;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[Hera] Starting...");

        instance = this;
        this.luckPerms = getServer().getServicesManager().load(LuckPerms.class);

        Dotenv dotenv = null;
        dotenv = Dotenv.configure().load();

        int port = Integer.parseInt(dotenv.get("SQL_PORT"));
        String host = dotenv.get("SQL_HOST");
        String tablename = dotenv.get("SQL_TABLENAME");
        String username = dotenv.get("SQL_USERNAME");
        String password = dotenv.get("SQL_PASSWORD");

        mysql.connect(host, port, tablename, username, password);

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
        mysql.disconnect();

        if (this.getConfig().getBoolean("WebhooksDiscord.activate")) {
            Webhooks.down();
        }

        getServer().getConsoleSender().sendMessage(" ");
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[Hera] Goodbye :)");
        getServer().getConsoleSender().sendMessage(" ");
    }
}