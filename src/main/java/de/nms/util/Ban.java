package de.nms.util;

import org.bukkit.entity.Player;

public class Ban {
    private Player player;
    private String  reason;


    public Player player() {
        return player;
    }

    public String reason() {
        return reason;
    }

    public Ban(String reason, Player player){
        this.player  = player;
        this.reason = reason;
    }
}
