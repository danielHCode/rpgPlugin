package me.daniel.rpg.core.event;

public interface RpgEngine {
    void handleEvent(Object info) throws IllegalStateException;
}
