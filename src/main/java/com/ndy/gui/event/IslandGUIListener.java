package com.ndy.gui.event;

import com.ndy.island.Island;
import com.ndy.island.storage.IslandStorage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class IslandGUIListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if(event.getInventory().getTitle().equals("무인도 메뉴")) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            Island island = IslandStorage.getInstance().getIsland(player);

            switch (event.getRawSlot()) {
                case 0:
                    player.teleport(island.getCenter().toLocation());
                    player.closeInventory();
                    break;

            }
        }
    }

}
