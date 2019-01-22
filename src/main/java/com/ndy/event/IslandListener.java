package com.ndy.event;

import com.ndy.island.storage.IslandStorage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class IslandListener implements Listener {

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        IslandStorage.getInstance().load(event.getPlayer());
    }

}
