package com.jp.ichi.spigot.commandhelper.argument;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.v1_13_R2.CommandListenerWrapper;

public class ArgumentExecute extends SimpleCommandArgument<String> {

    public ArgumentExecute(Command<CommandListenerWrapper> command) {
        super(command);
    }

    @Override
    public String getValue(CommandContext<CommandListenerWrapper> commandContext) {
        return "command";
    }
}
