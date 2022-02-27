package me.daniel.rpg.log;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class MessageUtil {
    public static void notifyPlayer(Player p, String message) {
        p.sendMessage(message);
        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
    }
}
