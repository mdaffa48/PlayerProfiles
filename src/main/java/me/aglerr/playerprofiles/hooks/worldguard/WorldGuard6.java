package me.aglerr.playerprofiles.hooks.worldguard;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class WorldGuard6 implements IRegionFinder{

    @Override
    public @NotNull List<String> getRegions(@NotNull Location location) {
        // Get the ApplicableRegionSet from the location
        com.sk89q.worldguard.protection.ApplicableRegionSet ars = WGBukkit.getPlugin().getRegionContainer().createQuery().getApplicableRegions(location);
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
