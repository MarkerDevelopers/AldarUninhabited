package com.ndy.gui;

import com.ndy.island.Island;
import com.ndy.island.storage.IslandStorage;
import com.ndy.util.GuiManager;
import com.ndy.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class IslandMenu {

    public static void open(Player player, Island island) {
        GuiManager manager = new GuiManager(9, "무인도 메뉴");
        manager.setItem(0, new ItemBuilder(Material.GRASS).setDisplayName("§e무인도 이동").build());
        manager.setItem(1, new ItemBuilder(Material.SKULL_ITEM).setDisplayName("§a파티원").build());
        manager.setItem(2, new ItemBuilder(Material.RAW_FISH).setDisplayName("§e기후§f: " + island.getClimateName()).build());

        player.openInventory(manager.getInventory());
    }

}
