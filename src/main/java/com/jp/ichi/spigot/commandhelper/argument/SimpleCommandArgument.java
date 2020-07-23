package com.jp.ichi.spigot.commandhelper.argument;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import net.minecraft.server.v1_13_R2.CommandListenerWrapper;

public abstract class SimpleCommandArgument<T> implements CommandArgument<T> {

    private final ArgumentBuilder<CommandListenerWrapper, ?> builder;
    private final Command<CommandListenerWrapper> command;
    private final boolean haveCommand;
    private final String name;

    public SimpleCommandArgument(String name, ArgumentBuilder<CommandListenerWrapper, ?> builder, CommandArgument<?>... arguments) {
        this.builder = builder;
        this.command = null;
        this.haveCommand = false;
        this.name = name;
        for (CommandArgument<?> argument : arguments) {
            then(argument);
        }
    }

    public SimpleCommandArgument(Command<CommandListenerWrapper> command) {
        this.builder = null;
        this.command = command;
        this.haveCommand = true;
        this.name = null;
    }

    @Override
    public String getName() {
        return name;
    }

    @SuppressWarnings("unchecked")
    @Override
    public CommandArgument<?> then(CommandArgument<?> argument) {
        ArgumentBuilder<?, ?> builder2 = argument.getArgumentBuilder();
        if (builder2 instanceof LiteralArgumentBuilder<?>) {
            getArgumentBuilder().then((LiteralArgumentBuilder<CommandListenerWrapper>) builder2);
        } else if (builder2 instanceof RequiredArgumentBuilder<?, ?>) {
            getArgumentBuilder().then((RequiredArgumentBuilder<CommandListenerWrapper, ?>) builder2);
        } else if (argument.haveCommand()) {
            System.out.println("command");
            getArgumentBuilder().executes(argument.getCommand());
        }
        return this;
    }

    @Override
    public ArgumentBuilder<CommandListenerWrapper, ?> getArgumentBuilder() {
        return builder;
    }

    @Override
    public boolean haveCommand() {
        return haveCommand;
    }

    @Override
    public Command<CommandListenerWrapper> getCommand() {
        return command;
    }


}
