package de.nms.database;

import org.bukkit.configuration.InvalidConfigurationException;

import java.io.IOException;

public abstract class Database {
    public abstract void connect();
    public abstract void start();
    public abstract void data(String key, Object value);
    public abstract DataBaseType type();


    public enum DataBaseType {
        MYSQL,
        SQLITE,
        REDIS,
        YAML,
        TOML
    }
}
