package de.nms.events;

import de.nms.Main;
import de.nms.util.BanManager;
import de.nms.util.Helper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.awt.*;

public class LoginEvent implements Listener {

    @EventHandler
    public void onLogin(PlayerLoginEvent e){
        if (e.getPlayer().getHAProxyAddress() != null){
            e.setResult(PlayerLoginEvent.Result.KICK_FULL);
            e.kickMessage(Helper.convert("<newline><red>VPN detected! Please disable your VPN/Proxy<newline>"));
        }
        if ( BanManager.isBan(e.getPlayer().getUniqueId()) ){
            e.setResult(PlayerLoginEvent.Result.KICK_FULL);
            e.kickMessage(Helper.convert("<newline><red>You were banned!<newline> <yellow>Reason:<newline><greeen>$reason<newline>".replace("$reason", BanManager.getReason(e.getPlayer().getUniqueId()))));
        }
        Main.getInstance().db().data("playerdata."+e.getPlayer().getUniqueId()+".logins."+Helper.DATE, true);
    }
}
