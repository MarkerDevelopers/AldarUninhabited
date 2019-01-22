package com.ndy.island.option;

import com.ndy.module.PluginModuleManager;
import com.ndy.util.DataManager;

public class IslandData {

    private static IslandData instance;
    private DataManager dataManager = new DataManager("data.yml"
            , PluginModuleManager.getManager().getModule("AldarUninhabited"));

    private IslandData() {}

    public static synchronized IslandData getInstance() {
        if(instance == null) instance = new IslandData();

        return instance;
    }

    public DataManager getDataManager() { return dataManager; }

    public void save() { DataManager.save(dataManager); }
}
