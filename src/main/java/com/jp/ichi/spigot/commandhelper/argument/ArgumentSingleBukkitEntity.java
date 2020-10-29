package com.jp.ichi.spigot.commandhelper.argument;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.v1_13_R2.ArgumentEntity;
import net.minecraft.server.v1_13_R2.CommandListenerWrapper;
import org.bukkit.entity.Entity;

import java.util.stream.Collectors;

public class ArgumentSingleBukkitEntity extends SimpleCommandArgument<Entity> {

    public ArgumentSingleBukkitEntity(String name, CommandArgument<?>... arguments) {
        super(name,()-> RequiredArgumentBuilder.argument(name, ArgumentEntity.a()), arguments);
    }

    @Override
    public Entity getValue(CommandContext<CommandListenerWrapper> commandContext) {
        try {
            return ArgumentEntity.a(commandContext, getName()).getBukkitEntity();
        } catch (CommandSyntaxException exception) {
            return null;
        }
    }
}
