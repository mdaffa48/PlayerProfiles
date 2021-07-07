package me.aglerr.playerprofiles.manager.customgui;

import me.aglerr.playerprofiles.inventory.items.GUIItem;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class CustomGUI {

    private final String fileName;
    private final String title;
    private final int size;
    private final FileConfiguration config;
    private final List<GUIItem> items;

    public CustomGUI(String fileName, String title, int size, FileConfiguration config, List<GUIItem> items) {
        this.fileName = fileName;
        this.title = title;
        this.size = size;
        this.config = config;
        this.items = items;
    }

    public String getFileName() {
        return fileName;
    }

    public String getTitle(){
        return title;
    }

    public int getSize(){
        return size;
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public List<GUIItem> getItems() {
        return items;
    }
}
