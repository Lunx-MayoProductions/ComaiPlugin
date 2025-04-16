package de.nms.commands.impl;

import de.nms.commands.ComaiCommand;
import de.nms.util.Helper;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.ArgumentSuggestions;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import dev.jorel.commandapi.arguments.StringArgument;

public class InfoCommand {

    public static void command() {
        CommandAPICommand command = new CommandAPICommand("info")
                .withAliases("broadcast", "allsay")
                .withPermission("comai.commands.broadcast")
                .withArguments(new GreedyStringArgument("message").includeSuggestions(ArgumentSuggestions.strings("[MESSAGE]")))
                .executes((sender, args) -> {
                    Helper.pSend("<yellow>Wir senden einen broadcast...", sender);
                   Helper.sendEveryone("<newline>" +
                           args.get("message") +
                           "<newline><gray><i>$date$");
                });
        command.register("comai");
    }
}
