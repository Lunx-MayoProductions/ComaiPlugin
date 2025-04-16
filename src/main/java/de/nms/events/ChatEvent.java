package de.nms.events;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ChatEvent implements Listener {
    @EventHandler
    public void onChat(AsyncChatEvent e){
        e.renderer((player, sourceDisplayNane, message, viewer) -> {
            Component formatted;
            String gloabal_prefix = "<gray>[<gradient:white:aqua>CursorCraft<gray>] <reset>";
            String format = " <gray>: <reset>";
            boolean viewerHasPerm = viewer instanceof Player player1 ? player1.hasPermission("chat.see") : false;
            boolean legacy = isLegacy(message);
            Component fMsg = player.hasPermission("chat.color") && !legacy ? MiniMessage.miniMessage().deserialize(PlainTextComponentSerializer.plainText().serialize(message)) : message;
            if (viewerHasPerm && player.hasPermission("chat.color") && !legacy){
                formatted = MiniMessage.miniMessage().deserialize(gloabal_prefix + player.getName() + " <gray>[" + player.getUniqueId() + ", " + player.getAddress().getAddress().getHostAddress() + "]" + format).append(fMsg);
            } else if ( viewerHasPerm && player.hasPermission("chat.color") && legacy ) {
                formatted = MiniMessage.miniMessage().deserialize(
                        gloabal_prefix + player.getName() + " <gray>[" + player.getUniqueId() + ", " + player.getAddress().getAddress().getHostAddress() + "]" + format
                ).append(
                        LegacyComponentSerializer.legacySection().deserialize(
                                PlainTextComponentSerializer.plainText().serialize(message)));
            }
            else{
                formatted = MiniMessage.miniMessage().deserialize(gloabal_prefix + player.getName()+ format).append(fMsg);
            }
            return formatted;
        });
    }

    private boolean isLegacy(Component message) {
        AtomicBoolean bool = new AtomicBoolean();
        List<String> colorCodes = Arrays.asList("&0", "&1", "&2", "&3", "&4", "&5", "&6", "&7", "&8", "&9", "&a", "&b", "&c","&d", "&e", "&f", "&k", "&l", "&m", "&n", "&o", "&r");
        colorCodes.forEach(code -> {
            if (PlainTextComponentSerializer.plainText().serialize(message).contains(code)){
                bool.set(true);
                return;
            }else{
                bool.set(false);
            }
        });
        return bool.get();
    }
}
