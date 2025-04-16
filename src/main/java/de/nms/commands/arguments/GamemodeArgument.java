package de.nms.commands.arguments;

import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandExceptionType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import de.nms.commands.impl.GamemodeCommand;
import dev.jorel.commandapi.arguments.Argument;
import dev.jorel.commandapi.arguments.CommandAPIArgumentType;
import dev.jorel.commandapi.executors.CommandArguments;
import org.bukkit.GameMode;

import java.util.Locale;

public class GamemodeArgument extends Argument implements ArgumentType {
    static GamemodeArgument instance;

    public GamemodeArgument(String nodename) {
        super(nodename, StringArgumentType.string());
    }

    @Override
    public Class getPrimitiveType() {
        return this.getClass();
    }

    @Override
    public CommandAPIArgumentType getArgumentType() {
        return CommandAPIArgumentType.CUSTOM;
    }

    @Override
    public Object parseArgument(CommandContext commandContext, String s, CommandArguments commandArguments) throws CommandSyntaxException {
        return null;
    }

    @Override
    public GameMode parse(StringReader stringReader) throws CommandSyntaxException {
        switch (stringReader.getString().toLowerCase()){
            case "creative":
            case "c":
            case "1":
                return GameMode.CREATIVE;
            case "survival":
            case "0":
            case "s":
                return GameMode.SURVIVAL;
            case "spectator":
            case "3":
            case "sp":
                return GameMode.SPECTATOR;
            case "adventure":
            case "2":
            case "a":
                return GameMode.ADVENTURE;
            case null, default:
                throw new CommandSyntaxException(CommandSyntaxException.BUILT_IN_EXCEPTIONS.literalIncorrect(), new LiteralMessage("Expected an Argument of type Gamemode!"));
        }
    }
}
