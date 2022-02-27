package me.daniel.rpg.command;

import me.daniel.rpg.Main;
import me.daniel.rpg.core.Registry;
import me.daniel.rpg.core.event.RpgEvent;
import me.daniel.rpg.core.event.RpgEventType;
import me.daniel.rpg.core.event.WorkerState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("rpg.spawn.admin")) return false;

        if (args.length == 0) sender.sendMessage(Registry.mobChunks.toString());
        if (args.length == 1) {
            switch (args[0]) {
                case "true", "1", "on" -> {
                    Registry.config.put("spawn", true);
                    Main.getPlugin().manager.handleEvent(new RpgEvent("SpawnEngine", RpgEventType.SPAWN, WorkerState.ENABlED, 1));
                    sender.sendMessage("§aMob spawning enabled");
                }
                case "false", "0", "off" -> {
                    Main.getPlugin().manager.handleEvent(new RpgEvent("SpawnEngine", RpgEventType.SPAWN, WorkerState.DISABLED, 1));
                    sender.sendMessage("§4Mob spawning disabled");
                }
                default -> {
                    return false;
                }
            }
        }
        return true;
    }
}
