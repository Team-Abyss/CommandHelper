package com.jp.ichi.spigot.commandhelper.argument;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.v1_13_R2.CommandListenerWrapper;
import net.minecraft.server.v1_13_R2.ParticleParam;

public class ArgumentParticle extends SimpleCommandArgument<ParticleParam> {
    public ArgumentParticle(String name, CommandArgument<?>... arguments) {
        super(name,()-> RequiredArgumentBuilder.argument(name , net.minecraft.server.v1_13_R2.ArgumentParticle.a()), arguments);
    }

    @Override
    public ParticleParam getValue(CommandContext<CommandListenerWrapper> commandContext) {
        return net.minecraft.server.v1_13_R2.ArgumentParticle.a(commandContext, getName());
    }

    //usage: CommandListenerWrapper.getWorld.a(entityPlayer, particleparam, force, loc.x, loc.y, loc.z, count, x,y,z,speed)
}
