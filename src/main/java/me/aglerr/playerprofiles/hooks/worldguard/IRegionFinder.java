package me.aglerr.playerprofiles.hooks.worldguard;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IRegionFinder {

    @NotNull
    List<String> getRegions(@NotNull Location location);

}
