package com.ndy.island.option;

import com.ndy.gson.GsonUtil;

import java.io.File;

public class IslandOptionFactory {

    private static IslandOptionFactory instance;

    private IslandOption option;

    private IslandOptionFactory(){ }

    public static synchronized IslandOptionFactory getInstance() {
        if(instance == null) instance = new IslandOptionFactory();

        return instance;
    }

    public IslandOption getOption() {
        return option;
    }

    public void loadOption(File optionFolder) {
        GsonUtil<IslandOption> gsonUtil = new GsonUtil<>(optionFolder);

        if(gsonUtil.exists("options")) {
            this.option = gsonUtil.parseObject("options", IslandOption.class);
            System.out.println("AldarUninhabited Option Loaded");
        }else {
            this.option = new IslandOption();
            saveOption(optionFolder);
            System.out.println("AldarUninhabited Option Initialize");
        }
    }

    public void saveOption(File optionFolder) {
        GsonUtil<IslandOption> gsonUtil = new GsonUtil<>(optionFolder);

        if(option == null) return;

        gsonUtil.writeGson("options", this.option);
    }

}
