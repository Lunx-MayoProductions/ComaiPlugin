package de.nms.database;

import de.nms.Main;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.net.URI;

public class YamlDatabase extends Database{
    static YamlConfiguration db;
    @Override
    public void connect(){
        File file = new File("database.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
         db = new YamlConfiguration();
        try {
            db.load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public static void save(){
        File file = new File("database.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            db.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start() {
        db.set("enabled", "true");
    }

    @Override
    public void data(String key, Object value) {
        db.set(key, value);
    }

    @Override
    public DataBaseType type() {
        return DataBaseType.YAML;
    }
}
