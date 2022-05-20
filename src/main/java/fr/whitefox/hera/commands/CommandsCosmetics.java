package fr.whitefox.hera.commands;

import fr.whitefox.hera.Main;

import fr.whitefox.hera.utils.ParticleRunnable;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;



public class CommandsCosmetics implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {

            Player player = (Player) sender;

            if (cmd.getName().equalsIgnoreCase("staffhat")) {


                // Give head staff
                ItemStack staffhat = new ItemStack(Material.STAINED_GLASS, 1, (byte) 4);
                ItemMeta customW = staffhat.getItemMeta();
                customW.setDisplayName("§5 §Staff Helmet");
                customW.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
                customW.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                staffhat.setItemMeta(customW);

                player.getInventory().setHelmet(staffhat);
                player.sendMessage("§aVous avez reçu votre StaffHat");

                player.updateInventory();

                return true;
            }

            if (cmd.getName().equalsIgnoreCase("p")) {

                ParticleRunnable particle = ParticleRunnable.runnables.get(player.getUniqueId());

                //Est-ce qu'on a déjà des particules sur cette personne ?
                if(particle == null) {

                    //Si non on en met
                    particle = new ParticleRunnable(player);
                    //Puis on lance
                    Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.that, particle, 0, 1);
                    //...et on ajoute dans notre dictionaire
                    ParticleRunnable.runnables.put(player.getUniqueId(), particle);
                } else {
                    //Si oui on arrête
                    particle.cancel();
                    //...et on enlève du dictionaire
                    ParticleRunnable.runnables.remove(player.getUniqueId());
                }


                return true;
            }

        }

        return false;
    }
}


// Bukkit.getScheduler().runTaskTimer(that, new ParticleRunnable(player), 0,  1);