package com.ndy.island.generator.server;

import com.ndy.AldarUninhabitedPlugin;
import com.ndy.command.IslandCommand;
import com.ndy.island.generator.abstraction.GenerateAble;
import com.ndy.island.option.IslandOptionFactory;

import java.io.File;

public class ServerGenerator implements GenerateAble {

    /** @description 서버 데이터 파일 Generator */

    private File dataFolder;

    public ServerGenerator(File dataFolder) {
        this.dataFolder = dataFolder;
    }

    private void initializeFolders() {
        if(!dataFolder.exists()) dataFolder.mkdir();

        File schematicFolder = new File(dataFolder, "schematics");
        File islandDataFolder = new File(dataFolder, "islandData");

        createFolder(schematicFolder);
        createFolder(islandDataFolder);
    }

    private void initializeDataFile() {
        IslandOptionFactory.getInstance().loadOption(dataFolder);

    }

    private void createFolder(File folder) {
        if(!folder.exists()) folder.mkdir();
    }

    @Override
    public boolean generate() throws Exception {
        initializeFolders();
        initializeDataFile();

        AldarUninhabitedPlugin.instance.getCommand("island").setExecutor(new IslandCommand());

        return true;
    }

    @Override
    public String getName() {
        return "Server Generator";
    }
}
