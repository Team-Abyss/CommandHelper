package com.jp.ichi.spigot.commandhelper.argument;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.v1_13_R2.CommandListenerWrapper;

public class ArgumentLiteral extends SimpleCommandArgument<String> {

    public ArgumentLiteral(String name, CommandArgument<?>... arguments) {
        super(name, LiteralArgumentBuilder.literal(name), arguments);
    }

    @Override
    public String getValue(CommandContext<CommandListenerWrapper> commandContext) {
        return getName();
    }
}
