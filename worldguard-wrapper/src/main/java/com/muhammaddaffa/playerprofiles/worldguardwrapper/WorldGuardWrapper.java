package com.muhammaddaffa.playerprofiles.worldguardwrapper;

import com.muhammaddaffa.api.IRegionFinder;
import com.muhammaddaffa.playerprofiles.worldguardwrapper.wg6.RegionFinder6;
import com.muhammaddaffa.playerprofiles.worldguardwrapper.wg7.RegionFinder7;

public class WorldGuardWrapper {

    private static final WorldGuardWrapper instance = new WorldGuardWrapper();

    public static WorldGuardWrapper getInstance(){
        return instance;
    }

    private final IRegionFinder regionFinder;

    private WorldGuardWrapper(){
        IRegionFinder selected;
        try{
            Class.forName("com.sk89q.worldguard.WorldGuard");
            selected = new RegionFinder7();
        } catch (ClassNotFoundException ex){
            selected = new RegionFinder6();
        }
        regionFinder = selected;
    }

    public IRegionFinder getRegionFinder() {
        return regionFinder;
    }

}
