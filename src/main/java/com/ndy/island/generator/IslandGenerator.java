package com.ndy.island.generator;

import com.ndy.island.generator.abstraction.GenerateAble;
import org.bukkit.entity.Player;

public class IslandGenerator implements GenerateAble {

    private Player player;

    public IslandGenerator(Player player) {
        this.player = player;
    }

    @Override
    public boolean generate() {
        /*Schematic Load*/

        return true;
    }

    @Override
    public String getName() { return player.getName() + " Island Generator"; }
}
