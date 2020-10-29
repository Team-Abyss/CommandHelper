package com.jp.ichi.spigot.commandhelper.argument;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import net.minecraft.server.v1_13_R2.CommandListenerWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class SimpleCommandArgument<T> implements CommandArgument<T> {

    private final ArgumentBuilderBuilder builder;
    private final Command<CommandListenerWrapper> command;
    private final boolean haveCommand;
    private final String name;
    private final List<CommandArgument<?>> children = new ArrayList<>();
    private Predicate<CommandListenerWrapper> requirement = null;

    public SimpleCommandArgument(String name, ArgumentBuilderBuilder builder, CommandArgument<?>... arguments) {
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

    @Override
    public CommandArgument<?> then(CommandArgument<?> argument) {
        children.add(argument);
        return this;
    }

    @Override
    public CommandArgument<?> requires(Predicate<CommandListenerWrapper> requirement) {
        this.requirement = requirement;
        return this;
    }

    @Override
    public Predicate<CommandListenerWrapper> getRequirement() {
        return requirement;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ArgumentBuilder<CommandListenerWrapper, ?> getArgumentBuilder() {
        if (builder == null) {
            return null;
        }

        ArgumentBuilder<CommandListenerWrapper, ?> argumentBuilder = builder.create();
        for (CommandArgument<?> argument: children) {
            ArgumentBuilder<?, ?> builder2 = argument.getArgumentBuilder();
            if (builder2 instanceof LiteralArgumentBuilder<?>) {
                argumentBuilder.then((LiteralArgumentBuilder<CommandListenerWrapper>) builder2);
            } else if (builder2 instanceof RequiredArgumentBuilder<?, ?>) {
                argumentBuilder.then((RequiredArgumentBuilder<CommandListenerWrapper, ?>) builder2);
            } else if (argument.haveCommand()) {
                argumentBuilder.executes(argument.getCommand());
            }
        }
        if (requirement != null) {
            argumentBuilder.requires(requirement);
        }
        return argumentBuilder;
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
