package com.jp.ichi.spigot.commandhelper.argument;

import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.v1_13_R2.CommandListenerWrapper;
import net.minecraft.server.v1_13_R2.MobEffectList;

public class ArgumentMobEffectList extends SimpleCommandArgument<MobEffectList> {

    public ArgumentMobEffectList(String name, CommandArgument<?>... arguments) {
        super(name,()-> RequiredArgumentBuilder.argument(name, net.minecraft.server.v1_13_R2.ArgumentMobEffect.a()), arguments);
    }

    @Override
    public MobEffectList getValue(CommandContext<CommandListenerWrapper> commandContext) {
        try {
            return net.minecraft.server.v1_13_R2.ArgumentMobEffect.a(commandContext, getName());
        } catch (CommandSyntaxException exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
