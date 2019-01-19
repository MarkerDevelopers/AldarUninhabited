package com.ndy.chunk;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WaterChunkGenerator extends ChunkGenerator {

    public List<BlockPopulator> get(World w){
        return new ArrayList<>();
    }

    private void setBlock(int x, int y, int z, byte[][] chunk, Material material){
        if(chunk[y >> 4] == null){
            chunk[y >> 4] = new byte[4096];
        }
        if (y > 256 || y < 0 || x > 16 || x < 0 || z > 16 || z < 0) {
            return;
        }
        try {
            chunk[y >> 4][(y & 0xF) << 8 | z << 4 | x] = (byte)material.getId();
        }
        catch (Exception e) {}
    }

    public byte[][] generateBlockSections(World world, Random rand, int chunkX, int chunkZ, ChunkGenerator.BiomeGrid biomes) {
        final byte[][] chunk = new byte[world.getMaxHeight() / 16][];
        final int height = world.getMaxHeight() / 4;
        for (int x = 0; x < 16; ++x) {
            for (int z = 0; z < 16; ++z) {
                for (int y = 1; y < height; ++y) {
                    this.setBlock(x, y, z, chunk, Material.WATER);
                }
            }
        }
        return chunk;
    }

}
