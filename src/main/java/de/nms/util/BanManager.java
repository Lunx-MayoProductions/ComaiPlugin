package de.nms.util;

import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.UUID;

public class BanManager {
    static HashMap<UUID, String>  cachedBans = new HashMap<>();

    public static void addBan(UUID uuid, String reason){
        cachedBans.put(uuid, reason);
    }

    public static boolean isBan(UUID  uuid){
        return cachedBans.containsKey(uuid);
    }


    public static Ban getBan(UUID uuid){
        return new Ban(cachedBans.getOrDefault(uuid, "Not there."), Bukkit.getPlayer(uuid));
    }

    public static String getReason(UUID  uuid){
        return cachedBans.getOrDefault(uuid, "Banned by an operator.");
    }

    public static void removeBan(UUID uuid){
        cachedBans.remove(uuid)
;    }
}
