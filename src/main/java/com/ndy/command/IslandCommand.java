package com.ndy.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class IslandCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(label.equals("island") || label.equals("섬") || label.equals("팜") || label.equals("무인도")) {

        }

        return false;
    }
}
