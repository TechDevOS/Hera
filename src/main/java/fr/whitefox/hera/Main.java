package fr.whitefox.hera;

import fr.whitefox.hera.commands.*;
import fr.whitefox.hera.events.JoinEvent;
import fr.whitefox.hera.events.QuitEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;


public final class Main extends JavaPlugin {

    public ArrayList<Player> invisible_list = new ArrayList<>();

    public static Main that;

    @Override
    public void onEnable() {

        getServer().getConsoleSender().sendMessage("");
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[Hera] Up !");
        getServer().getConsoleSender().sendMessage("");

        getCommand("dupeip").setExecutor(new CommandsModeration());
        getCommand("vanish").setExecutor(new VanishCommand(this));
        getCommand("tpall").setExecutor(new CommandsTeleportation());
        getCommand("gm").setExecutor(new GamemodeCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("feed").setExecutor(new FeedCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("stop").setExecutor(new StopCommand());
        // getCommand("p").setExecutor(new CommandsCosmetics());

        //  Dev
        getCommand("update").setExecutor(new CommandsTestPlugin());

        getServer().getPluginManager().registerEvents(new JoinEvent(this), this);
        getServer().getPluginManager().registerEvents(new QuitEvent(), this);
    }

    @Override
    public void onDisable() {
        that = null;

        getServer().getConsoleSender().sendMessage("");
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[Hera] Goodbye :)");
        getServer().getConsoleSender().sendMessage("");
    }
}