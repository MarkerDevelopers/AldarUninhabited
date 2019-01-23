package com.ndy.event;

import com.ndy.island.Island;
import com.ndy.island.storage.IslandStorage;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class IslandListener implements Listener {

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        IslandStorage.getInstance().load(event.getPlayer());
    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();

        if(location.getWorld().getName().equals("island_world")) {
            Island island = IslandStorage.getInstance().getIsland(player);

            if(island != null && !island.withInIsland(player))
                event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteractEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();

        if(location.getWorld().getName().equals("island_world")) {
            Island island = IslandStorage.getInstance().getIsland(player);

            if(event.getItem().getType() == Material.FISHING_ROD) return;

            if(island != null && !island.withInIsland(player))
                event.setCancelled(true);
        }
    }

}
