package com.jp.ichi.spigot.commandhelper.argument;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.v1_13_R2.CommandListenerWrapper;
import net.minecraft.server.v1_13_R2.Entity;

import java.util.Collection;

public class ArgumentEntity extends SimpleCommandArgument<Collection<? extends Entity>> {

    public ArgumentEntity(String name, CommandArgument<?>... arguments) {
        super(name, RequiredArgumentBuilder.argument(name, net.minecraft.server.v1_13_R2.ArgumentEntity.b()), arguments);
    }

    @Override
    public Collection<? extends Entity> getValue(CommandContext<CommandListenerWrapper> commandContext) {
        try {
            return net.minecraft.server.v1_13_R2.ArgumentEntity.b(commandContext, getName());
        } catch (CommandSyntaxException exception) {
            return null;
        }
    }
}
