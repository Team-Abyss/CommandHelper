package com.jp.ichi.spigot.commandhelper.argument;

import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.v1_13_R2.CommandListenerWrapper;
import net.minecraft.server.v1_13_R2.Vec2F;

public class ArgumentRotation extends SimpleCommandArgument<ArgumentRotation.Rotation> {

    public ArgumentRotation(String name , CommandArgument<?>... arguments) {
        super(name,()-> RequiredArgumentBuilder.argument(name,net.minecraft.server.v1_13_R2.ArgumentRotation.a()), arguments);
    }

    @Override
    public Rotation getValue(CommandContext<CommandListenerWrapper> commandContext) {
        return new Rotation(net.minecraft.server.v1_13_R2.ArgumentRotation.a(commandContext, getName()).b(commandContext.getSource()));
    }

    public static class Rotation {

        private final float yaw;
        private final float pitch;

        public Rotation(Vec2F vec2F) {
            this.yaw = vec2F.j;
            this.pitch = vec2F.i;
        }

        public float getPitch() {
            return pitch;
        }

        public float getYaw() {
            return yaw;
        }
    }
}
