package com.ndy.command.impl;

import com.ndy.command.executor.IslandCreateCommand;
import com.ndy.command.executor.IslandDeleteCommand;

import java.util.ArrayList;
import java.util.List;

public class IslandCommandExecutor {

    private static IslandCommandExecutor instance;
    private static List<AbstractCommandRamify> commandRamifyList = new ArrayList<>();

    static {
        instance = new IslandCommandExecutor();

        commandRamifyList.add(new IslandCreateCommand());
        commandRamifyList.add(new IslandDeleteCommand());
    }

    private IslandCommandExecutor() {}

    public static IslandCommandExecutor getInstance() { return instance; }

    public AbstractCommandRamify getCommandExecutor(String command) {
        for(AbstractCommandRamify commandRamify : commandRamifyList) {
            if(commandRamify.isMatched(command)) return commandRamify;
        }

        return null;
    }

}
