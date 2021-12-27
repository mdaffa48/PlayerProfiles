package me.aglerr.playerprofiles.configs;

import me.aglerr.mclibs.libs.CustomConfig;

public class ConfigManager {

    public static CustomConfig CONFIG;
    public static CustomConfig GUI;
    public static CustomConfig DATA;

    public static void initialize(){
        CONFIG = new CustomConfig("config.yml", null);
        GUI = new CustomConfig("gui.yml", null);
        DATA = new CustomConfig("data.yml", null);
    }

    public static void reload(){
        CONFIG.reloadConfig();
        GUI.reloadConfig();
    }

}
