package me.aglerr.playerprofiles.utils;

import me.aglerr.lazylibs.libs.Common;
import me.aglerr.playerprofiles.inventory.items.GUIItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class ClickManager {

    private static final Pattern pattern = Pattern.compile("(?<=\\[CONSOLE\\] |\\[PLAYER\\] |\\[SOUND\\] |\\[MESSAGE\\] |\\[OPENGUIMENU\\] |\\[CLOSE\\])");

    public static void handleInventoryClick(GUIItem item, Player player, Player target, InventoryClickEvent event){
        // If the click type is LEFT, handle the left click
        if(event.getClick() == ClickType.LEFT)
            handleInventoryLeftClick(item, player, target);
        // If the click type is RIGHT, handle the right click
        if(event.getClick() == ClickType.RIGHT)
            handleInventoryRightClick(item, player, target);
    }

    private static void handleInventoryLeftClick(GUIItem item, Player player, Player target){
        // Loop through all left click commands and handle the task
        item.getLeftCommands().forEach(command -> handleTask(command, player, target));
    }

    private static void handleInventoryRightClick(GUIItem item, Player player, Player target){
        // Loop through all right click commands and handle the task
        item.getRightCommands().forEach(command -> handleTask(command, player, target));
    }

    private static void handleTask(String command, Player player, Player target){
        // Get the array from the splitted pattern
        String[] cmds = pattern.split(command);
        String tag = cmds[0];
        // Get the list of arguments
        List<String> taskList = new ArrayList<>(Arrays.asList(cmds).subList(1, cmds.length));
        String task = cmds.length > 1 ? String.join(" ", taskList) : "";
        // Get the final arguments with parsed placeholder
        String finalTask = Utils.tryParsePAPI(task, target)
                .replace("{player}", player.getName())
                .replace("{target}", target.getName());
        // Check if the tag is CONSOLE
        if(tag.equalsIgnoreCase("[CONSOLE] ")){
            // Execute a command in console
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), finalTask);
            return;
        }
        // Check if the tag is PLAYER
        if(tag.equalsIgnoreCase("[PLAYER] ")){
            // Make the player perform a command
            player.performCommand(finalTask);
            return;
        }
        // Check if the tag is MESSAGE
        if(tag.equalsIgnoreCase("[MESSAGE] ")){
            // Send the player a message
            player.sendMessage(Common.color(finalTask));
            return;
        }
        // Check if the tag is OPENGUIMENU
        if(tag.equalsIgnoreCase("[OPENGUIMENU] ")){
            // Open a custom menu (coming soon)
            return;
        }
        // Check if the tag is CLOSE
        if(tag.equalsIgnoreCase("[CLOSE]")){
            // Close the inventory
            player.closeInventory();
        }
    }

}
