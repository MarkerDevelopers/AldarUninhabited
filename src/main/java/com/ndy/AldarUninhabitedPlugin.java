package com.ndy;

import com.ndy.island.generator.server.ServerFunctionGenerator;
import com.ndy.island.generator.server.ServerGenerator;
import com.ndy.island.generator.server.WorldGenerator;
import com.ndy.island.generator.executer.Generator;
import com.ndy.island.option.IslandOptionFactory;
import com.ndy.module.PluginModuleManager;
import com.ndy.module.impl.IModuleInitializer;
import com.ndy.module.type.ModuleLoadResult;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class AldarUninhabitedPlugin extends JavaPlugin implements IModuleInitializer {

    public static AldarUninhabitedPlugin instance;

    @Override
    public void onEnable() {
        PluginModuleManager.getManager().registerModule(this, this);
    }

    @Override
    public ModuleLoadResult initialize() throws Exception {
        instance = this;
        serverInitialize();

        return ModuleLoadResult.Success;
    }

    @Override
    public void dispose() {
        IslandOptionFactory.getInstance().saveOption(getDataFolder());
    }

    private void serverInitialize() {
        Generator generator = new Generator()
                .addGenerateAble(new WorldGenerator())
                .addGenerateAble(new ServerGenerator(getDataFolder()))
                .addGenerateAble(new ServerFunctionGenerator());

        generator.dispose();
    }
}
