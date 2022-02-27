package me.daniel.rpg.file;

import me.daniel.rpg.log.Logger;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class ConfigManager {

    private final String fileName;

    public ConfigManager(String fileName) {
        this.fileName = fileName;
    }

    public HashMap<String, Object> parseConfig() {
        return new Yaml().load(readConfig());
    }

    private void handleFileNotExist(File f) {
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            Logger.error("could not create config File '"+fileName+"'");
        }
    }

    private String readConfig() {
        File f = new File(fileName);

        if (!f.exists()) {
            Logger.warn("Config '"+fileName+"' does not exist. It will be created");
            handleFileNotExist(f);
        }

        try {
            var fis = new FileInputStream(f);
            var temp = new String(fis.readAllBytes());
            fis.close();
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
            Logger.error("could not read config File '" + fileName + "'");
            return "";
        }
    }

}
