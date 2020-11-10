package com.jp.ichi.spigot.commandhelper.argument;

import com.jp.ichi.spigot.commandhelper.CommandRunner;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.v1_13_R2.*;
import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public interface CommandArgument<T> {

    static ArgumentStart Start(String command, CommandArgument<?>... arguments) {
        return new ArgumentStart(command, arguments);
    }

    static CommandArgument<String> Literal(String name, CommandArgument<?>... arguments) {
        return new ArgumentLiteral(name, arguments);
    }

    static CommandArgument<String> String(String name, CommandArgument<?>... arguments) {
        return new ArgumentString(name, arguments);
    }

    static CommandArgument<Integer> Integer(String name, CommandArgument<?>... arguments) {
        return new ArgumentInteger(name, arguments);
    }

    static CommandArgument<Integer> Integer(String name, int min, CommandArgument<?>... arguments) {
        return new ArgumentInteger(name, min, arguments);
    }

    static CommandArgument<Integer> Integer(String name, int min, int max, CommandArgument<?>... arguments) {
        return new ArgumentInteger(name, min, max, arguments);
    }

    static CommandArgument<Double> Double(String name, CommandArgument<?>... arguments) {
        return new ArgumentDouble(name, arguments);
    }

    static CommandArgument<Double> Double(String name, int min, CommandArgument<?>... arguments) {
        return new ArgumentDouble(name, min, arguments);
    }

    static CommandArgument<Double> Double(String name, int min, int max, CommandArgument<?>... arguments) {
        return new ArgumentDouble(name, min, max, arguments);
    }

    static CommandArgument<Float> Float(String name, CommandArgument<?>... arguments) {
        return new ArgumentFloat(name, arguments);
    }

    static CommandArgument<Float> Float(String name, int min, CommandArgument<?>... arguments) {
        return new ArgumentFloat(name, min, arguments);
    }

    static CommandArgument<Float> Float(String name, int min, int max, CommandArgument<?>... arguments) {
        return new ArgumentFloat(name, min, max, arguments);
    }

    static CommandArgument<Boolean> Boolean(String name, CommandArgument<?>... arguments) {
        return new ArgumentBoolean(name, arguments);
    }

    static CommandArgument<List<? extends Entity>> Entity(String name, CommandArgument<?>... arguments) {
        return new ArgumentEntity(name, arguments);
    }

    static CommandArgument<List<org.bukkit.entity.Entity>> BukkitEntity(String name, CommandArgument<?>... arguments) {
        return new ArgumentBukkitEntity(name, arguments);
    }

    static CommandArgument<org.bukkit.entity.Entity> SingleBukkitEntity(String name, CommandArgument<?>... arguments) {
        return new ArgumentSingleBukkitEntity(name, arguments);
    }

    static CommandArgument<List<EntityPlayer>> Player(String name, CommandArgument<?>... arguments) {
        return new ArgumentPlayer(name, arguments);
    }

    static CommandArgument<List<Player>> BukkitPlayer(String name, CommandArgument<?>... arguments) {
        return new ArgumentBukkitPlayer(name, arguments);
    }

    static CommandArgument<Location> Location(String name, CommandArgument<?>... arguments) {
        return new ArgumentLocation(name, arguments);
    }

    static CommandArgument<ArgumentRotation.Rotation> Rotation(String name, CommandArgument<?>... arguments) {
        return new ArgumentRotation(name, arguments);
    }

    static CommandArgument<Location> BlockLocation(String name, CommandArgument<?>... arguments) {
        return new ArgumentBlockLocation(name, arguments);
    }

    static CommandArgument<BlockData> BlockData(String name, CommandArgument<?>... arguments) {
        return new ArgumentBlockData(name, arguments);
    }

    static CommandArgument<ParticleParam> Particle(String name, CommandArgument<?>... arguments) {
        return new ArgumentParticle(name, arguments);
    }

    static CommandArgument<MobEffectList> MobEffectList(String name, CommandArgument<?>... arguments) {
        return new ArgumentMobEffectList(name, arguments);
    }

    static CommandArgument<MobEffect> MobEffect(CommandArgument<?>... arguments) {
        return new ArgumentMobEffect(arguments);
    }

    static CommandArgument<String> Command(CommandRunner command) {
        return new ArgumentExecute(it ->
        {
            try {
                return command.run(it.getSource().getBukkitSender(), it);
            } catch (Exception exception) {
                exception.printStackTrace();
                return 1;
            }
        }
        );
    }

    static CommandArgument<String> Command(Command<CommandListenerWrapper> command) {
        return new ArgumentExecute(command);
    }

    String getName();

    ArgumentBuilder<CommandListenerWrapper, ?> getArgumentBuilder();

    boolean haveCommand();

    Command<CommandListenerWrapper> getCommand();

    CommandArgument<?> then(CommandArgument<?> argument);

    CommandArgument<?> requires(Predicate<CommandListenerWrapper> requirement);

    Predicate<CommandListenerWrapper> getRequirement();

    T getValue(CommandContext<CommandListenerWrapper> commandContext);
}
