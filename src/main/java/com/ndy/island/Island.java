package com.ndy.island;

import com.ndy.island.party.IslandParty;
import com.ndy.util.LocationWrapper;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Island {

    private String ownerUuid;
    private LocationWrapper center;
    private IslandParty party;

    public Island(Player player, Location center) {
        this.ownerUuid = player.getUniqueId().toString();
        this.center = new LocationWrapper(center.getWorld(), center.getX(), center.getY(), center.getZ());
        this.party = new IslandParty();
        this.party.joinPartyMember(player);
    }

    public String getOwnerUuid() { return ownerUuid; }
    public LocationWrapper getCenter() { return center; }
    public IslandParty getParty() { return party; }

    public void setCenter(Location center) { this.center = new LocationWrapper(center.getWorld(), center.getX(), center.getY(), center.getZ()); }
    public void setOwnerUuid(String ownerUuid) { this.ownerUuid = ownerUuid; }
}
