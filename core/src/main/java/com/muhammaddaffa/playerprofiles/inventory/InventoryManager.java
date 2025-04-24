package com.muhammaddaffa.playerprofiles.inventory;

import com.muhammaddaffa.mdlib.fastinv.FastInv;
import com.muhammaddaffa.playerprofiles.PlayerProfiles;
import com.muhammaddaffa.playerprofiles.inventory.items.ItemsLoader;
import com.muhammaddaffa.playerprofiles.manager.customgui.CustomGUI;
import com.muhammaddaffa.playerprofiles.manager.customgui.CustomGUIManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.logging.Logger;

public class InventoryManager {

    private final ItemsLoader itemsLoader;
    private final CustomGUIManager customGUIManager;
    public InventoryManager(PlayerProfiles plugin){
        itemsLoader = new ItemsLoader(plugin);
        customGUIManager = plugin.getCustomGUIManager();
    }

    public void initialize(){
        itemsLoader.loadItems();
    }

    public void reInitialize(){
        itemsLoader.reloadItems();
    }

    public void openInventory(@Nullable CustomGUI customGUI, Player player, Player target){
        // Check if the file name is null (means it's the main inventory)
        if(customGUI == null){
            FileConfiguration config = PlayerProfiles.GUI_DEFAULT.getConfig();

            String title = config.getString("title");

            int size = config.getInt("size");

            FastInv inventory = new ProfileInventory(itemsLoader.getMainMenuItems(), player, target, size, title);
            inventory.open(player);
            System.out.println("FROM NULL");
            return;
        }
        // Code logic if the custom gui isn't null
        // And the CustomGUI is @NotNull because it has been checked before calling this method
        // Create the LazyInventory object
        FastInv inventory = new ProfileInventory(customGUI.items(), player, target, customGUI.size(), customGUI.title());
        System.out.println("NOT NULL");
        // Finally open the inventory for the player
        inventory.open(player);
    }

}
