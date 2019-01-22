package com.ndy.command.executor;

import com.ndy.command.impl.AbstractCommandRamify;
import com.ndy.island.Island;
import com.ndy.island.party.invite.IslandPartyInviteScheduler;
import com.ndy.island.party.invite.storage.IslandInviteSchedulerStorage;
import com.ndy.island.storage.IslandStorage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class IslandInviteCommand extends AbstractCommandRamify {

    public IslandInviteCommand() {
        super("초대", "invite");
    }

    @Override
    public boolean execute(Object... objects) {
        Player player = (Player) objects[0];
        Island island = IslandStorage.getInstance().getIsland(player);
        String[] args = (String[]) objects[1];

        if(args.length != 2) {
            setMessage("&c/island invite [playername]");
            return false;
        }
        if(island == null) {
            setMessage("&c참여한 섬이 없거나 생성한 섬이 없습니다.");
            return false;
        }
        if(island.getOwnerUuid().equals(player.getUniqueId().toString())) {
            setMessage("&c당신은 섬 주인이 아닙니다.");
            return false;
        }

        Player receiver = Bukkit.getPlayer(args[1]);
        Island receiverIsland = IslandStorage.getInstance().getIsland(receiver);
        if(receiver == null) {
            setMessage("&c플레이어가 온라인이 아닙니다.");
            return false;
        }
        if(receiverIsland != null) {
            setMessage("&c이미 대상은 섬을 가지고 있습니다.");
            return false;
        }

        IslandInviteSchedulerStorage.sendInvite(island, receiver);
        return false;
    }
}
