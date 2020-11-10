package com.jp.ichi.spigot.commandhelper.argument;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.v1_13_R2.CommandListenerWrapper;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ArgumentLocationRotation implements CommandArgument<Location> {

    private final List<CommandArgument<?>> children = new ArrayList<>();

    @Override
    public String getName() {
        return "location";
    }

    @Override
    public ArgumentBuilder<CommandListenerWrapper, ?> getArgumentBuilder() {
        return null;
    }

    @Override
    public boolean haveCommand() {
        return false;
    }

    @Override
    public Command<CommandListenerWrapper> getCommand() {
        return null;
    }

    @Override
    public CommandArgument<?> then(CommandArgument<?> argument) {
        children.add(argument);
        return this;
    }

    @Override
    public CommandArgument<?> requires(Predicate<CommandListenerWrapper> requirement) {
        return null;
    }

    @Override
    public Predicate<CommandListenerWrapper> getRequirement() {
        return null;
    }

    @Override
    public Location getValue(CommandContext<CommandListenerWrapper> commandContext) {
        return null;
    }
}
