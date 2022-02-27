package me.daniel.rpg.core;

import me.daniel.rpg.command.FlySpeedCommand;
import me.daniel.rpg.command.SpawnCommand;
import me.daniel.rpg.command.WarpCommand;
import me.daniel.rpg.log.Logger;
import me.daniel.rpg.command.BuildCommand;
import me.daniel.rpg.file.ConfigManager;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;

import java.util.ArrayList;
import java.util.HashMap;


public class Registry {


    public static HashMap<String, Object> config;

    public static ArrayList<Chunk> mobChunks = new ArrayList<>();

    public static HashMap<String, Location> warps = new HashMap<>() {
    };

    public static final HashMap<String, CommandExecutor> commands = new HashMap<>() {
        {
            put("build", new BuildCommand());
            put("warp", new WarpCommand());
            put("spawn", new SpawnCommand());
            put("flyspeed", new FlySpeedCommand());
        }
    };

    public static void registerWarp(String name, Location l) {
        warps.put(name, l);
    }

    public static void loadConfig() {
        config = new ConfigManager("./plugins/config.yml").parseConfig();
        if (config == null) {
            Logger.log("Config not properly loaded");
        }
    }

    public static boolean containsChunk(Chunk c) {
        for (Chunk chunk : mobChunks) {
            if (chunk.equals(c)) {
                return true;
            }
        }
        return false;
    }
}
