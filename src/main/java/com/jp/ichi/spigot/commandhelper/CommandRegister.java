package com.jp.ichi.spigot.commandhelper;

import net.minecraft.server.v1_13_R2.CommandDispatcher;
import net.minecraft.server.v1_13_R2.CommandListenerWrapper;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_13_R2.CraftServer;

import java.lang.reflect.Field;

public class CommandRegister {

    private static com.mojang.brigadier.CommandDispatcher<CommandListenerWrapper> result = null;

    @SuppressWarnings("unchecked")
    private static com.mojang.brigadier.CommandDispatcher<CommandListenerWrapper> getDispatcher() {
        if (result == null) {
            try {
                CommandDispatcher dispatcher = ((CraftServer) Bukkit.getServer()).getServer().commandDispatcher;
                Field field = CommandDispatcher.class.getDeclaredField("b");
                field.setAccessible(true);
                result = (com.mojang.brigadier.CommandDispatcher<CommandListenerWrapper>) field.get(dispatcher);
            } catch (NoSuchFieldException | IllegalAccessException ignore) {}
        }
        return result;
    }


    public static void register(CommandNode node) {
        getDispatcher().register(node.getArgument().getArgumentBuilder());
    }

}
