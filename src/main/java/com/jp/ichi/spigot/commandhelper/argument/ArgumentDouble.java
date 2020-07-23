package com.jp.ichi.spigot.commandhelper.argument;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.v1_13_R2.CommandListenerWrapper;

public class ArgumentDouble extends SimpleCommandArgument<Double> {

    public ArgumentDouble(String name, CommandArgument<?>... arguments) {
        super(name, RequiredArgumentBuilder.argument(name, DoubleArgumentType.doubleArg()), arguments);
    }

    public ArgumentDouble(String name, int min, CommandArgument<?>... arguments) {
        super(name, RequiredArgumentBuilder.argument(name, DoubleArgumentType.doubleArg(min)), arguments);
    }

    public ArgumentDouble(String name, int min, int max, CommandArgument<?>... arguments) {
        super(name, RequiredArgumentBuilder.argument(name, DoubleArgumentType.doubleArg(min, max)), arguments);
    }

    @Override
    public Double getValue(CommandContext<CommandListenerWrapper> commandContext) {
        return DoubleArgumentType.getDouble(commandContext, getName());
    }
}
