package com.ndy.command.executor;

import com.ndy.command.impl.AbstractCommandRamify;
import com.ndy.island.Island;
import com.ndy.island.storage.IslandStorage;
import org.bukkit.entity.Player;

public class IslandLeaveCommand extends AbstractCommandRamify {

    public IslandLeaveCommand() {
        super("탈퇴", "나가기", "leave", "quit");
    }

    @Override
    public boolean execute(Object... objects) {
        Player player = (Player) objects[0];
        Island island = IslandStorage.getInstance().getIsland(player);

        if(island == null) {
            setMessage("§c당신은 섬에 속해있지 않습니다");
            return false;
        }
        if(island.isOwner(player)) {
            setMessage("§c섬 방장은 탈퇴할 수 없습니다. /island delete");
            return false;
        }

        island.getParty().quitPartyMember(player);
        IslandStorage.getInstance().save(island);

        setMessage("§c파티에서 나왔습니다.");
        return false;
    }

}
