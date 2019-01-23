package com.ndy.island.generator.server;

import com.ndy.AldarUninhabitedPlugin;
import com.ndy.command.IslandCommand;
import com.ndy.event.IslandListener;
import com.ndy.island.generator.abstraction.IGenerate;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class ServerFunctionGenerator implements IGenerate {

    private JavaPlugin plugin = AldarUninhabitedPlugin.instance;

    @Override
    public boolean generate() throws Exception {
        registerCommands();
        registerEvents();

        return true;
    }

    @Override
    public String getName() {
        return "Function";
    }

    private void registerCommands() { plugin.getCommand("island").setExecutor(new IslandCommand()); }
    private void registerEvents() { Bukkit.getPluginManager().registerEvents(new IslandListener(), plugin); }
}
