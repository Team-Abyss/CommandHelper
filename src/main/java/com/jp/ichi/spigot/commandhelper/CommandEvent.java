package com.jp.ichi.spigot.commandhelper;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;

public class CommandEvent implements Listener {

    @EventHandler
    public void onCommand(PlayerCommandSendEvent event) {
        CommandRegister.unregisterNotOverride(event.getCommands());
    }
}
