package com.ndy.island.party.invite;

import com.ndy.island.Island;
import org.bukkit.entity.Player;

public class IslandPartyInvite {

    private Island inviteIsland;
    private Player receiver;
    private boolean accept = false;

    public IslandPartyInvite(Island inviteIsland, Player receiver) {
        this.inviteIsland = inviteIsland;
        this.receiver = receiver;
    }

    public void accept(boolean accept) { this.accept = accept; }
    public boolean isAccept() { return accept; }
    public Island getInviteIsland() { return inviteIsland; }
    public Player getReceiver() { return receiver; }
}
