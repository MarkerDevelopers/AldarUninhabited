package com.ndy.schematic;

import com.ndy.AldarUninhabitedPlugin;
import com.ndy.island.storage.IslandStorage;
import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.blocks.BaseBlock;
import com.sk89q.worldedit.schematic.MCEditSchematicFormat;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.io.File;
import java.lang.reflect.Field;

public class Schematic {

    /* Climate Schematic */

    /**
     * @return Island Center Location
     * */
    public static Location loadSchematic(File file, Location center, Player player) {
        try {
            CuboidClipboard clipboard = MCEditSchematicFormat.getFormat(file).load(file);
            return paste(center, clipboard, player);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @return Island Center Location
     * */
    private static Location paste(Location center,CuboidClipboard clipboard, Player player) {
        BaseBlock[][][] blocks = getBlocks(clipboard);

        int sizeX = clipboard.getLength();
        int sizeY = clipboard.getHeight();
        int sizeZ = clipboard.getWidth();

        long start = System.currentTimeMillis();
        int x = 0, y = 0, z = 0;
        Location location = null; //sync
        for (x = 0; x < sizeX; x++) {
            for (y = 0; y < sizeY; y++) {
                for (z = 0; z < sizeZ; z++) {
                    BaseBlock block = blocks[x][y][z];
                    location = new Location(center.getWorld(),  center.getZ() + x, center.getY() + y, center.getZ() + z);
                    int typeId = block.getId();

                    if (typeId != 0 && typeId != Material.STATIONARY_WATER.getId()) {
                        location.getBlock().setType(Material.getMaterial(typeId));
                        location.getBlock().setData((byte) block.getData());
                    }
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("스케메틱 로드 소요시간 : " + (end - start)/1000.0 + " 초");

        location = new Location(location.getWorld(), location.getX() - (sizeX/2.0F), location.getY(), location.getZ() - (sizeZ/2.0F));
        player.teleport(location);

        return location;
    }

    private static BaseBlock[][][] getBlocks(CuboidClipboard clipboard) {
        try {
            Field field = CuboidClipboard.class.getDeclaredField("data");

            field.setAccessible(true);
            return (BaseBlock[][][]) field.get(clipboard);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File getIslandSchematicFile(String fileName) {
        File schematicFolder = new File(AldarUninhabitedPlugin.instance.getDataFolder(), "schematics");

        return new File(schematicFolder, fileName);
    }

}
