package de.nms.util;

import com.nametagedit.plugin.NametagEdit;
import de.nms.Main;
import net.luckperms.api.LuckPerms;
import org.bukkit.entity.Player;

import static de.nms.util.Helper.rawConvert;

public class TabManager {

    public static void doTab(Player player){
        String header = Main.getInstance().config().getString("tab.header");
        String footer = Main.getInstance().config().getString("tab.footer");
        LuckPerms luckPerms = Main.getLUCKPERMS();
        String prefix = luckPerms.getPlayerAdapter(Player.class).getMetaData(player).getPrefix();
        if (player.hasPermission("comai.tab.see")){
            player.sendPlayerListHeaderAndFooter(rawConvert(header), rawConvert(footer));
        }else{
            player.sendPlayerListHeaderAndFooter(rawConvert("<gradient:red:dark_red>NO PERMISSION"), rawConvert("<gradient:red:dark_red>NO PERMISSION"));
        }

        player.playerListName(rawConvert(prefix).append(player.name()));
        NametagEdit.getApi().setPrefix(player, prefix);
    }
}
