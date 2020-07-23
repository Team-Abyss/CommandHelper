package com.jp.ichi.spigot.commandhelper.argument;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.v1_13_R2.ArgumentPosition;
import net.minecraft.server.v1_13_R2.BlockPosition;
import net.minecraft.server.v1_13_R2.CommandListenerWrapper;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class ArgumentBlockLocation extends SimpleCommandArgument<Location> {
    public ArgumentBlockLocation(String name, CommandArgument<?>... arguments) {
        super(name, RequiredArgumentBuilder.argument(name,ArgumentPosition.a()), arguments);
    }

    @Override
    public Location getValue(CommandContext<CommandListenerWrapper> commandContext) {
        try {
            BlockPosition position = ArgumentPosition.a(commandContext, getName());
            return new Location(commandContext.getSource().getWorld().getWorld(), position.getX(), position.getY(), position.getZ());
        } catch (CommandSyntaxException exception) {
            return null;
        }
    }
}
