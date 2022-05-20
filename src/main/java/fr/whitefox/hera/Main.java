package fr.whitefox.hera;

import fr.whitefox.hera.commands.*;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;


public final class Main extends JavaPlugin {

    public static Main that;

    @Override
    public void onEnable() {
        that = this;

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "----------------------------------------");
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Hera Plugin started");
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "----------------------------------------");

        //  Management
        getCommand("alert").setExecutor(new CommandsManagement());
        getCommand("down").setExecutor(new CommandsManagement());

        //  Moderation
        getCommand("clean").setExecutor(new CommandsModeration());

        //  Teleportation
        getCommand("tpall").setExecutor(new CommandsTeleportation());
        getCommand("spawn").setExecutor(new CommandsTeleportation());

        //  Cosmetic
        getCommand("staffhat").setExecutor(new CommandsCosmetics());
        getCommand("p").setExecutor(new CommandsCosmetics());

        //  Dev
        getCommand("update").setExecutor(new CommandsTestPlugin());



        getServer().getPluginManager().registerEvents(new HeraListeners(), this);

    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "----------------------------------------");
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "Hera Plugin stopped");
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "----------------------------------------");
        that = null;
    }
}
