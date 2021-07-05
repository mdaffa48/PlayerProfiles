package me.aglerr.playerprofiles.inventory;

import me.aglerr.lazylibs.inventory.LazyInventory;
import me.aglerr.playerprofiles.configs.ConfigManager;
import me.aglerr.playerprofiles.enums.InventoryType;
import me.aglerr.playerprofiles.inventory.items.ItemsLoader;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class InventoryManager {

    private final ItemsLoader itemsLoader = new ItemsLoader();

    public void initialize(){
        itemsLoader.loadItems();
    }

    public void reInitialize(){
        itemsLoader.reloadItems();
    }

    public void openInventory(InventoryType type, Player player, Player target){
        // Check if the type is profile inventory
        if(type == InventoryType.PROFILE){
            FileConfiguration config = ConfigManager.GUI.getConfig();

            String title = config.getString("title");
            int size = config.getInt("size");

            LazyInventory inventory = new ProfileInventory(itemsLoader.getMainMenuItems(), player, target, size, title);
            inventory.open(player);
        }
    }

}
