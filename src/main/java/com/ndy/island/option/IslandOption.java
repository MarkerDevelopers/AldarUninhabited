package com.ndy.island.option;

import com.ndy.util.LocationWrapper;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class IslandOption {

    private int height, range, distance, partyMax;
    private LocationWrapper lastLocation;
    private String lastIslandUUID;
    private String schemaitcName;

    public IslandOption() {
        this.height = 64;
        this.range = 100;
        this.distance = 15;
        this.partyMax = 5;
        this.lastLocation = new LocationWrapper(new Location(Bukkit.getWorld("island_world"), 0, 0, 0));
        this.lastIslandUUID = "none";
        this.schemaitcName = "none.schematic";
    }

    public int getDistance() { return distance; }
    public int getHeight() { return height; }
    public int getPartyMax() { return partyMax; }
    public int getRange() { return range; }
    public String getSchematics() { return this.schemaitcName; }
    public LocationWrapper getLastLocation() { return lastLocation; }
    public String getLastIslandUUID() { return lastIslandUUID; }

    public void setDistance(int distance) { this.distance = distance; }
    public void setHeight(int height) { this.height = height; }
    public void setLastIslandUUID(String lastIslandUUID) { this.lastIslandUUID = lastIslandUUID; }
    public void setLastLocation(LocationWrapper lastLocation) { this.lastLocation = lastLocation; }
    public void setPartyMax(int partyMax) { this.partyMax = partyMax; }
    public void setRange(int range) { this.range = range; }
    public void setSchematics(String schemaitcName) { this.schemaitcName = schemaitcName; }

}
