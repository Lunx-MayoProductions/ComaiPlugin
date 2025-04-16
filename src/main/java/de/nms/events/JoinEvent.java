package de.nms.events;

import de.nms.util.Helper;
import de.nms.util.TabManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent  e)
    {
        TabManager.doTab(e.getPlayer());
        e.joinMessage(Helper.convert(""));
    }}
