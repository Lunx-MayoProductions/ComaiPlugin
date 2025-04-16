package de.nms.util;

import de.nms.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class Helper {
    public static String DATE = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(LocalDateTime.now());
    public static String PREFIX = "<gray>[<gradient:dark_purple:light_purple>Comai<gray>] <reset>";
    public static void send(String message, CommandSender sender){
        String fMSG = message
                .replace("$prefix$", PREFIX)
                .replace("$date$", DATE);
        sender.sendRichMessage(fMSG);
        Main.getInstance().db().data("data.messages."+DATE, message);
    }

    public static Component convert(String miniMessage){
        String fMSG = "$prefix$" + miniMessage + " <gray>[$date$]";
        String message = fMSG
                .replace("$prefix$", PREFIX)
                .replace("$date$", DATE);

        return MiniMessage.miniMessage().deserialize(message);
    }

    public static Component rawConvert(String miniMessage){
        return MiniMessage.miniMessage().deserialize(miniMessage);
    }

    public static void sendEveryone(String message){
        String fMSG = message
                .replace("$prefix$", PREFIX)
                .replace("$date$", DATE);
        Bukkit.getOnlinePlayers().forEach(player -> {
            send(fMSG, player);
        });
    }

    public static void pSend(String message, CommandSender sender){
        String fMSG = "$prefix$" + message + " <gray>[$date$]";
        send(fMSG, sender);
    }

    public static void pSendEveryone(String message){
        String fMSG = "$prefix$" + message + " <gray>[$date$]";
        sendEveryone(fMSG);
    }
}
