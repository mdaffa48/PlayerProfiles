package me.aglerr.playerprofiles.wg7;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class RegionFinder {

    /**
     * Get a list of region in a specific location
     *
     * @param location - the location to get a list of regions
     * @return - the list of region name
     */
    public static List<String> getRegionsInLocation(Location location){
        // Get the location util using BukkitAdapter
        com.sk89q.worldedit.util.Location loc = BukkitAdapter.adapt(location);
        // Get the RegionContainer
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        // Get the RegionQuery
        RegionQuery query = container.createQuery();
        // Get the ApplicableRegionSet
        ApplicableRegionSet ars = query.getApplicableRegions(loc);
        // Create an empty list of string
        List<String> regions = new ArrayList<>();
        // Loop through all regions in the location
        for(ProtectedRegion region : ars){
            // Add the region name/id to the regions list
            regions.add(region.getId());
        }
        // Finally, return the list of regions name
        return regions;
    }

}
