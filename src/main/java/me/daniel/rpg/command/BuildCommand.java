package me.daniel.rpg.command;

import me.daniel.rpg.Main;
import me.daniel.rpg.log.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuildCommand implements CommandExecutor {

    private final Main main;

    public BuildCommand() {
        main = Main.getPlugin();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("rpg.build")) return false;
        if (args.length == 0 && sender instanceof Player) {
            var p = (Player) sender;
            if (main.build.get(p.getUniqueId()) != null &&main.build.get(p.getUniqueId())) {
                main.build.put(p.getUniqueId(), false);
                MessageUtil.notifyPlayer(p, "§4You are no longer allowed to build");
            } else {
                main.build.put(p.getUniqueId(), true);
                MessageUtil.notifyPlayer(p, "§aYou can build from now on");

            }
            return true;
        }
        if (args.length == 1) {
            var t = Bukkit.getPlayer(args[0]);
            if (t == null) {
                sender.sendMessage("§4Player '"+args[0]+"' does not exist");
                return false;
            }
            if (main.build.get(t.getUniqueId()) != null &&main.build.get(t.getUniqueId())) {
                main.build.put(t.getUniqueId(), false);
                MessageUtil.notifyPlayer(t, "§4You are no longer allowed to build");
                sender.sendMessage(t.getName()+" cant build anymore");
            } else {
                main.build.put(t.getUniqueId(), true);
                MessageUtil.notifyPlayer(t, "§aYou can build from now on");
                sender.sendMessage(t.getName()+" can build from now on");
            }
            return true;
        }
        sender.sendMessage("§4Invalid Syntax");
        return false;
    }
}
