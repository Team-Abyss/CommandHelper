package com.jp.ichi.spigot.commandhelper;

import com.jp.ichi.spigot.commandhelper.argument.CommandArgument;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.server.v1_13_R2.CommandDispatcher;
import net.minecraft.server.v1_13_R2.CommandListenerWrapper;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.craftbukkit.v1_13_R2.CraftServer;
import org.bukkit.craftbukkit.v1_13_R2.command.VanillaCommandWrapper;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class CommandRegister {

//    private static com.mojang.brigadier.CommandDispatcher<CommandListenerWrapper> dispatcher = ((CraftServer) Bukkit.getServer()).getServer().commandDispatcher.a();
/*
    @SuppressWarnings("unchecked")
    private static com.mojang.brigadier.CommandDispatcher<CommandListenerWrapper> getDispatcher() {
        if (dispatcher == null) {
            try {
                CommandDispatcher dispatcher = ((CraftServer) Bukkit.getServer()).getServer().commandDispatcher;
                Field field = CommandDispatcher.class.getDeclaredField("b");
                field.setAccessible(true);
                CommandRegister.dispatcher = (com.mojang.brigadier.CommandDispatcher<CommandListenerWrapper>) field.get(dispatcher);
            } catch (NoSuchFieldException | IllegalAccessException ignore) {}
        }
        return dispatcher;
    }


 */

    private static final CommandDispatcher dispatcher = new CommandDispatcher();

    public static final List<LiteralCommandNode<CommandListenerWrapper>> registeredNode = new ArrayList<>();
    public static final List<LiteralCommandNode<CommandListenerWrapper>> notOverrideNode = new ArrayList<>();

    private static boolean unregistered = false;

    public static void unregisterAll() {
        //executeで実行可能な問題が残っている
        /*
        com.mojang.brigadier.CommandDispatcher<CommandListenerWrapper> dispatcher = ((CraftServer) Bukkit.getServer()).getServer().vanillaCommandDispatcher.a();
        com.mojang.brigadier.CommandDispatcher<CommandListenerWrapper> dispatcher2 = ((CraftServer) Bukkit.getServer()).getServer().commandDispatcher.a();
        registeredNode.forEach(it -> {
            if (dispatcher.getRoot().getChildren().remove(it)) {
                System.out.println("true" + it.getName());
            } else {
                System.out.println("false" + it.getName());
            }
        });
        if (dispatcher.getRoot().getChild("test") != null) {
            System.out.println("true test");
        } else {
            System.out.println("false test");
        }
        registeredNode.forEach(dispatcher2.getRoot().getChildren()::remove);
        registeredNode.forEach(dispatcher.getRoot().getChildren()::remove);

         */
        try {
            Field f = SimpleCommandMap.class.getDeclaredField("knownCommands");
            f.setAccessible(true);
            Map<String, Command> commandMap = (Map<String, Command>) f.get(((CraftServer) Bukkit.getServer()).getCommandMap());
            registeredNode.forEach (it -> commandMap.remove("minecraft:" + it.getName()));
            registeredNode.forEach(it -> commandMap.remove(it.getName()));
        } catch (NoSuchFieldException | IllegalAccessException exception) {
            exception.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void unregisterNotOverride(Collection<String> commands) {
        notOverrideNode.forEach(it -> commands.remove("minecraft:" + it.getName()));
        if (! unregistered) {

            try {
                Field f = SimpleCommandMap.class.getDeclaredField("knownCommands");
                f.setAccessible(true);
                Map<String, Command> commandMap = (Map<String, Command>) f.get(((CraftServer) Bukkit.getServer()).getCommandMap());
                notOverrideNode.forEach (it -> {
                    commandMap.remove("minecraft:" + it.getName());
           //         ((CraftServer) Bukkit.getServer()).getServer().vanillaCommandDispatcher.a().getRoot().removeCommand("minecraft:"+it.getName());
            //        ((CraftServer) Bukkit.getServer()).getServer().commandDispatcher.a().getRoot().getChildren().forEach(it2 -> System.out.println(it2.getName()));
                });
            } catch (NoSuchFieldException | IllegalAccessException exception) {
                exception.printStackTrace();
            }
            unregistered = true;

        }
    }

    public static void register(Plugin plugin, CommandNode node, boolean override) {
        LiteralArgumentBuilder<CommandListenerWrapper> originalBuilder = node.getArgument().getArgumentBuilder();
        dispatcher.a().register(originalBuilder);
        LiteralCommandNode<CommandListenerWrapper> literalNode = originalBuilder.build();
        registeredNode.add(literalNode);
        ((CraftServer) Bukkit.getServer()).getCommandMap().register(plugin.getName().toLowerCase(), new VanillaCommandWrapper(dispatcher,literalNode).setAliases(node.getArgument().getAliases()));
        com.mojang.brigadier.CommandDispatcher<CommandListenerWrapper> dis = ((CraftServer) Bukkit.getServer()).getServer().vanillaCommandDispatcher.a();
        if (! override) {
            dis.register(originalBuilder);
            notOverrideNode.add(literalNode);
        }
    }

    @SuppressWarnings({"unchecked"})
    public static void register(Plugin plugin, CommandNode node) {
        register(plugin, node, true);
        /*
        ArrayList<LiteralArgumentBuilder<CommandListenerWrapper>> builders = new ArrayList<>();

        LiteralArgumentBuilder<CommandListenerWrapper> originalBuilder = node.getArgument().getArgumentBuilder();
        //registeredBuilder.add(originalBuilder);
        LiteralCommandNode<CommandListenerWrapper> literalNode = originalBuilder.build();
      //  ((CraftServer) Bukkit.getServer()).getServer().commandDispatcher.a().register(originalBuilder);
        registeredNode.add(literalNode);
        dispatcher.a().register(originalBuilder);
        ((CraftServer) Bukkit.getServer()).getCommandMap().register(plugin.getName().toLowerCase(), new VanillaCommandWrapper(dispatcher,literalNode));

        node.getArgument().getAliases().forEach(it -> {
            LiteralArgumentBuilder<CommandListenerWrapper> builder =(LiteralArgumentBuilder<CommandListenerWrapper>) ( LiteralArgumentBuilder.literal(it).requires((Predicate) literalNode.getRequirement())).redirect(literalNode);
            builders.add(builder);
        });

        builders.forEach(it -> {
            dispatcher.a().register(it);
            ((CraftServer) Bukkit.getServer()).getCommandMap().register(plugin.getName().toLowerCase(), new VanillaCommandWrapper(dispatcher,it.build()));
        });


        new BukkitRunnable(){
            @Override
            public void run() {
                /*
                dis.getRoot().addChild(literalNode);
                System.out.println("register");

                 */
                //TODO
        /*
                Command command = ((CraftServer) Bukkit.getServer()).getCommandMap().getCommand("execute");
                if (command != null) {
                    System.out.println("not null");
                    if (command instanceof VanillaCommandWrapper) {
                        System.out.println("vanilla");
                        VanillaCommandWrapper vc = (VanillaCommandWrapper) command;
                        try {
                            Field f = VanillaCommandWrapper.class.getDeclaredField("dispatcher");
                            f.setAccessible(true);
                            ((CommandDispatcher) f.get(vc)).a().register(originalBuilder);
                        } catch (NoSuchFieldException |IllegalAccessException exception) {
                            exception.printStackTrace();
                        }
                    }
                } else {
                    System.out.println("null");
                }
                //
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("CommandHelper"),0);


         */

    }

}
