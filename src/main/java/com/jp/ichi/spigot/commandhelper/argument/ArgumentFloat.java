package com.jp.ichi.spigot.commandhelper.argument;

import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.v1_13_R2.CommandListenerWrapper;

public class ArgumentFloat extends SimpleCommandArgument<Float> {

    public ArgumentFloat(String name, CommandArgument<?>... arguments) {
        super(name, RequiredArgumentBuilder.argument(name, FloatArgumentType.floatArg()), arguments);
    }

    public ArgumentFloat(String name, int min, CommandArgument<?>... arguments) {
        super(name, RequiredArgumentBuilder.argument(name, FloatArgumentType.floatArg(min)), arguments);
    }

    public ArgumentFloat(String name, int min, int max, CommandArgument<?>... arguments) {
        super(name, RequiredArgumentBuilder.argument(name, FloatArgumentType.floatArg(min, max)), arguments);
    }

    @Override
    public Float getValue(CommandContext<CommandListenerWrapper> commandContext) {
        return FloatArgumentType.getFloat(commandContext, getName());
    }
}
