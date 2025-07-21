package com.muhammaddaffa.playerprofiles.manager.customgui;

import com.muhammaddaffa.mdlib.utils.Logger;
import com.muhammaddaffa.playerprofiles.ConfigValue;
import com.muhammaddaffa.playerprofiles.PlayerProfiles;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CustomGuiCreator {

    private final PlayerProfiles plugin;

    public CustomGuiCreator(PlayerProfiles plugin){
        this.plugin = plugin;
    }

    public void createCustomGui() {
        // Create the gui
        FileConfiguration config = PlayerProfiles.GUI_CREATOR.getConfig();
        for (String section : config.getConfigurationSection("menu").getKeys(false)) {
            String fileName = config.getString("menu." + section + ".fileName");
            if (fileName == null || !fileName.endsWith(".yml")) {
                Logger.severe("Invalid file name or invalid extensions!");
                continue;
            }

            File file = getFileName(fileName);
            if (file.exists()) continue;

            // Create the file
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
                if (file.length() == 0) {
                    populateFile(file);
                }
                Logger.info("Created custom gui " + fileName);
            } catch (Exception ex) {
                Logger.severe("Failed to create custom gui " + fileName);
                ex.printStackTrace();
            }
        }
    }

    private void populateFile(File file) {
        // Populate the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(ConfigValue.template());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File getMainDirectory() {
        return new File(PlayerProfiles.getInstance().getDataFolder() + File.separator + "custom-gui");
    }
    private File getFileName(String name) {
        return new File(PlayerProfiles.getInstance().getDataFolder() + File.separator + "custom-gui" + File.separator + name);
    }
}
