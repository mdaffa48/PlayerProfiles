package me.aglerr.playerprofiles.hooks.worldguard;

import me.aglerr.playerprofiles.manager.DependencyManager;

public class WorldGuardWrapper {

    private IRegionFinder regionFinder;

    public WorldGuardWrapper(){
        // Return if world guard is not enabled
        if(!DependencyManager.WORLD_GUARD) return;
        // Check if the world guard version is 6
        if(DependencyManager.WORLD_GUARD_VERSION == 6)
            // Initialize the region finder with WorldGuard6
            regionFinder = new WorldGuard6();
        // Check if the world guard version is 7
        if(DependencyManager.WORLD_GUARD_VERSION == 7)
            // Initialize the region finder with WorldGuard7
            regionFinder = new WorldGuard7();
    }

    public IRegionFinder getRegionFinder() {
        return regionFinder;
    }
}
