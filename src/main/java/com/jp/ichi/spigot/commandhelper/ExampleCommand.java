package com.jp.ichi.spigot.commandhelper;

import com.jp.ichi.spigot.commandhelper.argument.CommandArgument;
import com.jp.ichi.spigot.commandhelper.argument.ArgumentStart;
import org.bukkit.entity.Entity;

import java.util.Collection;

public class ExampleCommand implements CommandNode {
    @Override
    public ArgumentStart getArgument() {

        CommandArgument<?> literal = CommandArgument.Literal("aaa");
        CommandArgument<Collection<Entity>> entity = CommandArgument.BukkitEntity("target");
        CommandArgument<?> distance = CommandArgument.Integer("distance");
        CommandArgument<?> literal2 = CommandArgument.Literal("bbb");

        return CommandArgument.Start("test")
                .then(literal
                        .then(entity
                                .then(distance
                                        .then(CommandArgument.Command(it ->{
                                            System.out.println("aaa");
                                            System.out.println(distance.getValue(it));
                                            for (Entity entity1: entity.getValue(it)) {
                                                System.out.println(entity1.getName());
                                            }
                                            return 1;
                                        })))))
                .then(literal2
                        .then(CommandArgument.Command(it ->{
                            System.out.println("bbb");
                            return 0;
                        })))
                .then(CommandArgument.Command(it -> {
                    System.out.println("test");
                    return 1;
                }));
    }
}
