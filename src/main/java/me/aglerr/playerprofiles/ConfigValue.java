package me.aglerr.playerprofiles;

import me.aglerr.playerprofiles.configs.ConfigManager;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class ConfigValue {

    public static String PREFIX;

    // Auto Refresh
    public static boolean AUTO_REFRESH_ENABLED;
    public static int AUTO_REFRESH_TICK;

    // Distance Check
    public static boolean DISTANCE_CHECK_ENABLED;
    public static double MAXIMUM_DISTANCE;
    public static String DISTANCE_TOO_FAR;

    // Options
    public static boolean DISABLE_NPC_PROFILE;
    public static boolean MUST_SHIFT_CLICK;
    public static boolean DISABLE_IN_COMBAT_ENABLED;
    public static String DISABLE_IN_COMBAT_MESSAGE;

    // Disabled worlds
    public static List<String> DISABLED_WORLDS;
    public static String DISABLED_WORLD_MESSAGE;

    // Disabled regions
    public static List<String> DISABLED_REGIONS;
    public static String PLAYER_DISABLED_REGIONS;
    public static String TARGET_DISABLED_REGIONS;

    // Interact Cooldown
    public static boolean COOLDOWN_ENABLED;
    public static int COOLDOWN_TIME;
    public static String COOLDOWN_MESSAGE;

    // Messages
    public static String NO_PERMISSION;
    public static String RELOAD;
    public static String INVALID_PLAYER;
    public static String LOCK_PROFILE;
    public static String LOCK_PROFILE_OTHERS;
    public static String UNLOCK_PROFILE;
    public static String UNLOCK_PROFILE_OTHERS;
    public static String INVALID_GUI_NAME;

    // Messages List
    public static List<String> HELP_MESSAGES;

    public static void initialize(){
        FileConfiguration config = ConfigManager.CONFIG.getConfig();
        PREFIX = config.getString("messages.prefix");

        AUTO_REFRESH_ENABLED = config.getBoolean("autoRefresh.enabled");
        AUTO_REFRESH_TICK = config.getInt("autoRefresh.refreshEvery");

        DISTANCE_CHECK_ENABLED = config.getBoolean("distanceCheck.enabled");
        MAXIMUM_DISTANCE = config.getDouble("distanceCheck.distance");
        DISTANCE_TOO_FAR = config.getString("distanceCheck.tooFarMessage");

        DISABLE_NPC_PROFILE = config.getBoolean("options.disableNPC");
        MUST_SHIFT_CLICK = config.getBoolean("options.shiftClick");
        DISABLE_IN_COMBAT_ENABLED = config.getBoolean("options.disableInCombat.enabled");
        DISABLE_IN_COMBAT_MESSAGE = config.getString("options.disableInCombat.message");

        DISABLED_WORLDS = config.getStringList("disabledWorlds.worlds");
        DISABLED_WORLD_MESSAGE = config.getString("disabledWorlds.message");

        DISABLED_REGIONS = config.getStringList("disabledRegions.regions");
        PLAYER_DISABLED_REGIONS = config.getString("disabledRegions.playerInDisabledRegionMessage");
        TARGET_DISABLED_REGIONS = config.getString("disabledRegions.targetInDisabledRegionMessage");

        COOLDOWN_ENABLED = config.getBoolean("cooldown.enabled");
        COOLDOWN_TIME = config.getInt("cooldown.duration");
        COOLDOWN_MESSAGE = config.getString("cooldown.message");
    }

}
