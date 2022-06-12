package fr.whitefox.hera.commands;

import fr.whitefox.hera.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
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
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;

            double x = player.getLocation().getX();
            double y = player.getLocation().getY();
            double z = player.getLocation().getZ();

            if (plugin.invisible_list.contains(player)){
                /*for (Player people : Bukkit.getOnlinePlayers()){
                    people.showPlayer(plugin, player);
                }*/
                player.removePotionEffect(PotionEffectType.INVISIBILITY);
                plugin.invisible_list.remove(player);
                player.sendMessage(ChatColor.GREEN + "§6[§9Hera§6] §aYou are now visible to other players on the server.");
                Bukkit.broadcastMessage("[§a+§r] " + player.getDisplayName());

            }else {
                /*for (Player people : Bukkit.getOnlinePlayers()){
                    people.hidePlayer(plugin, player);
                }*/
                player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 2));
                plugin.invisible_list.add(player);
                player.sendMessage(ChatColor.GREEN + "§6[§9Hera§6] §aYou are now invisible!");
                Bukkit.broadcastMessage("[§4-§r] " + player.getDisplayName());

                /*for(int i = 0; i < 10; i++){
                    player.getWorld().spawnParticle(Particle.CLOUD, new Location(player.getWorld(), x, y, z), 10);
                }*/

            }

            return true;
        }

        return true;
    }
}
