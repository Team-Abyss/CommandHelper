package com.jp.ichi.spigot.commandhelper.argument;

import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.v1_13_R2.ArgumentEntity;
import net.minecraft.server.v1_13_R2.CommandListenerWrapper;
import org.bukkit.entity.Entity;

import java.util.List;
import java.util.stream.Collectors;

public class ArgumentBukkitEntity extends SimpleCommandArgument<List<Entity>> {
    public ArgumentBukkitEntity(String name, CommandArgument<?>... arguments) {
        super(name,()-> RequiredArgumentBuilder.argument(name, ArgumentEntity.b()), arguments);
    }

    @Override
    public List<Entity> getValue(CommandContext<CommandListenerWrapper> commandContext) {
        try {
            return ArgumentEntity.b(commandContext, getName()).stream().map(net.minecraft.server.v1_13_R2.Entity::getBukkitEntity).collect(Collectors.toList());
        } catch (CommandSyntaxException exception) {
            return null;
        }
    }
}
