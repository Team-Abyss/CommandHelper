package com.jp.ichi.spigot.commandhelper.argument;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.v1_13_R2.CommandListenerWrapper;

import java.util.ArrayList;
import java.util.List;

public class ArgumentStart extends ArgumentLiteral {

    private List<String> aliases = new ArrayList<>();

    public ArgumentStart(String command, CommandArgument<?>... arguments) {
        super(command, arguments);
    }

    @Override
    @SuppressWarnings("unchecked")
    public LiteralArgumentBuilder<CommandListenerWrapper> getArgumentBuilder() {
        return (LiteralArgumentBuilder<CommandListenerWrapper>) super.getArgumentBuilder();
    }

    public ArgumentStart setPermission(String permission) {
        requires(it -> it.getWorld() == null || it.getBukkitSender().hasPermission(permission));
        return this;
    }

    public ArgumentStart addAlias(String alias) {
        aliases.add(alias);
        return this;
    }

    public List<String> getAliases() {
        return aliases;
    }

    @Override
    public ArgumentStart then(CommandArgument<?> argument) {
        return (ArgumentStart) super.then(argument);
    }

    @Override
    public String getValue(CommandContext<CommandListenerWrapper> commandContext) {
        return getName();
    }
}
