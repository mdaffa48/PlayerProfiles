package me.aglerr.playerprofiles;

import me.aglerr.lazylibs.LazyLibs;
import me.aglerr.lazylibs.libs.Common;
import me.aglerr.playerprofiles.commands.TestCommand;
import me.aglerr.playerprofiles.configs.ConfigManager;
import me.aglerr.playerprofiles.inventory.InventoryManager;
import me.aglerr.playerprofiles.manager.DependencyManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerProfiles extends JavaPlugin {

    public static boolean HEX_AVAILABLE;

    private final InventoryManager inventoryManager = new InventoryManager();

    @Override
    public void onEnable(){
        // Injecting the libs
        LazyLibs.inject(this);
        Common.setPrefix("[PlayerProfiles]");
        // Check if hex is available
        HEX_AVAILABLE = isHexAvailable();
        // Check the dependency
        DependencyManager.checkDependency();
        // Initialize all config
        ConfigManager.initialize();
        // Initialize all config values
        ConfigValue.initialize();
        // Load all items for the inventory
        inventoryManager.initialize();
        // Register command
        this.getCommand("test").setExecutor(new TestCommand(this));
    }

    @Override
    public void onDisable(){

    }

    private boolean isHexAvailable(){
        return Bukkit.getVersion().contains("1.16") ||
                Bukkit.getVersion().contains("1.17");
    }

    public InventoryManager getInventoryManager(){
        return inventoryManager;
    }

}
