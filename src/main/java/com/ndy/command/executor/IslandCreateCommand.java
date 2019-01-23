package com.ndy.command.executor;

import com.ndy.command.impl.AbstractCommandRamify;
import com.ndy.island.generator.IslandGenerator;
import com.ndy.island.generator.executer.Generator;
import com.ndy.island.storage.IslandStorage;
import org.bukkit.entity.Player;

public class IslandCreateCommand extends AbstractCommandRamify {

    public IslandCreateCommand() {
        super("create", "생성");
    }

    @Override
    public boolean execute(Object... objects) {
        Player player = (Player) objects[0];

        if(IslandStorage.getInstance().isCreated(player)) {
            setMessage("§c이미 섬을 가지고 있습니다.");
            return false;
        }

        Generator generator = new Generator().addGenerateAble(new IslandGenerator(player));
        generator.dispose();
        return true;
    }
}
