package com.ndy.island;

import com.ndy.island.option.IslandOption;
import com.ndy.island.option.IslandOptionFactory;
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

    public boolean isOwner(Player player) { return getOwnerUuid().equalsIgnoreCase(player.getUniqueId().toString()); }

    public boolean withInIsland(Player player) {
        Location location = player.getLocation();
        int range = IslandOptionFactory.getInstance().getOption().getRange();
        int x = this.center.getBlockX(), z = this.center.getBlockZ();
        int subX = x-range, subZ = z-range;
        int addX = x+range, addZ = z+range;
        int locX = location.getBlockX(), locZ = location.getBlockZ();

        return center.getWolrd().equals(location.getWorld()) && (subX <= locX && addX >= locX) && (subZ <= locZ && addZ >= locZ);
    }
}
