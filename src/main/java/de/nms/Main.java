package de.nms;

import de.nms.commands.ComaiCommand;
import de.nms.commands.CommandManager;
import de.nms.commands.impl.BanCommand;
import de.nms.commands.impl.GamemodeCommand;
import de.nms.commands.impl.InfoCommand;
import de.nms.database.Database;
import de.nms.database.RedisDataBase;
import de.nms.database.YamlDatabase;
import de.nms.events.LoginEvent;
import de.nms.util.Licenser;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {
    private static Main main;
    private static boolean started;
    Database database;
    List<ComaiCommand> commands = new ArrayList<>( );

    @Override
    public void onEnable() {
        started = true;
        main = this;
        InfoCommand.command();
        GamemodeCommand.command();
        BanCommand.command();
        saveDefaultConfig();
        saveConfig();
        String licenseStatus = Licenser.check(getConfig().getString("important.license"), getConfig().getString("important.customerid")).msg();

        switch (config().getString("database.type").toLowerCase()) {
            case "yaml":
                database = new YamlDatabase();
                database.connect();
                database.start();
                break;
            case "redis":
                database = new RedisDataBase();
                database.connect();
                database.start();
                break;
            default:
                getLogger().severe("CONFIG MISCONFIGURED");
                Bukkit.getPluginManager().disablePlugin(this);
        }

        database.data("version", this.getPluginMeta().getVersion());
        started = true;

        new BukkitRunnable() {
            @Override
            public void run() {
                YamlDatabase.save();
            }
        }.runTaskTimer(this, 50L, 50L);

        getLogger().info("|-------------------------------------");
        getLogger().info("               COMAI PLUGIN           ");
        getLogger().info("");
        getLogger().info("> VERSION: " + getPluginMeta().getVersion());
        getLogger().info("> LICENSE: " + licenseStatus);
        getLogger().info("> CODED BY: Lunx");
        getLogger().info("");
        getLogger().info("|--------------------------------------");

        if (licenseStatus.equalsIgnoreCase(Licenser.LicenseState.FAIL.msg())){
            getLogger().severe("EXITING! You have an invalid license!");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        Bukkit.getPluginManager().registerEvents(new LoginEvent(), this);
    }
    public FileConfiguration config() {
        return getConfig();
    }


    public boolean started() {
        return started;
    }

    public static Main getInstance() throws IllegalStateException{
        if (!started){
            throw new IllegalStateException("Error: You tried to access class 'de.nms.Main' while it was not started!");
        }
        return main;
    }

    public Database db(){
        return database;
    }

    @Override
    public void onDisable(){
        YamlDatabase.save();
    }
}
