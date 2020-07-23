package com.jp.ichi.spigot.commandhelper.argument;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.v1_13_R2.CommandListenerWrapper;

public class ArgumentStart extends ArgumentLiteral {

    public ArgumentStart(String command, CommandArgument<?>... arguments) {
        super(command, arguments);
    }

    @Override
    @SuppressWarnings("unchecked")
    public LiteralArgumentBuilder<CommandListenerWrapper> getArgumentBuilder() {
        return (LiteralArgumentBuilder<CommandListenerWrapper>) super.getArgumentBuilder();
    }

    @Override
    public ArgumentStart then(CommandArgument<?> argument) {
        return (ArgumentStart) super.then(argument);
    }

    @Override
    public String getValue(CommandContext<CommandListenerWrapper> commandContext) {
        return getName();
    }
}
