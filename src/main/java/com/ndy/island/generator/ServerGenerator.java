package com.ndy.island.generator;

import com.ndy.island.generator.abstraction.GenerateAble;

public class ServerGenerator implements GenerateAble {

    private String configName;

    public ServerGenerator(String configName) {
        this.configName = configName;
    }

    @Override
    public boolean generate() throws Exception {
        return false;
    }

    @Override
    public String getName() {
        return null;
    }
}
