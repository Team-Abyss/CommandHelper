package com.jp.ichi.spigot.commandhelper.argument;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.v1_13_R2.ArgumentEntity;
import net.minecraft.server.v1_13_R2.CommandListenerWrapper;
import net.minecraft.server.v1_13_R2.EntityPlayer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ArgumentPlayer extends SimpleCommandArgument<List<EntityPlayer>> {

    public ArgumentPlayer(String name, CommandArgument<?>... arguments) {
        super(name,()-> RequiredArgumentBuilder.argument(name, (ArgumentType<?>) ArgumentEntity.d()), arguments);
    }

    @Override
    public List<EntityPlayer> getValue(CommandContext<CommandListenerWrapper> commandContext) {
        try {
            return new ArrayList<>(ArgumentEntity.f(commandContext, getName()));
        } catch (CommandSyntaxException exception) {
            return null;
        }
    }
}
