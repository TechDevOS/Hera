package fr.whitefox.hera.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ParticleRunnable extends BukkitRunnable {


    public static final Map<UUID, ParticleRunnable> runnables = new HashMap<>();

    private final Player player;

    public ParticleRunnable(Player player) {
        super();
        this.player = player;
    }

    @Override
    public void run() {
        double x = player.getLocation().getX();
        double y = player.getLocation().getY();
        double z = player.getLocation().getZ();

        for (double i = -1; i < 1; i = i + 0.1) {
            for (double u = -1; u < 1; u = u + 0.1) {
                player.getWorld().spawnParticle(Particle.REDSTONE, new Location(player.getWorld(), x - u, y - 0.1, z - i), 1);
            }
        }

    }

    @Override // this code can be in run(), its here as a possibility
    public synchronized void cancel() throws IllegalStateException {
        Bukkit.broadcastMessage("Action!");
        super.cancel();
    }

}
