package me.aglerr.playerprofiles;

import me.aglerr.mclibs.MCLibs;
import me.aglerr.mclibs.libs.Common;
import me.aglerr.playerprofiles.commands.LockCommand;
import me.aglerr.playerprofiles.commands.MainCommand;
import me.aglerr.playerprofiles.commands.ProfileCommand;
import me.aglerr.playerprofiles.commands.UnlockCommand;
import me.aglerr.playerprofiles.configs.ConfigManager;
import me.aglerr.playerprofiles.listeners.PlayerInteract;
import me.aglerr.playerprofiles.inventory.InventoryManager;
import me.aglerr.playerprofiles.manager.DependencyManager;
import me.aglerr.playerprofiles.manager.customgui.CustomGUIManager;
import me.aglerr.playerprofiles.manager.profile.ProfileManager;
import me.aglerr.playerprofiles.metrics.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerProfiles extends JavaPlugin {

    private static final int BSTATS_ID = 7049;

    private static PlayerProfiles instance;

    private final InventoryManager inventoryManager = new InventoryManager(this);
    private final CustomGUIManager customGUIManager = new CustomGUIManager(this);
    private final ProfileManager profileManager = new ProfileManager();

    @Override
    public void onEnable(){
        instance = this;
        // Injecting the libs
        MCLibs.init(this);
        Common.setPrefix("[PlayerProfiles]");
        // Check the dependency
        DependencyManager.checkDependency();
        // Initialize all config
        ConfigManager.initialize();
        // Initialize all config values
        ConfigValue.initialize();
        // Load all items for the inventory
        inventoryManager.initialize();
        // Load all custom guis
        customGUIManager.loadCustomGUI();
        // Load all profile data
        profileManager.loadProfileData();
        // Register the player interact event
        Bukkit.getPluginManager().registerEvents(new PlayerInteract(this), this);
        // Register all commands
        registerCommands();
        // Add bstats metrics
        new Metrics(this, BSTATS_ID);
    }

    @Override
    public void onDisable(){
        // Plugin shutdown logic
        // Save all profile data
        profileManager.saveProfileData();
    }

    public void reloadAllThing(){
        // Reload all configuration
        ConfigManager.reload();
        // Re-initialize the config value
        ConfigValue.initialize();
        // Reload all items for the profile inventory
        inventoryManager.reInitialize();
        // Reload all custom guis
        customGUIManager.reloadCustomGUI();
    }

    private void registerCommands(){
        // Register /playerprofiles command
        new MainCommand(this).registerThisCommand();
        // Register /profile command
        new ProfileCommand(this).registerThisCommand();
        // Register /lockprofile command
        new LockCommand(this).registerThisCommand();
        // Register /unlockprofile command
        new UnlockCommand(this).registerThisCommand();
    }

    public static PlayerProfiles getInstance() {
        return instance;
    }

    public InventoryManager getInventoryManager(){
        return inventoryManager;
    }

    public CustomGUIManager getCustomGUIManager() {
        return customGUIManager;
    }

    public ProfileManager getProfileManager() {
        return profileManager;
    }
}
