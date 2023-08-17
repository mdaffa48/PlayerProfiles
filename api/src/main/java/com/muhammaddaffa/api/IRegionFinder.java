package com.muhammaddaffa.api;

import org.bukkit.Location;

import java.util.List;

public interface IRegionFinder {

    List<String> getRegions(Location location);

}