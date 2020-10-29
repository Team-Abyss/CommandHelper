package com.jp.ichi.spigot.commandhelper.argument;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.v1_13_R2.ArgumentEntity;
import net.minecraft.server.v1_13_R2.CommandListenerWrapper;
import net.minecraft.server.v1_13_R2.EntityPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class ArgumentBukkitPlayer extends SimpleCommandArgument<List<Player>> {

    public ArgumentBukkitPlayer(String name, CommandArgument<?>... arguments) {
        super(name,()-> RequiredArgumentBuilder.argument(name, (ArgumentType<?>) ArgumentEntity.d()), arguments);
    }

    @Override
    public List<Player> getValue(CommandContext<CommandListenerWrapper> commandContext) {
        try {
            return ArgumentEntity.f(commandContext, getName()).stream().map(EntityPlayer::getBukkitEntity).collect(Collectors.toList());
        } catch (CommandSyntaxException exception) {
            return null;
        }
    }
}
