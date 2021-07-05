package me.aglerr.playerprofiles;

import me.aglerr.playerprofiles.configs.ConfigManager;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigValue {

    public static String PREFIX;

    public static boolean AUTO_REFRESH_ENABLED;
    public static int AUTO_REFRESH_TICK;

    public static boolean DISTANCE_CHECK_ENABLED;
    public static double MAXIMUM_DISTANCE;
    public static String DISTANCE_TOO_FAR;

    public static void initialize(){
        FileConfiguration config = ConfigManager.CONFIG.getConfig();
        PREFIX = config.getString("messages.prefix");

        AUTO_REFRESH_ENABLED = config.getBoolean("autoRefresh.enabled");
        AUTO_REFRESH_TICK = config.getInt("autoRefresh.refreshEvery");

        DISTANCE_CHECK_ENABLED = config.getBoolean("distanceCheck.enabled");
        MAXIMUM_DISTANCE = config.getDouble("distanceCheck.distance");
        DISTANCE_TOO_FAR = config.getString("distanceCheck.tooFarMessage");
    }

}
