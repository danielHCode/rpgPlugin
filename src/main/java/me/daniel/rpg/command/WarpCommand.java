package me.daniel.rpg.command;

import me.daniel.rpg.core.Registry;
import me.daniel.rpg.log.MessageUtil;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 2 && sender.hasPermission("rpg.warp.admin") && sender instanceof Player) {
            var p = (Player) sender;
            if (args[0].equals("new")) {
                Registry.registerWarp(args[1], p.getLocation());
                MessageUtil.notifyPlayer(p, "§aWarp '"+args[1]+"' successfully registered");
                return true;
            }
        }
        if (args.length == 1 && sender instanceof Player) {
            var p = (Player) sender;
            if (!Registry.warps.containsKey(args[0])) {
                MessageUtil.notifyPlayer(p, "§4The warp '"+args[0]+"' does not exist!");
            }
            p.teleport(Registry.warps.get(args[0]));
            p.playSound(p.getLocation(), Sound.ITEM_BONE_MEAL_USE, 1, 1);
        }
        return false;
    }
}
