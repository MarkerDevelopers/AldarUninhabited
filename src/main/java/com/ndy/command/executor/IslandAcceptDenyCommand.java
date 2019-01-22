package com.ndy.command.executor;

import com.ndy.command.impl.AbstractCommandRamify;
import com.ndy.island.party.invite.IslandPartyInviteScheduler;
import com.ndy.island.party.invite.storage.IslandInviteSchedulerStorage;
import org.bukkit.entity.Player;

public class IslandAcceptDenyCommand extends AbstractCommandRamify {

    public IslandAcceptDenyCommand() {
        super("accept", "수락");
    }

    @Override
    public boolean execute(Object... objects) {
        Player player = (Player) objects[0];
        IslandPartyInviteScheduler scheduler =IslandInviteSchedulerStorage.getScheduler(player);

        if(scheduler == null) {
            player.sendMessage("&c당신은 받은 초대가 없습니다.");
            return false;
        }

        scheduler.getInvite().accept(true);
        return false;
    }
}
