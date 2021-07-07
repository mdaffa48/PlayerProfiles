package me.aglerr.playerprofiles.hooks.deluxecombat;

import me.aglerr.playerprofiles.manager.DependencyManager;
import org.bukkit.entity.Player;

public class HDeluxeCombat {

    public static boolean isInCombat(Player player){
        return DependencyManager.getDeluxeAPI().isInCombat(player);
    }

}
