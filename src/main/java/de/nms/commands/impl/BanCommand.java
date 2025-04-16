package de.nms.commands.impl;

import de.nms.commands.ComaiCommand;
import de.nms.util.BanManager;
import de.nms.util.Helper;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import org.bukkit.entity.Player;

public class BanCommand{
    public static void command() {
        new CommandAPICommand("ban")
                .withAliases("comaiban", "coban")
                .withPermission("comai.commands.ban")
                .withArguments(new PlayerArgument("player"),  new GreedyStringArgument("reason"))
                .executes((sender, args) -> {
                    Player toBan = (Player) args.get("player");
                    String string = (String) args.get("reason");

                    BanManager.addBan(toBan.getUniqueId(), string);
                    Helper.pSend(String.format("You banned %s successfully!", toBan.getName()), sender);
                    toBan.kick(Helper.convert("<newline><red>You got banned!<newline>"));
                }).register("comai");
    }
}
