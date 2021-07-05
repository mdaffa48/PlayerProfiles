package me.aglerr.playerprofiles.manager;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class DependencyManager {

    public static boolean PLACEHOLDER_API;

    public static boolean WORLD_GUARD;
    public static int WORLD_GUARD_VERSION;

    public static void checkDependency(){
        PluginManager pm = Bukkit.getPluginManager();

        PLACEHOLDER_API = pm.getPlugin("PlaceholderAPI") != null;
        WORLD_GUARD = pm.getPlugin("WorldGuard") != null;

        if(WORLD_GUARD){
            Plugin plugin = pm.getPlugin("WorldGuard");

            if(plugin.getDescription().getVersion().startsWith("6"))
                WORLD_GUARD_VERSION = 6;

            if(plugin.getDescription().getVersion().startsWith("7"))
                WORLD_GUARD_VERSION = 7;
        }

    }

}
