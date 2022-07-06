package fr.whitefox.hera.commands;

import fr.whitefox.hera.Main;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class VanishCommand implements CommandExecutor {

    Main plugin;

    public VanishCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (cmd.getName().equalsIgnoreCase("vanish")) {

                if (args.length == 0) {
                    double x = player.getLocation().getX();
                    double y = player.getLocation().getY();
                    double z = player.getLocation().getZ();

                    if (plugin.invisible_list.contains(player)) {
                        player.removePotionEffect(PotionEffectType.INVISIBILITY);
                        plugin.invisible_list.remove(player);
                        player.sendMessage(ChatColor.GREEN + "§6[§9Hera§6] §aYou are now visible to other players on the server.");
                        Bukkit.broadcastMessage("[§a+§r] " + player.getDisplayName());

                    } else {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 2));
                        plugin.invisible_list.add(player);
                        player.sendMessage(ChatColor.GREEN + "§6[§9Hera§6] §aYou are now invisible!");
                        player.setGameMode(GameMode.CREATIVE);
                        Bukkit.broadcastMessage("[§4-§r] " + player.getDisplayName());
                    }

                    return true;
                }

                if (args.length >= 1) {
                    Player target = Bukkit.getServer().getPlayer(args[0]);

                    if (target == null) {
                        player.sendMessage(ChatColor.RED + "Le joueur n'existe pas ou n'est pas connecté !");
                        return true;
                    }

                    double x = target.getLocation().getX();
                    double y = target.getLocation().getY();
                    double z = target.getLocation().getZ();

                    if (plugin.invisible_list.contains(target)) {
                        target.removePotionEffect(PotionEffectType.INVISIBILITY);
                        plugin.invisible_list.remove(target);
                        target.sendMessage(ChatColor.GREEN + "§6[§9Hera§6] §aVous êtes maintenant visible pour les autres membres du serveur.");
                        player.sendMessage(ChatColor.GREEN + "§6[§9Hera§6] §aLe joueur §c" + target.getName() +"§a est maintenant visible pour les autres membres du serveur.");
                        Bukkit.broadcastMessage("[§a+§r] " + target.getDisplayName());

                    } else {
                        target.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 2));
                        plugin.invisible_list.add(target);
                        target.sendMessage(ChatColor.GREEN + "§6[§9Hera§6] §aVous êtes maintenant invisible !");
                        player.sendMessage(ChatColor.GREEN + "§6[§9Hera§6] §aLe joueur §c" + target.getName() +"§a est maintenant invisible.");
                        target.setGameMode(GameMode.CREATIVE);
                        Bukkit.broadcastMessage("[§4-§r] " + target.getDisplayName());
                    }

                    return true;
                }
            }

            return true;
        }

        return true;
    }
}
