package me.daniel.rpg.core;

import me.daniel.rpg.Main;
import me.daniel.rpg.core.event.RpgEngine;
import me.daniel.rpg.core.event.WorkerState;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.Callable;

public class SpawnEngine implements Runnable, RpgEngine {

    private Random rd = new Random();

    private Thread worker;

    public static boolean stop = false;

    public static int getMob() {
        var i = 0;
        i+= Math.round(Math.random() * 1.2 - 0.1);
        i+= Math.round(Math.random() *1.2 - 0.1);
        i+= Math.round(Math.random() *1.2 - 0.1);
        if (i == 0) return 1;
        return Math.min(i, 3);
    }

    private void spawn(Location location) {
        EntityType type = EntityType.CHICKEN;
        switch (location.getChunk().getChunkSnapshot(true, true, true).getBiome((int) (location.getBlockX() - (location.getChunk().getX() * 16)), location.getBlockY(), (int) (location.getBlockZ()- location.getChunk().getZ() * 16))) {
            case DARK_FOREST -> {
                switch (getMob()) {
                    case 1 -> type = EntityType.PILLAGER;
                    case 2 -> type = EntityType.VINDICATOR;
                    case 3 -> type = EntityType.EVOKER;
                }
            }
            case BEACH -> {
                switch (getMob()) {
                    case 1 -> type = EntityType.DROWNED;
                    case 2 -> type = EntityType.TURTLE;
                    case 3 -> type = EntityType.GUARDIAN;
                }
            }
            case MOUNTAINS -> {
                switch (getMob()) {
                    case 1 -> type = EntityType.GOAT;
                    case 2 -> type = EntityType.STRAY;
                    case 3 -> type = EntityType.WOLF;
                }
            }
        }
        EntityType finalType = type;
        Bukkit.getScheduler().callSyncMethod(Main.getPlugin(), new Callable<Object>() {
            @Override
            public Object call() {
                Objects.requireNonNull(Bukkit.getWorld("world")).spawnEntity(location, finalType, true);
                return null;
            }
        });
    }

    private int getMulti() {
        if (rd.nextBoolean())
            return 1;
        else
            return -1;
    }

    private Location findLocation(Player p) {
        if (!Registry.containsChunk(p.getLocation().getChunk())) {
            int count = 0;
            while (count < 10) {
                count ++;
                double x = (double) (p.getLocation().getBlockX() + (Math.round(Math.random() * 30 * getMulti())));
                double z = (double) (p.getLocation().getBlockZ() - (Math.round(Math.random() * 30 * getMulti())));

                var location = new Location(p.getWorld(), x, p.getLocation().getY(), z);
                location = new Location(p.getWorld(),x,location.getChunk().getChunkSnapshot().getHighestBlockYAt(location.getBlockX() - location.getChunk().getX() * 16, location.getBlockZ()- location.getChunk().getZ() * 16) + 1, z );

                if (location.getBlock().isEmpty() && location != p.getLocation() && !Registry.containsChunk(location.getChunk())) {
                    Objects.requireNonNull(Bukkit.getWorld("world")).spawnParticle(Particle.BARRIER, location, 1);
                    return location;
                }
            }
        }
        return null;
    }

    @Override
    public void run() {
        while (Registry.config.get("spawn") instanceof Boolean && (Boolean) Registry.config.get("spawn") && !stop) {

            for (Player p :Bukkit.getOnlinePlayers()) {
                var loc = findLocation(p);
                if (loc == null) {
                    p.sendMessage("Could not find a spot to spawn an enemy");
                    continue;
                }
                spawn(loc);
                p.sendMessage("spawned");
            }

            if (SpawnEngine.stop) return;

            try {
                Thread.sleep(Math.round(Math.random()* 40000));
            } catch (InterruptedException e) {
                Bukkit.broadcastMessage("Spawn thread failed sleeping and is forced to shut down");
                return;
            }
        }
    }

    @Override
    public void handleEvent(Object value) {
        if (value instanceof WorkerState) {
            var bool = (WorkerState) value;
            if (bool == WorkerState.ENABlED) {
                if (worker == null) {
                    stop = false;
                    worker = new Thread(this);
                    worker.start();
                }
            } else {
                stop = true;
                worker = null;
            }
            return;
        }
        throw new IllegalStateException("Value must be boolean");
    }
}
