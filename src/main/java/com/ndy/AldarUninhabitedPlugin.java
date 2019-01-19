package com.ndy;

import com.ndy.island.generator.ServerGenerator;
import com.ndy.island.generator.WorldGenerator;
import com.ndy.island.generator.executer.Generator;
import com.ndy.module.PluginModuleManager;
import com.ndy.module.impl.IModuleInitializer;
import com.ndy.module.type.ModuleLoadResult;
import org.bukkit.plugin.java.JavaPlugin;

public class AldarUninhabitedPlugin extends JavaPlugin implements IModuleInitializer {

    @Override
    public void onEnable() {
        PluginModuleManager.getManager().registerModule(this, this);
    }

    @Override
    public ModuleLoadResult initialize() throws Exception {
        serverInitialize();

        return ModuleLoadResult.Success;
    }

    @Override
    public void dispose() {

    }

    private void serverInitialize() {
        Generator generator = new Generator()
                .addGenerateAble(new WorldGenerator())
                .addGenerateAble(new ServerGenerator("config.yml"));
        generator.dispose();
    }
}
