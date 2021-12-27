package me.aglerr.playerprofiles.inventory;

import me.aglerr.mclibs.inventory.SimpleInventory;
import me.aglerr.playerprofiles.PlayerProfiles;
import me.aglerr.playerprofiles.configs.ConfigManager;
import me.aglerr.playerprofiles.inventory.items.ItemsLoader;
import me.aglerr.playerprofiles.manager.customgui.CustomGUI;
import me.aglerr.playerprofiles.manager.customgui.CustomGUIManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

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
            FileConfiguration config = ConfigManager.GUI.getConfig();

            String title = config.getString("title");
            int size = config.getInt("size");

            SimpleInventory inventory = new ProfileInventory(itemsLoader.getMainMenuItems(), player, target, size, title);
            inventory.open(player);
            return;
        }
        // Code logic if the custom gui isn't null
        // And the CustomGUI is @NotNull because it has been checked before calling this method
        // Create the LazyInventory object
        SimpleInventory inventory = new ProfileInventory(customGUI.getItems(), player, target, customGUI.getSize(), customGUI.getTitle());
        // Finally open the inventory for the player
        inventory.open(player);
    }

}
