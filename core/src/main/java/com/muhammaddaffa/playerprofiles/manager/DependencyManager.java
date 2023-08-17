package com.muhammaddaffa.playerprofiles.manager;

import me.aglerr.mclibs.libs.Common;
import nl.marido.deluxecombat.api.DeluxeCombatAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class DependencyManager {

    public static boolean PLACEHOLDER_API;
    public static boolean COMBAT_LOG_X;

    public static boolean DELUXE_COMBAT;
    private static DeluxeCombatAPI deluxeAPI;

    public static boolean WORLD_GUARD;
    public static int WORLD_GUARD_VERSION;

    public static void checkDependency(){
        PluginManager pm = Bukkit.getPluginManager();

        PLACEHOLDER_API = pm.getPlugin("PlaceholderAPI") != null;
        COMBAT_LOG_X = pm.getPlugin("CombatLogX") != null;
        DELUXE_COMBAT = pm.getPlugin("DeluxeCombat") != null;
        WORLD_GUARD = pm.getPlugin("WorldGuard") != null;

        if(DELUXE_COMBAT){
            deluxeAPI = new DeluxeCombatAPI();
        }

        if(WORLD_GUARD){
            Plugin plugin = pm.getPlugin("WorldGuard");

            if(plugin.getDescription().getVersion().startsWith("6"))
                Common.log("&rFound WorldGuard! Using WorldGuard API version 6");
                WORLD_GUARD_VERSION = 6;

            if(plugin.getDescription().getVersion().startsWith("7"))
                Common.log("&rFound WorldGuard! Using WorldGuard API version 7");
                WORLD_GUARD_VERSION = 7;
        }

    }

    public static DeluxeCombatAPI getDeluxeAPI(){
        return deluxeAPI;
    }

}
