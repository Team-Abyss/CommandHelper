package com.jp.ichi.spigot.commandhelper.argument;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.v1_13_R2.CommandListenerWrapper;
import net.minecraft.server.v1_13_R2.MobEffect;
import net.minecraft.server.v1_13_R2.MobEffectList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ArgumentMobEffect implements CommandArgument<MobEffect> {


    private Predicate<CommandListenerWrapper> requirement = null;
    private final List<CommandArgument<?>> children = new ArrayList<>();

    public ArgumentMobEffect(CommandArgument<?>... arguments) {
        for (CommandArgument<?> argument : arguments) {
            then(argument);
        }

    }

    @Override
    public String getName() {
        return "effect";
    }

    @Override
    public ArgumentBuilder<CommandListenerWrapper, ?> getArgumentBuilder() {
        RequiredArgumentBuilder<CommandListenerWrapper,?> mobEffectList = RequiredArgumentBuilder.argument("effect", net.minecraft.server.v1_13_R2.ArgumentMobEffect.a());
        RequiredArgumentBuilder<CommandListenerWrapper,?> second = RequiredArgumentBuilder.argument("second", IntegerArgumentType.integer(1, 1000000));
        RequiredArgumentBuilder<CommandListenerWrapper,?> amplifier = RequiredArgumentBuilder.argument("amplifier", IntegerArgumentType.integer(0, 255));
        RequiredArgumentBuilder<CommandListenerWrapper,?> bool = RequiredArgumentBuilder.argument("hideParticles", BoolArgumentType.bool());

        for (CommandArgument<?> argument: children) {
            ArgumentBuilder<?, ?> builder2 = argument.getArgumentBuilder();
            if (builder2 instanceof LiteralArgumentBuilder<?>) {
                amplifier.then((LiteralArgumentBuilder<CommandListenerWrapper>) builder2);
                bool.then((LiteralArgumentBuilder<CommandListenerWrapper>) builder2);
            } else if (builder2 instanceof RequiredArgumentBuilder<?, ?>) {
                amplifier.then((RequiredArgumentBuilder<CommandListenerWrapper, ?>) builder2);
                bool.then((RequiredArgumentBuilder<CommandListenerWrapper, ?>) builder2);
            } else if (argument.haveCommand()) {
                amplifier.executes(argument.getCommand());
                bool.executes(argument.getCommand());
            }
        }
        mobEffectList.then(second.then(amplifier.then(bool)));
        return mobEffectList;
    }

    @Override
    public boolean haveCommand() {
        return false;
    }

    @Override
    public Command<CommandListenerWrapper> getCommand() {
        return null;
    }

    @Override
    public CommandArgument<?> then(CommandArgument<?> argument) {
        children.add(argument);
        return this;
    }

    @Override
    public CommandArgument<?> requires(Predicate<CommandListenerWrapper> requirement) {
        this.requirement = requirement;
        return this;
    }

    @Override
    public Predicate<CommandListenerWrapper> getRequirement() {
        return requirement;
    }

    @Override
    public MobEffect getValue(CommandContext<CommandListenerWrapper> commandContext) {

        int integer = IntegerArgumentType.getInteger(commandContext, "second");
        int amplifier = IntegerArgumentType.getInteger(commandContext, "amplifier");
        MobEffectList mobeffectlist;
        try {
            mobeffectlist = net.minecraft.server.v1_13_R2.ArgumentMobEffect.a(commandContext, "effect");
        } catch (CommandSyntaxException exception) {
            exception.printStackTrace();
            return null;
        }

        int k;
        if (mobeffectlist.isInstant()) {
            k = integer;
        } else {
            k = integer * 20;
        }

        boolean flag;
        try {
            flag = BoolArgumentType.getBool(commandContext, "hideParticles");
        } catch (IllegalArgumentException exception) {
            flag = false;
        }

        return new MobEffect(mobeffectlist, k, amplifier, false, !flag);
    }
}
