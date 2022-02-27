package me.daniel.rpg.listener;

import me.daniel.rpg.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Listener implements org.bukkit.event.Listener {
    @EventHandler
    public void onPlayerBuild(PlayerInteractEvent ev) {
        if (ev.getAction().equals(Action.LEFT_CLICK_BLOCK) || ev.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            var build = Main.getPlugin().build;
            if (build.get(ev.getPlayer().getUniqueId()) == null ||
                    !build.get(ev.getPlayer().getUniqueId()))
                ev.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerHit(PlayerInteractEntityEvent ev) {
        if (ev.getRightClicked() instanceof Player) {
            ev.setCancelled(true);
        }
    }
}
