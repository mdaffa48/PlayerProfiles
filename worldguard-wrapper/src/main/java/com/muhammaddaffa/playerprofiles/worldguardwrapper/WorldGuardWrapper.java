package com.muhammaddaffa.playerprofiles.worldguardwrapper;

import com.muhammaddaffa.api.IRegionFinder;
import com.muhammaddaffa.playerprofiles.worldguardwrapper.wg7.RegionFinder7;

public class WorldGuardWrapper {

    private static final WorldGuardWrapper instance = new WorldGuardWrapper();

    public static WorldGuardWrapper getInstance(){
        return instance;
    }

    private final IRegionFinder regionFinder = new RegionFinder7();

    public IRegionFinder getRegionFinder() {
        return regionFinder;
    }

}
