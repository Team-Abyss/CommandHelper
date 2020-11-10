package com.jp.ichi.spigot.commandhelper.argument;

import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.v1_13_R2.ArgumentTile;
import net.minecraft.server.v1_13_R2.CommandListenerWrapper;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.v1_13_R2.block.data.CraftBlockData;

public class ArgumentBlockData extends SimpleCommandArgument<BlockData> {

    public ArgumentBlockData(String name, CommandArgument<?>... arguments) {
        super(name,()-> RequiredArgumentBuilder.argument(name, ArgumentTile.a()),arguments);

    }

    @Override
    public BlockData getValue(CommandContext<CommandListenerWrapper> commandContext) {
        return CraftBlockData.fromData(ArgumentTile.a(commandContext, getName()).a());
    }
}
