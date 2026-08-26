package com.muhammaddaffa.playerprofiles.hooks.combatlogx;

import com.muhammaddaffa.mdlib.utils.Logger;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Method;

public class HCombatLogX {

    private static Method isInCombatMethod;
    private static boolean initialized = false;

    private static void initialize() {
        if (initialized) return;
        initialized = true;

        Plugin plugin = Bukkit.getPluginManager().getPlugin("CombatLogX");
        if (plugin == null) return;

        try {
            Class<?> combatLogXClass = Class.forName("com.github.sirblobman.combatlogx.api.ICombatLogX");
            Class<?> combatManagerClass = Class.forName("com.github.sirblobman.combatlogx.api.manager.ICombatManager");

            Method getCombatManagerMethod = combatLogXClass.getMethod("getCombatManager");
            Object combatManager = getCombatManagerMethod.invoke(plugin);

            isInCombatMethod = combatManagerClass.getMethod("isInCombat", Player.class);
        } catch (Exception e) {
            Logger.severe("Failed to initialize CombatLogX hook: " + e.getMessage());
        }
    }

    public static boolean isInCombat(Player player) {
        initialize();
        if (isInCombatMethod == null) return false;

        try {
            Plugin plugin = Bukkit.getPluginManager().getPlugin("CombatLogX");
            if (plugin == null) return false;

            Class<?> combatLogXClass = Class.forName("com.github.sirblobman.combatlogx.api.ICombatLogX");
            Method getCombatManagerMethod = combatLogXClass.getMethod("getCombatManager");
            Object combatManager = getCombatManagerMethod.invoke(plugin);

            return (boolean) isInCombatMethod.invoke(combatManager, player);
        } catch (Exception e) {
            return false;
        }
    }

}