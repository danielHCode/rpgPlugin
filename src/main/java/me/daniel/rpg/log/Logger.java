package me.daniel.rpg.log;

import me.daniel.rpg.Main;

import java.util.logging.Level;

public class Logger {
    public static String plPrefix = "[BanManager] ";

    public static void warn(String message) {
        Main.getPlugin().getLogger().warning(plPrefix+message);
    }

    public static void error(String message) {
        Main.getPlugin().getLogger().log(Level.FINEST,plPrefix+message);

    }

    public static void log(String message) {
        Main.getPlugin().getLogger().log(Level.INFO,plPrefix+message);
    }
}
