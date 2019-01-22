package com.ndy.island.party.invite.storage;

import com.ndy.island.Island;
import com.ndy.island.party.invite.IslandPartyInvite;
import com.ndy.island.party.invite.IslandPartyInviteScheduler;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class IslandInviteSchedulerStorage {

    private static List<IslandPartyInviteScheduler> list = new ArrayList<>();

    public static boolean sendInvite(Island island, Player receiver) {
        IslandPartyInvite invite = new IslandPartyInvite(island, receiver);

        if(isSent(invite)) return false;

        IslandPartyInviteScheduler scheduler = new IslandPartyInviteScheduler(invite);
        scheduler.startTask();
        return true;
    }

    public static void remove(IslandPartyInviteScheduler scheduler) {
        list.remove(scheduler);
    }

    public static IslandPartyInviteScheduler getScheduler(Player receiver) {
        for(IslandPartyInviteScheduler scheduler : list) {
            if(scheduler.equals(receiver)) return scheduler;
        }
        return null;
    }

    public static boolean isSent(IslandPartyInvite invite) {
        for(IslandPartyInviteScheduler scheduler : list) {
            if(scheduler.equals(invite)) return true;
        }
        return false;
    }

}
