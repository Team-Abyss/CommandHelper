package com.jp.ichi.spigot.commandhelper.argument;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.v1_13_R2.CommandListenerWrapper;

public class ArgumentBoolean extends SimpleCommandArgument<Boolean> {

    public ArgumentBoolean(String name, CommandArgument<?>... arguments) {
        super(name,()-> RequiredArgumentBuilder.argument(name, BoolArgumentType.bool()), arguments);

    }

    @Override
    public Boolean getValue(CommandContext<CommandListenerWrapper> commandContext) {
        return BoolArgumentType.getBool(commandContext, getName());
    }
}
