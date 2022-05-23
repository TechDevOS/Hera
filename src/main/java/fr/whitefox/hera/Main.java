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
        that = this;

        getServer().getConsoleSender().sendMessage("");
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[Hera] Up !");
        getServer().getConsoleSender().sendMessage("");

        //  Management
        getCommand("alert").setExecutor(new CommandsManagement());
        getCommand("down").setExecutor(new CommandsManagement());

        //  Moderation
        getCommand("dupeip").setExecutor(new CommandsModeration());
        getCommand("sc").setExecutor(new CommandsModeration());
        getCommand("vanish").setExecutor(new VanishCommand(this));

        //  Teleportation
        getCommand("tpall").setExecutor(new CommandsTeleportation());

        //  Cosmetic
        getCommand("staffhat").setExecutor(new CommandsCosmetics());
        getCommand("p").setExecutor(new CommandsCosmetics());

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
