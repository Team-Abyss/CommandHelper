package com.jp.ichi.spigot.commandhelper.argument;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.v1_13_R2.CommandListenerWrapper;

public class ArgumentString extends SimpleCommandArgument<String> {

    public ArgumentString(String name, CommandArgument<?>... arguments) {
        super(name, RequiredArgumentBuilder.argument(name, StringArgumentType.string()), arguments);
    }

    @Override
    public String getValue(CommandContext<CommandListenerWrapper> commandContext) {
        return StringArgumentType.getString(commandContext, getName());
    }
}
