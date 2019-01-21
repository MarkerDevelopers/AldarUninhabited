package com.ndy.island.generator;

import com.ndy.island.Island;
import com.ndy.island.generator.abstraction.GenerateAble;
import com.ndy.island.option.IslandData;
import com.ndy.island.option.IslandOption;
import com.ndy.island.option.IslandOptionFactory;
import com.ndy.island.storage.IslandStorage;
import com.ndy.schematic.Schematic;
import com.ndy.util.LocationWrapper;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.File;

public class IslandGenerator implements GenerateAble {

    private Player player;

    public IslandGenerator(Player player) {
        this.player = player;
    }

    @Override
    public boolean generate() {
        IslandOption option = IslandOptionFactory.getInstance().getOption();
        String uuidStr = player.getUniqueId().toString();
        int next = option.getRange() + option.getDistance();
        Location location = option.getLastLocation().toLocation().clone();
        location.setY(option.getHeight());
        location.add(next, 0, next);

        option.setLastLocation(new LocationWrapper(location));
        option.setLastIslandUUID(uuidStr);

        IslandData.getInstance().getDataManager().set(uuidStr, uuidStr);

        Island island = new Island(player, location);
        IslandStorage.getInstance().registerIsland(island);

        File schematic = Schematic.getIslandSchematicFile(option.getSchematics());
        Schematic.loadSchematic(schematic, location, player);

        return true;
    }

    @Override
    public String getName() { return player.getName() + " Island Generator"; }
}
