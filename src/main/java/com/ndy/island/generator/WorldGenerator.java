package com.ndy.island.generator;

import com.ndy.chunk.WaterChunkGenerator;
import com.ndy.island.generator.abstraction.GenerateAble;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

public class WorldGenerator implements GenerateAble {

    public WorldGenerator() {}

    @Override
    public boolean generate() throws Exception {
        World world = Bukkit.getWorld("island_world");

        if (world == null) {
            WorldCreator creator = new WorldCreator("island_world");
            creator.generator(new WaterChunkGenerator());
            Bukkit.createWorld(creator);
        }
        return true;
    }

    @Override
    public String getName() {
        return "World Generator";
    }
}
