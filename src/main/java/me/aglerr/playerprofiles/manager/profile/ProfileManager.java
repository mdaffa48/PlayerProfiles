package me.aglerr.playerprofiles.manager.profile;

import me.aglerr.mclibs.libs.CustomConfig;
import me.aglerr.playerprofiles.configs.ConfigManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProfileManager {

    private final Map<String, Profile> profileMap = new HashMap<>();

    public Profile getOrCreate(Player player){
        return getOrCreate(player.getUniqueId());
    }

    public Profile getOrCreate(UUID uuid){
        return getOrCreate(uuid.toString());
    }

    public Profile getOrCreate(String uuid){
        // Check if the player has the data
        if(profileMap.containsKey(uuid)){
            // Return the profile data, if the player has the data
            return profileMap.get(uuid);
        }
        // If player doesn't have profile data, create a new one
        Profile profile = new Profile(uuid);
        // Store the data to the hash map
        profileMap.put(uuid, profile);
        // Return the new profile
        return profile;
    }

    public boolean isProfileLocked(Player player){
        return getOrCreate(player).isLocked();
    }

    public void lockProfile(Player player){
        getOrCreate(player).setLocked(true);
    }

    public void unlockProfile(Player player){
        getOrCreate(player).setLocked(false);
    }

    public void loadProfileData(){
        // Get the file configuration of data.yml
        FileConfiguration config = ConfigManager.DATA.getConfig();
        // If there is no data, just stop the code
        if(!config.isConfigurationSection("data")) return;
        // Loop through all data config section
        for(String uuid : config.getConfigurationSection("data").getKeys(false)){
            // Get the lock status from the data
            boolean lockedStatus = config.getBoolean("data." + uuid);
            // Create a new profile object and store them into the hash map
            profileMap.put(uuid, new Profile(uuid, lockedStatus));
        }
    }

    public void saveProfileData(){
        // Get the File of data.yml
        CustomConfig data = ConfigManager.DATA;
        // Get the file configuration of data.yml
        FileConfiguration config = data.getConfig();
        // Loop through all data in the hash map
        for(String uuid : profileMap.keySet()){
            // Get the profile object
            Profile profile = getOrCreate(uuid);
            // Set the profile data into the config
            config.set("data." + uuid, profile.isLocked());
        }
        // After all data has been set, save the config
        data.saveConfig();
    }

}
