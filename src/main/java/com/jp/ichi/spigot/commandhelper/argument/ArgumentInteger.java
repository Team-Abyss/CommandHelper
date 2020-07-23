package com.jp.ichi.spigot.commandhelper.argument;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.v1_13_R2.CommandListenerWrapper;

public class ArgumentInteger extends SimpleCommandArgument<Integer> {

    public ArgumentInteger(String name, CommandArgument<?>... arguments) {
        super(name, RequiredArgumentBuilder.argument(name, IntegerArgumentType.integer()), arguments);
    }

    public ArgumentInteger(String name, int min, CommandArgument<?>... arguments) {
        super(name, RequiredArgumentBuilder.argument(name, IntegerArgumentType.integer(min)), arguments);
    }

    public ArgumentInteger(String name, int min, int max, CommandArgument<?>... arguments) {
        super(name, RequiredArgumentBuilder.argument(name, IntegerArgumentType.integer(min, max)), arguments);
    }

    @Override
    public Integer getValue(CommandContext<CommandListenerWrapper> commandContext) {
        return IntegerArgumentType.getInteger(commandContext, getName());
    }
}
