package me.daniel.rpg.listener;

import me.daniel.rpg.core.Registry;
import me.daniel.rpg.log.MessageUtil;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class SpawnListener implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent ev) {//Register new chunks
        if (ev.getAction().equals(Action.LEFT_CLICK_AIR) && ev.getPlayer().hasPermission("rpg.spawn.admin")) {
            if (Objects.requireNonNull(ev.getItem()).getType().equals(Material.ACACIA_BOAT)) {
                var chunk = ev.getPlayer().getLocation().getChunk();
                if (Registry.containsChunk(chunk)) {
                    Registry.mobChunks.remove(chunk);
                    MessageUtil.notifyPlayer(ev.getPlayer(), "§4Chunk removed");
                    return;
                }
                Registry.mobChunks.add(chunk);
                MessageUtil.notifyPlayer(ev.getPlayer(), "Chunk registered");
            }
        }
    }
}
