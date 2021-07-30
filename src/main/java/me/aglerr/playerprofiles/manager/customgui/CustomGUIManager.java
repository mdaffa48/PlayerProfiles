package me.aglerr.playerprofiles.manager.customgui;

import me.aglerr.playerprofiles.PlayerProfiles;
import me.aglerr.playerprofiles.inventory.items.GUIItem;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomGUIManager {

    private final Map<String, CustomGUI> guiMap = new HashMap<>();

    private final PlayerProfiles plugin;
    public CustomGUIManager(PlayerProfiles plugin){
        this.plugin = plugin;
    }

    public CustomGUI getByName(String name){
        return guiMap.get(name);
    }

    public List<String> getListName(){
        return new ArrayList<>(guiMap.keySet());
    }

    public void clearCustomGUI(){
        guiMap.clear();
    }

    public void reloadCustomGUI(){
        clearCustomGUI();
        loadCustomGUI();
    }

    public void loadCustomGUI(){
        // Get the directory path
        final String DIRECTORY = plugin.getDataFolder() + File.separator + "custom-gui";
        // Get the custom-gui directory
        File directory = new File(DIRECTORY);
        // Check if the directory is not exist
        if(!directory.exists())
            // Finally create the directory
            directory.mkdirs();
        // Get all files in custom-gui directory
        File[] files = new File(DIRECTORY).listFiles();
        // Check if there is no files
        if(files.length <= 0){
            // Create the file onto the server directory
            plugin.saveResource("custom-gui/punish-gui.yml", false);
        }
        // Now get the final list of files in the directory
        File[] finalFiles = new File(DIRECTORY).listFiles();
        // Loop through all files
        for(File file : finalFiles){
            // Get the file configuration
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            // Get the file name
            String fileName = file.getName();
            // Create an empty array list of GUIItem
            List<GUIItem> guiItems = new ArrayList<>();
            // Get the inventory title
            String title = config.getString("title");
            // Get the final title, null-safe
            String finalTitle = title == null ? "Inventory" : title;
            // Get the inventory size
            int size = config.getInt("size");
            // Now, we load the items - first loop through all items
            for(String configKey : config.getConfigurationSection("items").getKeys(false)){
                String path = "items." + configKey;
                String type = config.getString(path + ".type");
                String material = config.getString(path + ".material");
                int amount = config.getInt(path + ".amount");
                String name = config.getString(path + ".name");
                List<Integer> slots = config.getIntegerList(path + ".slots");
                boolean glowing = config.getBoolean(path + ".glowing");
                boolean hideAttributes = config.getBoolean(path + ".hideAttributes");
                boolean usePermission = config.getBoolean(path + ".usePermission");
                String permission = config.getString(path + ".permission");
                List<String> lore = config.getStringList(path + ".lore");
                List<String> leftCommands = config.getStringList(path + ".leftClickCommands");
                List<String> rightCommands = config.getStringList(path + ".rightClickCommands");
                int customModelData = config.getInt(path + ".customModelData");
                boolean onlyOwner = config.getBoolean(path + ".onlyOwner");
                boolean onlyVisitor = config.getBoolean(path + ".onlyVisitor");
                // Create the GUIItem object
                GUIItem guiItem = new GUIItem(type, material, amount, name, slots, glowing, hideAttributes, usePermission,
                        permission, lore, leftCommands, rightCommands, customModelData, onlyOwner, onlyVisitor);
                // Finally add the gui item to the list that has been created before
                guiItems.add(guiItem);
            }
            // After items are loaded, now we create the CustomGUI object
            CustomGUI customGUI = new CustomGUI(fileName, finalTitle, size, config, guiItems);
            // Now we store the custom gui onto the list
            this.guiMap.put(fileName, customGUI);
        }
        // That's it
    }

}
