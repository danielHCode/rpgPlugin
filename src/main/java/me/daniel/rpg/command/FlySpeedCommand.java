package me.daniel.rpg.command;

import me.daniel.rpg.log.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlySpeedCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("rpg.admin")) return false;
        if (args.length < 1 || !(sender instanceof Player)) return false;
        var p = (Player) sender;
        try {
            p.setFlySpeed(Float.parseFloat(args[0]));
            MessageUtil.notifyPlayer(p, "§aFlight Speed changed to §6"+args[0]);
            return true;
        } catch (Exception e) {
            MessageUtil.notifyPlayer(p, "§4usage /flyspeed <int>");
        }
        return false;
    }
}
