package com.ndy.island.generator;

import com.ndy.gson.GsonUtil;
import com.ndy.island.Island;
import com.ndy.island.generator.abstraction.IGenerate;
import com.ndy.island.option.IslandData;
import com.ndy.island.storage.IslandStorage;
import org.bukkit.entity.Player;

public class IslandDeleter implements IGenerate {

    private Player player;

    public IslandDeleter(Player player) {
        this.player = player;
    }

    @Override
    public boolean generate() throws Exception {
        Island island = IslandStorage.getInstance().getIsland(player);
        String uuidStr = player.getUniqueId().toString();

        IslandData.getInstance().getDataManager().set(uuidStr, null);
        IslandData.getInstance().save();

        GsonUtil<Island> gsonUtil = new GsonUtil<>(IslandStorage.getInstance().getIslandFolder());
        gsonUtil.delete(uuidStr);

        IslandStorage.getInstance().unregisterIsland(island);

        player.performCommand("스폰");
        return true;
    }

    @Override
    public String getName() {
        return player.getName() + " Island Delete";
    }
}
