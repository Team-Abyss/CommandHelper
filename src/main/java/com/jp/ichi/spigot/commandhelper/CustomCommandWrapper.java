package com.jp.ichi.spigot.commandhelper;

import com.google.common.base.Joiner;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.tree.CommandNode;
import net.minecraft.server.v1_13_R2.CommandDispatcher;
import net.minecraft.server.v1_13_R2.CommandListenerWrapper;
import org.apache.commons.lang.Validate;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.craftbukkit.v1_13_R2.command.VanillaCommandWrapper;
import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomCommandWrapper extends BukkitCommand {

    private final CommandDispatcher dispatcher;
    public final CommandNode<CommandListenerWrapper> vanillaCommand;

    public CustomCommandWrapper(CommandDispatcher dispatcher, CommandNode<CommandListenerWrapper> vanillaCommand) {
        super(vanillaCommand.getName(),"A CommandHelper provided command.", vanillaCommand.getUsageText(), Collections.EMPTY_LIST);
        this.dispatcher = dispatcher;
        this.vanillaCommand = vanillaCommand;
        //TODO
        // this.setPermission(getPermission("なにか"));
    }

    @Override
    public boolean execute(@Nonnull CommandSender sender,@Nonnull String commandLabel,@Nonnull String[] args) {
        if (!testPermission(sender)) return true;

        CommandListenerWrapper icommandListener = VanillaCommandWrapper.getListener(sender);
        dispatcher.a(icommandListener, toDispatcher(args, getName()), toDispatcher(args, commandLabel));
        return true;
    }

    @Override
    public List<String> tabComplete(@Nonnull CommandSender sender,@Nonnull String alias,@Nonnull String[] args, Location location) throws IllegalArgumentException {
        Validate.notNull(sender, "Sender cannot be null");
        Validate.notNull(args, "Arguments cannot be null");
        Validate.notNull(alias, "Alias cannot be null");

        CommandListenerWrapper icommandlistener = VanillaCommandWrapper.getListener(sender);
        ParseResults<CommandListenerWrapper> parsed = dispatcher.a().parse(toDispatcher(args, getName()), icommandlistener);

        List<String> results = new ArrayList<>();
        dispatcher.a().getCompletionSuggestions(parsed).thenAccept((suggestions) -> {
            suggestions.getList().forEach((s) -> results.add(s.getText()));
        });
        return results;
    }

    private String toDispatcher(String[] args, String name) {
        return "/" + name + ((args.length > 0) ? " " + Joiner.on(' ').join(args) : "");
    }
}
