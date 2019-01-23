package com.ndy.command.executor;

import com.ndy.command.impl.AbstractCommandRamify;
import com.ndy.island.party.invite.IslandPartyInviteScheduler;
import com.ndy.island.party.invite.storage.IslandInviteSchedulerStorage;
import org.bukkit.entity.Player;

public class IslandDenyCommand extends AbstractCommandRamify {

    public IslandDenyCommand() {
        super("거절", "deny");
    }

    @Override
    public boolean execute(Object... objects) {
        Player player = (Player) objects[0];
        IslandPartyInviteScheduler scheduler = IslandInviteSchedulerStorage.getScheduler(player);

        if(scheduler == null) {
            setMessage("&c당신은 받은 초대가 없습니다.");
            return false;
        }

        scheduler.getInvite().accept(false);
        scheduler.cancelTask();
        setMessage("&c거절하였습니다.");
        return false;
    }
}
