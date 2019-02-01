package com.ndy.island.generator;

import com.ndy.api.ClimateAPI;
import com.ndy.climate.abstraction.AbstractClimate;
import com.ndy.island.Island;
import com.ndy.island.generator.abstraction.IGenerate;
import com.ndy.island.option.IslandData;
import com.ndy.island.option.IslandOption;
import com.ndy.island.option.IslandOptionFactory;
import com.ndy.island.storage.IslandStorage;
import com.ndy.schematic.Schematic;
import com.ndy.util.LocationWrapper;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.List;

public class IslandGenerator implements IGenerate {

    private Player player;

    public IslandGenerator(Player player) {
        this.player = player;
    }

    @Override
    public boolean generate() {
        IslandOption option = IslandOptionFactory.getInstance().getOption();
        String uuidStr = player.getUniqueId().toString();

        List<String> climateNames = AbstractClimate.ClimateName.getUseAbleClimateNames();
        AbstractClimate climate = ClimateAPI.getClimate(climateNames.get((int) (Math.random() * climateNames.size())));
        List<String> schematics = climate.getContext().getAttribute("schematics");

        Location location = setupLocation(option, uuidStr);

        IslandData.getInstance().getDataManager().set(uuidStr, uuidStr);
        IslandData.getInstance().save();

        Island island = new Island(player, location, climate);

        File schematic = Schematic.getIslandSchematicFile(schematics.get((int) (Math.random() * schematics.size())));
        Location center = Schematic.loadSchematic(schematic, location, player);

        island.setCenter(center);
        IslandStorage.getInstance().registerIsland(island);

        return true;
    }

    private Location setupLocation(IslandOption option, String uuidStr) {
        Location location = option.getLastLocation().toLocation().clone();
        int next = option.getRange() + option.getDistance();
        location.setY(option.getHeight());
        location.add(next, 0, next);

        option.setLastLocation(new LocationWrapper(location));
        option.setLastIslandUUID(uuidStr);

        return location;
    }

    @Override
    public String getName() { return player.getName() + " Island Generator"; }
}
