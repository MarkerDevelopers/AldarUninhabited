package com.ndy.command;

import com.ndy.command.impl.AbstractCommandRamify;
import com.ndy.command.impl.IslandCommandExecutor;
import com.ndy.island.Island;
import com.ndy.island.storage.IslandStorage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class IslandCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(label.equals("island") || label.equals("섬") || label.equals("팜") || label.equals("무인도")) {
            Player player = (Player) sender;
            if(args.length == 0) {
                if(!IslandStorage.getInstance().isCreated((Player) sender)){
                    sender.sendMessage("§c생성된 무인도가 없습니다. /island create");
                    return false;
                }

                Island island = IslandStorage.getInstance().getIsland(player);
                player.teleport(island.getCenter().toLocation());
                //GUI OPENED
                return false;
            }

            AbstractCommandRamify commandRamify = IslandCommandExecutor.getInstance().getCommandExecutor(args[0]);
            if(commandRamify == null) {
                player.sendMessage("§c커맨드를 제대로 입력해주세요.");
                return false;
            }

            commandRamify.execute(player, args);
            player.sendMessage(commandRamify.getMessage());
        }

        return false;
    }

}
