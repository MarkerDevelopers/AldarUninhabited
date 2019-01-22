package com.ndy.island.storage;

import com.ndy.AldarUninhabitedPlugin;
import com.ndy.gson.GsonUtil;
import com.ndy.island.Island;
import com.ndy.island.option.IslandData;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class IslandStorage {

    private static IslandStorage instance;

    private Map<String, Island> islandMap = new HashMap<>();
    private Map<String, String> uuidMap = new HashMap<>();

    private IslandStorage() { }

    public static synchronized IslandStorage getInstance() {
        if(instance == null) instance = new IslandStorage();

        return instance;
    }

    public File getIslandFolder() {
        return new File(AldarUninhabitedPlugin.instance.getDataFolder(), "islandData");
    }

    public void registerIsland(Island island) {
        /* Island 생성시에만 실행됨 */
        save(island);
        islandMap.put(island.getOwnerUuid(), island);
        uuidMap.put(island.getOwnerUuid(), island.getOwnerUuid());
    }

    public void unregisterIsland(Island island) {
        islandMap.remove(island.getOwnerUuid());
        uuidMap.remove(island.getOwnerUuid());
    }

    public boolean isCreated(Player player) {
        UUID uuid = player.getUniqueId();
        File islandFolder = getIslandFolder();

        return getIsland(player) != null || new File(islandFolder, uuid.toString() + ".json").exists();
    }

    public void save(Island island) {
        GsonUtil<Island> gson = new GsonUtil<>(getIslandFolder());
        gson.writeGson(island.getOwnerUuid(), island);
    }

    public Island getIsland(Player player) {
        String uuidStr = player.getUniqueId().toString();
        boolean contains = IslandData.getInstance().getDataManager().contains(uuidStr);
        if(!islandMap.containsKey(uuidStr)) {
            load(player);
        }

        if(contains) {
            String uuid = null;
            if(uuidMap.get(uuidStr) == null) {
                uuid = IslandData.getInstance().getDataManager().getString(uuidStr);
                uuidMap.put(uuidStr, uuid);
            }
            Island island = islandMap.get(uuid);

            if(island == null) load(uuid);

            return island;
        }

        return null;
    }

    /**
     * @return isOwner
     * */
    public boolean load(Player player) {
        String uuidStr = player.getUniqueId().toString();
        Object fileName = IslandData.getInstance().getDataManager().get(uuidStr);

        if(fileName != null && islandMap.get(fileName) == null) {
            Island island = islandLoad(uuidStr);

            if(island != null) {
                islandMap.put((String) fileName, island);
                return true;
            }
        }
        return false;
    }

    public void load(String ownerUUID) {
        if(islandMap.get(ownerUUID) == null) {
            Island island = islandLoad(ownerUUID);

            if(island != null) {
                islandMap.put(ownerUUID, island);
            }
        }
    }

    private Island islandLoad(String fileName) {
        GsonUtil<Island> gsonUtil = new GsonUtil<>(getIslandFolder());
        Island island = gsonUtil.parseObject(fileName, Island.class);

        return island;
    }

}
