package com.ndy.island.generator;

import com.ndy.island.Island;
import com.ndy.island.generator.abstraction.GenerateAble;
import com.ndy.island.option.IslandData;
import com.ndy.island.party.IslandParty;
import com.ndy.island.party.invite.IslandPartyInvite;
import org.bukkit.entity.Player;

public class IslandInviteGenerator implements GenerateAble {

    private IslandPartyInvite invite;

    public IslandInviteGenerator(IslandPartyInvite invite) {
        this.invite = invite;
    }

    @Override
    public boolean generate() {
        Island island = invite.getInviteIsland();
        Player player = invite.getReceiver();
        IslandParty party = island.getParty();
        party.joinPartyMember(player);

        IslandData.getInstance().getDataManager().set(player.getUniqueId().toString(), island.getOwnerUuid());
        IslandData.getInstance().save();


        player.teleport(island.getCenter().toLocation());
        return true;
    }

    @Override
    public String getName() {
        return invite.getReceiver().getName() + " Invite";
    }
}
