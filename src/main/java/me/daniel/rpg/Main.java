package me.daniel.rpg;

import me.daniel.rpg.core.Registry;
import me.daniel.rpg.core.SpawnEngine;
import me.daniel.rpg.core.event.EventManager;
import me.daniel.rpg.core.event.RpgEvent;
import me.daniel.rpg.core.event.RpgEventType;
import me.daniel.rpg.core.event.WorkerState;
import me.daniel.rpg.listener.Listener;
import me.daniel.rpg.listener.SpawnListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class Main extends JavaPlugin {

    public HashMap<UUID, Boolean> build = new HashMap<>();

    private static Main main;

    public EventManager manager;

    public static Main getPlugin() {
        return main;
    }

    @Override
    public void onEnable() {
        main = this;
        // Plugin startup logic
        System.out.println("hello");


        Registry.loadConfig();

        Registry.commands.forEach((name, executor) -> {
            try {
                getCommand(name).setExecutor(executor);
            } catch (Exception ignored) {}
        });

        Bukkit.getPluginManager().registerEvents(new Listener(), this);
        Bukkit.getPluginManager().registerEvents(new SpawnListener(), this);

        manager = new EventManager();

        manager.handleEvent(new RpgEvent("SpawnEngine", RpgEventType.SPAWN, WorkerState.ENABlED, 1));

    }


    @Override
    public void onDisable() {
        // Plugin shutdown lo
        Registry.config.put("spawn", false);
        manager.handleEvent(new RpgEvent("SpawnEngine", RpgEventType.SPAWN, WorkerState.DISABLED, 1));
    }
}
