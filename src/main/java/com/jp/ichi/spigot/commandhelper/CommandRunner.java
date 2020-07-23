package com.jp.ichi.spigot.commandhelper;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.v1_13_R2.CommandListenerWrapper;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

public abstract class CommandRunner {
    public abstract int run(CommandSender sender, CommandContext<CommandListenerWrapper> command);

    public CommandSender getCommandSender(CommandContext<CommandListenerWrapper> context) {
        return context.getSource().getBukkitSender();
    }

    public World getWorld(CommandContext<CommandListenerWrapper> context) {
        return context.getSource().getWorld().getWorld();
    }

    public double getX(CommandContext<CommandListenerWrapper> context) {
        return context.getSource().getPosition().x;
    }

    public double getY(CommandContext<CommandListenerWrapper> context) {
        return context.getSource().getPosition().y;
    }

    public double getZ(CommandContext<CommandListenerWrapper> context) {
        return context.getSource().getPosition().z;
    }

}
