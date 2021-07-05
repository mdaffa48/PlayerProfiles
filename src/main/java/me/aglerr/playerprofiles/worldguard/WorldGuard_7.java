package me.aglerr.playerprofiles.worldguard;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class WorldGuard_7 implements IRegionFinder{

    @Override
    public List<String> getRegionInLocation(@NotNull Location location) {
        // Get the location util using BukkitAdapter
        com.sk89q.worldedit.util.Location loc = BukkitAdapter.adapt(location);
        // Get the RegionContainer
        com.sk89q.worldguard.protection.regions.RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        // Get the RegionQuery
        com.sk89q.worldguard.protection.regions.RegionQuery query = container.createQuery();
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
