package me.aglerr.playerprofiles.wg6;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class RegionFinder {

    public static List<String> getRegionsInLocation(Location location){
        // Get the RegionManager using the world from the location
        RegionManager rm = WGBukkit.getRegionManager(location.getWorld());
        // Get the ApplicableRegionSet from the location
        ApplicableRegionSet ars = rm.getApplicableRegions(location);
        // Create an empty array list of string
        List<String> regions = new ArrayList<>();
        // Loop through all regions in the location
        for(ProtectedRegion region : ars){
            // Add the region name to the list
            regions.add(region.getId());
        }
        // Finally, return the list of regions name
        return regions;
    }

}
