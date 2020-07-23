package com.jp.ichi.spigot.commandhelper.argument;

import com.jp.ichi.spigot.commandhelper.CommandRunner;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.v1_13_R2.CommandListenerWrapper;
import net.minecraft.server.v1_13_R2.Entity;
import net.minecraft.server.v1_13_R2.EntityPlayer;
import org.bukkit.Location;

import java.util.Collection;

public interface CommandArgument<T> {

    static ArgumentStart Start(String command, CommandArgument<?>... arguments) {
        return new ArgumentStart(command, arguments);
    }

    static CommandArgument<String> Literal(String name, CommandArgument<?>... arguments) {
        return new ArgumentLiteral(name, arguments);
    }

    static CommandArgument<String> String(String name, CommandArgument<?>... arguments) {
        return new ArgumentString(name, arguments);
    }

    static CommandArgument<Integer> Integer(String name, CommandArgument<?>... arguments) {
        return new ArgumentInteger(name, arguments);
    }

    static CommandArgument<Integer> Integer(String name, int min, CommandArgument<?>... arguments) {
        return new ArgumentInteger(name, min, arguments);
    }

    static CommandArgument<Integer> Integer(String name, int min, int max, CommandArgument<?>... arguments) {
        return new ArgumentInteger(name, min, max, arguments);
    }

    static CommandArgument<Double> Double(String name, CommandArgument<?>... arguments) {
        return new ArgumentDouble(name, arguments);
    }

    static CommandArgument<Double> Double(String name, int min, CommandArgument<?>... arguments) {
        return new ArgumentDouble(name, min, arguments);
    }

    static CommandArgument<Double> Double(String name, int min, int max, CommandArgument<?>... arguments) {
        return new ArgumentDouble(name, min, max, arguments);
    }

    static CommandArgument<Float> Float(String name, CommandArgument<?>... arguments) {
        return new ArgumentFloat(name, arguments);
    }

    static CommandArgument<Float> Float(String name, int min, CommandArgument<?>... arguments) {
        return new ArgumentFloat(name, min, arguments);
    }

    static CommandArgument<Float> Float(String name, int min, int max, CommandArgument<?>... arguments) {
        return new ArgumentFloat(name, min, max, arguments);
    }

    static CommandArgument<Collection<? extends Entity>> Entity(String name, CommandArgument<?>... arguments) {
        return new ArgumentEntity(name, arguments);
    }

    static CommandArgument<Collection<org.bukkit.entity.Entity>> BukkitEntity(String name, CommandArgument<?>... arguments) {
        return new ArgumentBukkitEntity(name, arguments);
    }

    static CommandArgument<Collection<EntityPlayer>> Player(String name, CommandArgument<?>... arguments) {
        return new ArgumentPlayer(name, arguments);
    }

    static CommandArgument<Location> Location(String name, CommandArgument<?>... arguments) {
        return new ArgumentLocation(name, arguments);
    }

    static CommandArgument<Location> BlockLocation(String name, CommandArgument<?>... arguments) {
        return new ArgumentBlockLocation(name, arguments);
    }

    static CommandArgument<String> Command(CommandRunner command) {
        return new ArgumentExecute(it -> command.run(it.getSource().getBukkitSender(),it));
    }

    static CommandArgument<String> Command(Command<CommandListenerWrapper> command) {
        return new ArgumentExecute(command);
    }

    String getName();

    ArgumentBuilder<CommandListenerWrapper, ?> getArgumentBuilder();

    boolean haveCommand();

    Command<CommandListenerWrapper> getCommand();

    CommandArgument<?> then(CommandArgument<?> argument);

    T getValue(CommandContext<CommandListenerWrapper> commandContext);
}
