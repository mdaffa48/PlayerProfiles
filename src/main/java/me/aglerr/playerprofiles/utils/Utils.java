package me.aglerr.playerprofiles.utils;

import me.aglerr.lazylibs.libs.Common;
import me.aglerr.playerprofiles.PlayerProfiles;
import me.aglerr.playerprofiles.manager.DependencyManager;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static boolean hasCustomModelData(){
        return Bukkit.getVersion().contains("1.14") ||
                Bukkit.getVersion().contains("1.15") ||
                Bukkit.getVersion().contains("1.16") ||
                Bukkit.getVersion().contains("1.17");
    }

    public static String tryParsePAPI(String message, Player target){
        // Get the final message with hex
        String finalMessage = PlayerProfiles.HEX_AVAILABLE ? Common.hex(message) : message;
        // Finally, return the value with placeholder api support
        return DependencyManager.PLACEHOLDER_API ?
                PlaceholderAPI.setPlaceholders(target, finalMessage) :
                finalMessage;
    }

    public static List<String> tryParsePAPI(List<String> message, Player target){
        // Check if placeholder api is not enabled
        if(!DependencyManager.PLACEHOLDER_API)
            // Return the colored message with hex support
            return PlayerProfiles.HEX_AVAILABLE ? Common.hex(message) : Common.color(message);
        // Create an empty list of string
        List<String> translated = new ArrayList<>();
        // Loop through all the messages
        for(String text : message){
            // Add the message to the list with translated hex and placeholder api
            translated.add(
                    PlaceholderAPI.setPlaceholders(target, PlayerProfiles.HEX_AVAILABLE ? Common.hex(text) : text)
            );
        }
        // Finally, return the list
        return translated;
    }

}
