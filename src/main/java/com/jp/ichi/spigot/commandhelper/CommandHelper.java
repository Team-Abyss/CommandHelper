package com.jp.ichi.spigot.commandhelper;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandHelper extends JavaPlugin {
    @Override
    public void onEnable() {
       // CommandRegister.register(this,new ExampleCommand(),false);
        Bukkit.getPluginManager().registerEvents(new CommandEvent(),this);
    }

    @Override
    public void onDisable() {
        CommandRegister.unregisterAll();
    }
}
