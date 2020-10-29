package com.jp.ichi.spigot.commandhelper.argument;

import com.mojang.brigadier.builder.ArgumentBuilder;
import net.minecraft.server.v1_13_R2.CommandListenerWrapper;

public interface ArgumentBuilderBuilder {

    ArgumentBuilder<CommandListenerWrapper, ?> create();
}
