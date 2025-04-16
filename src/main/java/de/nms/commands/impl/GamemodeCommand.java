package de.nms.commands.impl;

import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import de.nms.commands.ComaiCommand;
import de.nms.commands.arguments.GamemodeArgument;
import de.nms.util.Helper;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.ArgumentSuggestions;
import dev.jorel.commandapi.arguments.StringArgument;
import dev.jorel.commandapi.executors.PlayerCommandExecutor;
import org.bukkit.GameMode;

public class GamemodeCommand{
    public static void command() {

       new CommandAPICommand("gamemode")
                .withAliases("gm", "gmode")
                .withArguments(
                        new StringArgument("gamemode").
                                includeSuggestions(ArgumentSuggestions.strings
                                        ("creative", "1", "c", "survival", "s", "0", "spectator", "3", "sp", "adventure", "2", "a")))
                .withPermission("comai.commands.gamemode")
                .executesPlayer((PlayerCommandExecutor) (player, args) -> {
                    String gameMode1 = (String) args.get("gamemode");
                    GameMode gameMode;
                    switch (gameMode1.toLowerCase()){
                        case "creative":
                        case "c":
                        case "1":
                            gameMode =  GameMode.CREATIVE;
                            player.setGameMode(gameMode);
                            Helper.pSend("<green>Dein Gamemode wurde auf " + gameMode.name() + " gesetzt!", player);
                            break;
                        case "survival":
                        case "0":
                        case "s":
                            gameMode =  GameMode.SURVIVAL;
                            player.setGameMode(gameMode);
                            Helper.pSend("<green>Dein Gamemode wurde auf " + gameMode.name() + " gesetzt!", player);
                            break;
                        case "spectator":
                        case "3":
                        case "sp":
                            gameMode = GameMode.SPECTATOR;
                            player.setGameMode(gameMode);
                            Helper.pSend("<green>Dein Gamemode wurde auf " + gameMode.name() + " gesetzt!", player);
                            break;
                        case "adventure":
                        case "2":
                        case "a":
                            gameMode =  GameMode.ADVENTURE;
                            player.setGameMode(gameMode);
                            Helper.pSend("<green>Dein Gamemode wurde auf " + gameMode.name() + " gesetzt!", player);
                            break;
                        default:
                            Helper.pSend("<red>Unbekannter Gamemode", player);
                            break;
                    }
                }).register("comai");
    }
}
