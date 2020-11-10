package com.jp.ichi.spigot.commandhelper.argument;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.v1_13_R2.ArgumentVec3;
import net.minecraft.server.v1_13_R2.CommandListenerWrapper;
import net.minecraft.server.v1_13_R2.Vec3D;
import org.bukkit.Location;

public class ArgumentLocation extends SimpleCommandArgument<Location> {
    public ArgumentLocation(String name, CommandArgument<?>... arguments) {
        super(name,()-> RequiredArgumentBuilder.argument(name, ArgumentVec3.a()), arguments);
    }

    @Override
    public Location getValue(CommandContext<CommandListenerWrapper> commandContext) {
        try {
            Vec3D vec3D = ArgumentVec3.a(commandContext, getName());
            return new Location(commandContext.getSource().getWorld().getWorld(),vec3D.x,vec3D.y,vec3D.z);
        } catch (CommandSyntaxException exception) {
            return null;
        }
    }
}
