package com.muhammaddaffa.playerprofiles.worldguardwrapper.wg7;

import com.muhammaddaffa.api.IRegionFinder;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class RegionFinder7 implements IRegionFinder {

    @Override
    public List<String> getRegions(Location location) {
        com.sk89q.worldedit.util.Location loc = BukkitAdapter.adapt(location);

        com.sk89q.worldguard.protection.regions.RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        com.sk89q.worldguard.protection.regions.RegionQuery query = container.createQuery();
        com.sk89q.worldguard.protection.ApplicableRegionSet ars = query.getApplicableRegions(loc);

        List<String> regions = new ArrayList<>();
        for (ProtectedRegion region : ars) {
            regions.add(region.getId());
        }
        return regions;
    }

}