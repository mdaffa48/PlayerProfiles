package me.aglerr.playerprofiles.inventory;

import me.aglerr.mclibs.inventory.SimpleInventory;
import me.aglerr.mclibs.libs.Common;
import me.aglerr.mclibs.libs.Executor;
import me.aglerr.playerprofiles.ConfigValue;
import me.aglerr.playerprofiles.configs.ConfigManager;
import me.aglerr.playerprofiles.inventory.items.GUIItem;
import me.aglerr.playerprofiles.utils.ClickManager;
import me.aglerr.playerprofiles.utils.ItemManager;
import me.aglerr.playerprofiles.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;

public class ProfileInventory extends SimpleInventory {

    public ProfileInventory(List<GUIItem> items, Player player, Player target, int size, String title) {
        super(size, Utils.tryParsePAPI(title, player, target));

        this.setAllItems(items, player, target);

        // If auto refresh is enabled
        if(ConfigValue.AUTO_REFRESH_ENABLED){
            // Start the auto refresh task
            BukkitTask task = Executor.syncTimer(0L, ConfigValue.AUTO_REFRESH_TICK, () ->
                    this.setAllItems(items, player, target));
            // And remove the task after the inventory closed
            this.addCloseHandler(event -> task.cancel());
        }
        // If distance check is enabled
        if(ConfigValue.DISTANCE_CHECK_ENABLED){
            // Start the check distance task
            BukkitTask task = Executor.syncTimer(0L, 20L, () ->
                    checkDistance(player, target));
            // And remove the task after the inventory closed
            this.addCloseHandler(event -> task.cancel());
        }

    }

    private void setAllItems(List<GUIItem> items, Player player, Player target){
        // Loop through all items
        items.forEach(item -> {
            // Get the item stack
            ItemStack stack = ItemManager.createGUIItem(item, player, target);
            // Check if the item use permission to see
            if(item.isUsePermission()){
                // If the player doesn't have the permission, don't show it
                if(!player.hasPermission(item.getPermission())) {
                    return;
                }
            }
            // Check if the item is set to only visitor
            if(item.isOnlyVisitor()){
                // If the player and the target is the same, don't show the item
                if(player.equals(target)) {
                    return;
                }
            }
            // Check if the item is set to only owner
            if(item.isOnlyOwner()){
                // If the player and the target is not the same, don't show the item
                if(!player.equals(target)) {
                    return;
                }
            }
            // Now, set the item to the inventory
            this.setItems(item.getSlots(), stack, event -> {
                // Set the click event for the item
                ClickManager.handleInventoryClick(item, player, target, event);
            });
        });
        // After all items being set, we finally set the fill items
        ItemManager.fillItem(this, ConfigManager.GUI.getConfig());
    }

    private void checkDistance(Player player, Player target){
        // If the target is offline, just close the inventory
        if(target == null){
            // Close the inventory
            player.closeInventory();
            // Send a message to the player
            player.sendMessage(Common.color(ConfigValue.DISTANCE_TOO_FAR
                    .replace("{prefix}", ConfigValue.PREFIX))
                    .replace("{player}", target.getName()));
            return;
        }
        if(!player.getWorld().equals(target.getWorld())){
            // Close the inventory
            player.closeInventory();
            // Send a message to the player
            player.sendMessage(Common.color(ConfigValue.DISTANCE_TOO_FAR
                    .replace("{prefix}", ConfigValue.PREFIX))
                    .replace("{player}", target.getName()));
            return;
        }
        // Get the distance between the player and the target
        double distance = player.getLocation().distance(target.getLocation());
        // Check if the distance is greater than the configured maximum distance
        if(distance > ConfigValue.MAXIMUM_DISTANCE){
            // Close the inventory
            player.closeInventory();
            // Send a message to the player
            player.sendMessage(Common.color(ConfigValue.DISTANCE_TOO_FAR
                    .replace("{prefix}", ConfigValue.PREFIX))
                    .replace("{player}", target.getName()));
        }
    }
}
