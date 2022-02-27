package me.daniel.rpg.core.event;

import me.daniel.rpg.core.ChestEngine;
import me.daniel.rpg.core.SpawnEngine;

import java.util.HashMap;
import java.util.Objects;

public class EventManager {

    private final HashMap<String, RpgEngine> handlers = new HashMap<>() {
        {
            put("SpawnEngine", new SpawnEngine());
            put("ChestEngine", new ChestEngine());
        }
    };

    public EventManager() {

    }

    public void handleEvent(RpgEvent event) {
        handlers.forEach((name, handler) -> {
            if (Objects.equals(name, event.getTarget())) {
                handler.handleEvent(event.getValue());
            }
        });
    }
}
