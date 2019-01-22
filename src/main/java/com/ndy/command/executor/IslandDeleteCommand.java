package com.ndy.command.executor;

import com.ndy.command.impl.AbstractCommandRamify;
import com.ndy.island.Island;
import com.ndy.island.generator.IslandDeleteGenerator;
import com.ndy.island.generator.executer.Generator;
import com.ndy.island.storage.IslandStorage;
import org.bukkit.entity.Player;

public class IslandDeleteCommand extends AbstractCommandRamify {

    public IslandDeleteCommand() {
        super("delete", "remove", "삭제");
    }

    @Override
    public boolean execute(Object... objects) {
        Player player = (Player) objects[0];
        Island island = IslandStorage.getInstance().getIsland(player);

        if(!IslandStorage.getInstance().isCreated(player)) {
            setMessage("&c생성된 무인도가 없습니다.");
            return false;
        }
        if(island.getParty().getPartyMemberSize() > 1) {
            setMessage("&c현재 파티원이 있으므로 무인도를 삭제할 수 없습니다.");
            return false;
        }

        Generator generator = new Generator();
        generator.addGenerateAble(new IslandDeleteGenerator(player));
        generator.dispose();

        setMessage("§a섬을 삭제하였습니다.");
        return true;
    }
}
